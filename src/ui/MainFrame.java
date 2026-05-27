package ui;

import java.awt.Color;
import chart.PopularityChart;
import java.awt.EventQueue;
import java.awt.Component;
import export.ExcelExporter;

import javax.swing.SwingWorker;

import dao.PlaylistDAO;
import dao.TrackDAO;

import model.Playlist;
import model.Track;
import model.User;

import util.SessionManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import chart.GenreChart;
import factory.DAOFactory;
public class MainFrame extends JFrame {

    private int selectedId = -1;

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JTextField txtSearch;
    private JTextField txtName;
    private JTextField txtPopularity;
    private JTextField txtDuration;
    private JTextField txtGenre;

    private JTable tableTracks;

    private JComboBox<String> cbGenre;
    private JLabel lblPage;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnExport;
    private JButton btnPlaylist;

    // CURRENT USER
    private User currentUser;

    private String sessionToken;
    private int currentPage = 1;

    private int pageSize = 5;

    /**
     * Launch
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    LoginFrame frame =
                            new LoginFrame();

                    frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create frame
     */
    public MainFrame(
            User user,
            String token
    ) {

        this.currentUser = user;

        this.sessionToken = token;

        // WINDOW
        setTitle(
                "Spotify Desktop App - "
                + currentUser.getRole()
        );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 1000, 650);

        setLocationRelativeTo(null);

        // PANEL
        contentPane = new JPanel();

        contentPane.setBackground(
                new Color(25,20,20)
        );

        contentPane.setBorder(
                new EmptyBorder(5, 5, 5, 5)
        );

        setContentPane(contentPane);

        contentPane.setLayout(null);

        // TITLE
        JLabel lblTitle =
                new JLabel("Spotify Management System");

        lblTitle.setForeground(Color.WHITE);

        lblTitle.setBounds(20, 10, 300, 30);

        contentPane.add(lblTitle);

        // USER INFO
        JLabel lblUser =
                new JLabel(
                        "Welcome: "
                        + currentUser.getFullName()
                        + " | Role: "
                        + currentUser.getRole()
                );

        lblUser.setForeground(Color.WHITE);

        lblUser.setBounds(20,40,350,30);

        contentPane.add(lblUser);

        // LOGOUT BUTTON
        JButton btnLogout =
                new JButton("Logout");

        btnLogout.setBounds(820,20,120,30);

        btnLogout.setBackground(
                new Color(255,80,80)
        );

        btnLogout.addActionListener(e -> {

            SessionManager.removeSession(
                    sessionToken
            );

            dispose();

            new LoginFrame()
                    .setVisible(true);
        });

        contentPane.add(btnLogout);

        // SEARCH TEXT
        txtSearch = new JTextField();

        txtSearch.setBounds(20, 80, 180, 30);

        contentPane.add(txtSearch);

        // REALTIME SEARCH
        txtSearch.getDocument()
                .addDocumentListener(

                new DocumentListener() {

            public void insertUpdate(
                    DocumentEvent e) {

                searchRealtime();
            }

            public void removeUpdate(
                    DocumentEvent e) {

                searchRealtime();
            }

            public void changedUpdate(
                    DocumentEvent e) {

                searchRealtime();
            }
        });

        // BUTTON SEARCH
        JButton btnSearch =
                new JButton("Search");

        btnSearch.setBackground(
                new Color(30,215,96)
        );

        btnSearch.setBounds(220, 80, 100, 30);

        btnSearch.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                searchRealtime();
            }
        });

        contentPane.add(btnSearch);

        // BUTTON LOAD
        JButton btnLoad =
                new JButton("Load Data");

        btnLoad.setBackground(
                new Color(30,215,96)
        );

        btnLoad.setBounds(340, 80, 120, 30);

        btnLoad.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                loadTable();
            }
        });

        contentPane.add(btnLoad);

        // BUTTON SORT
        JButton btnSort =
                new JButton("Top Popular");

        btnSort.setBounds(500,80,140,30);

        btnSort.setBackground(
                new Color(30,215,96)
        );

        btnSort.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                DefaultTableModel model =
                        (DefaultTableModel)
                                tableTracks.getModel();

                model.setRowCount(0);
                lblPage.setText(
                        "Page: " + currentPage
                );

                TrackDAO dao =
                		DAOFactory.getTrackDAO();

                for(Track t : dao.sortByPopularity()) {

                    Object[] row = {

                            t.getId(),
                            t.getName(),
                            t.getArtistName(),
                            t.getAlbumName(),
                            t.getGenre(),
                            t.getPopularity(),
                            t.getDuration()
                    };

                    model.addRow(row);
                }
            }
        });

        contentPane.add(btnSort);

        // FILTER GENRE
        cbGenre =
                new JComboBox<>();

        cbGenre.addItem("All");

        cbGenre.addItem("Pop");

        cbGenre.addItem("Romantic");

        cbGenre.addItem("Soul");

        cbGenre.addItem("Synth Pop");

        cbGenre.setBounds(660,80,140,30);

        contentPane.add(cbGenre);

        // BUTTON FILTER
        JButton btnFilter =
                new JButton("Filter");

        btnFilter.setBackground(
                new Color(30,215,96)
        );

        btnFilter.setBounds(820,80,120,30);

        btnFilter.addActionListener(e -> {

            String genre =
                    cbGenre.getSelectedItem()
                            .toString();

            filterGenre(genre);
        });

        contentPane.add(btnFilter);

        // TABLE
        tableTracks = new JTable();

        tableTracks.setAutoCreateRowSorter(true);

        tableTracks.setRowHeight(25);

        tableTracks.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseClicked(
                    MouseEvent e) {

                int row =
                        tableTracks.getSelectedRow();

                selectedId =
                        Integer.parseInt(
                                tableTracks.getValueAt(row,0)
                                        .toString()
                        );

                txtName.setText(
                        tableTracks.getValueAt(row,1)
                                .toString()
                );

                txtGenre.setText(
                        tableTracks.getValueAt(row,4)
                                .toString()
                );

                txtPopularity.setText(
                        tableTracks.getValueAt(row,5)
                                .toString()
                );

                txtDuration.setText(
                        tableTracks.getValueAt(row,6)
                                .toString()
                );
            }
        });

        tableTracks.setModel(
                new DefaultTableModel(

                        new Object[][] {
                        },

                        new String[] {
                                "ID",
                                "Name",
                                "Artist",
                                "Album",
                                "Genre",
                                "Popularity",
                                "Duration"
                        }
                )
        );

        // SCROLL
        JScrollPane scrollPane =
                new JScrollPane(tableTracks);

        scrollPane.setBounds(20, 130, 930, 250);

        contentPane.add(scrollPane);

        // LABEL NAME
        JLabel lblName =
                new JLabel("Track Name");

        lblName.setForeground(Color.WHITE);

        lblName.setBounds(20, 400, 100, 25);

        contentPane.add(lblName);

        // TXT NAME
        txtName = new JTextField();

        txtName.setBounds(120, 400, 180, 25);

        contentPane.add(txtName);

        // LABEL GENRE
        JLabel lblGenre =
                new JLabel("Genre");

        lblGenre.setForeground(Color.WHITE);

        lblGenre.setBounds(320, 400, 100, 25);

        contentPane.add(lblGenre);

        // TXT GENRE
        txtGenre = new JTextField();

        txtGenre.setBounds(420, 400, 120, 25);

        contentPane.add(txtGenre);

        // LABEL POPULARITY
        JLabel lblPopularity =
                new JLabel("Popularity");

        lblPopularity.setForeground(Color.WHITE);

        lblPopularity.setBounds(20, 450, 100, 25);

        contentPane.add(lblPopularity);

        // TXT POPULARITY
        txtPopularity = new JTextField();

        txtPopularity.setBounds(120, 450, 100, 25);

        contentPane.add(txtPopularity);

        // LABEL DURATION
        JLabel lblDuration =
                new JLabel("Duration");

        lblDuration.setForeground(Color.WHITE);

        lblDuration.setBounds(260, 450, 100, 25);

        contentPane.add(lblDuration);

        // TXT DURATION
        txtDuration = new JTextField();

        txtDuration.setBounds(340, 450, 180, 25);

        contentPane.add(txtDuration);

        // BUTTON ADD
        btnAdd =
                new JButton("Add Track");

        btnAdd.setBackground(
                new Color(30,215,96)
        );

        btnAdd.setBounds(700, 400, 180, 30);

        btnAdd.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                try {

                    String name =
                            txtName.getText();

                    String genre =
                            txtGenre.getText();

                    int popularity =
                            Integer.parseInt(
                                    txtPopularity.getText()
                            );

                    int duration =
                            Integer.parseInt(
                                    txtDuration.getText()
                            );

                    Track track =
                            new Track(
                                    name,
                                    popularity,
                                    duration
                            );

                    track.setGenre(genre);

                    TrackDAO dao =
                    		DAOFactory.getTrackDAO();

                    dao.add(track);

                    JOptionPane.showMessageDialog(
                            null,
                            "Added successfully!"
                    );

                    loadTable();

                    clearForm();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Please enter valid data!"
                    );
                }
            }
        });

        contentPane.add(btnAdd);

        // BUTTON UPDATE
        JButton btnUpdate =
                new JButton("Update");

        btnUpdate.setBackground(
                new Color(30,215,96)
        );

        btnUpdate.setBounds(700, 440, 180, 30);

        btnUpdate.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                try {

                    if(selectedId == -1) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please select a track!"
                        );

                        return;
                    }

                    String name =
                            txtName.getText();

                    String genre =
                            txtGenre.getText();

                    int popularity =
                            Integer.parseInt(
                                    txtPopularity.getText()
                            );

                    int duration =
                            Integer.parseInt(
                                    txtDuration.getText()
                            );

                    Track track =
                            new Track(
                                    name,
                                    popularity,
                                    duration
                            );

                    track.setGenre(genre);

                    track.setId(selectedId);

                    TrackDAO dao =
                    		DAOFactory.getTrackDAO();

                    dao.update(track);

                    JOptionPane.showMessageDialog(
                            null,
                            "Updated successfully!"
                    );

                    loadTable();

                    clearForm();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Update failed!"
                    );
                }
            }
        });

        contentPane.add(btnUpdate);

        // BUTTON DELETE
        JButton btnDelete =
                new JButton("Delete");

        btnDelete.setBackground(
                new Color(255,80,80)
        );

        btnDelete.setBounds(700, 480, 180, 30);

        btnDelete.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                try {

                    if(selectedId == -1) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Please select a track!"
                        );

                        return;
                    }

                    int confirm =
                            JOptionPane.showConfirmDialog(
                                    null,
                                    "Are you sure?"
                            );

                    if(confirm == 0) {

                        TrackDAO dao =
                        		DAOFactory.getTrackDAO();

                        dao.delete(selectedId);

                        JOptionPane.showMessageDialog(
                                null,
                                "Deleted successfully!"
                        );

                        loadTable();

                        clearForm();
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Delete failed!"
                    );
                }
            }
        });

        contentPane.add(btnDelete);

        // BUTTON PLAYLIST
        JButton btnPlaylist =
                new JButton("View Playlist");

        btnPlaylist.setBounds(20, 540, 180, 35);

        btnPlaylist.setBackground(
                new Color(30,215,96)
        );

        btnPlaylist.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                PlaylistDAO dao =
                        new PlaylistDAO();

                String message = "";

                for(Playlist p : dao.findAll()) {

                    message +=
                            p.getId()
                            + " - "
                            + p.getName()
                            + "\n";
                }

                JOptionPane.showMessageDialog(
                        null,
                        message
                );
            }
        });

        contentPane.add(btnPlaylist);

        // BUTTON CHART
        JButton btnChart =
                new JButton("Show Chart");

        btnChart.setBounds(240, 540, 180, 35);

        btnChart.setBackground(
                new Color(30,215,96)
        );

        btnChart.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                GenreChart chart =
                        new GenreChart();

                chart.setVisible(true);
            }
        });

        contentPane.add(btnChart);
        
        JButton btnBarChart =
                new JButton("Bar Chart");

        btnBarChart.setBounds(680, 540, 180, 35);

        btnBarChart.setBackground(
                new Color(30,215,96)
        );

        btnBarChart.addActionListener(e -> {

            new PopularityChart();
        });

        contentPane.add(btnBarChart);

        // BUTTON EXPORT
        btnExport =
                new JButton("Export Excel");

        btnExport.setBounds(460, 540, 180, 35);

        btnExport.setBackground(
                new Color(30,215,96)
        );

        btnExport.addActionListener(
                new java.awt.event.ActionListener() {

            public void actionPerformed(
                    java.awt.event.ActionEvent e) {

                try {

                    TrackDAO dao =
                    		DAOFactory.getTrackDAO();

                    ExcelExporter.exportTracks(
                            dao.findAll()
                    );

                    JOptionPane.showMessageDialog(
                            null,
                            "Export Excel Successfully!"
                    );

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Export Failed!"
                    );

                    ex.printStackTrace();
                }
            }
        });

        contentPane.add(btnExport);
     // BUTTON PREVIOUS
        JButton btnPrev =
                new JButton("Previous");

        btnPrev.setBounds(20,590,120,30);

        btnPrev.addActionListener(e -> {

            if(currentPage > 1) {

                currentPage--;

                loadTable();
            }
        });

        contentPane.add(btnPrev);
     // BUTTON NEXT
        JButton btnNext =
                new JButton("Next");

        btnNext.setBounds(160,590,120,30);

        btnNext.addActionListener(e -> {

            currentPage++;

            loadTable();
        });

        contentPane.add(btnNext);
     // PAGE LABEL
        lblPage =
                new JLabel("Page: 1");

        lblPage.setForeground(Color.WHITE);

        lblPage.setBounds(320,590,120,30);

        contentPane.add(lblPage);
     // ROLE-BASED ACCESS

        String role =
                currentUser.getRole();

        switch(role) {

            case "ADMIN":

                // FULL ACCESS

                break;

            case "STAFF":

                // STAFF cannot delete

                btnDelete.setEnabled(false);

                break;

            case "VIEWER":

                // READ ONLY

                btnAdd.setEnabled(false);

                btnUpdate.setEnabled(false);

                btnDelete.setEnabled(false);

                btnExport.setEnabled(false);

                btnPlaylist.setEnabled(false);

                break;
        }

        // AUTO LOAD
        loadTable();
    }

    // CLEAR FORM
    private void clearForm() {

        txtName.setText("");

        txtGenre.setText("");

        txtPopularity.setText("");

        txtDuration.setText("");

        selectedId = -1;
    }

    // LOAD TABLE WITH CONCURRENCY
    private void loadTable() {

        SwingWorker<Void, Track> worker =
                new SwingWorker<Void, Track>() {

            DefaultTableModel model =
                    (DefaultTableModel)
                            tableTracks.getModel();

            @Override
            protected Void doInBackground()
                    throws Exception {

                model.setRowCount(0);

                TrackDAO dao =
                        DAOFactory.getTrackDAO();

                for(Track t : dao.findAll(currentPage, pageSize)) {

                    publish(t);

                    Thread.sleep(50);
                }

                return null;
            }

            @Override
            protected void process(
                    java.util.List<Track> chunks
            ) {

                for(Track t : chunks) {

                    Object[] row = {

                            t.getId(),
                            t.getName(),
                            t.getArtistName(),
                            t.getAlbumName(),
                            t.getGenre(),
                            t.getPopularity(),
                            t.getDuration()
                    };

                    model.addRow(row);
                }
            }

            @Override
            protected void done() {

                JOptionPane.showMessageDialog(
                        null,
                        "Load Data Completed!"
                );
            }
        };

        worker.execute();
    }

    // FILTER GENRE
    private void filterGenre(
            String genre
    ) {

        DefaultTableModel model =
                (DefaultTableModel)
                        tableTracks.getModel();

        model.setRowCount(0);

        TrackDAO dao =
        		DAOFactory.getTrackDAO();

        for(Track t : dao.findAll()) {

            if(genre.equals("All")
                    || t.getGenre()
                    .equalsIgnoreCase(genre)) {

                Object[] row = {

                        t.getId(),
                        t.getName(),
                        t.getArtistName(),
                        t.getAlbumName(),
                        t.getGenre(),
                        t.getPopularity(),
                        t.getDuration()
                };

                model.addRow(row);
            }
        }
    }

    // REALTIME SEARCH
    private void searchRealtime() {

        DefaultTableModel model =
                (DefaultTableModel)
                        tableTracks.getModel();

        model.setRowCount(0);

        String keyword =
                txtSearch.getText();

        TrackDAO dao =
        		DAOFactory.getTrackDAO();

        String genre =
                cbGenre.getSelectedItem()
                        .toString();

        for(Track t : dao.search(keyword)) {

            if(genre.equals("All")
                    || t.getGenre()
                        .equalsIgnoreCase(genre)) {

            Object[] row = {

                    t.getId(),
                    t.getName(),
                    t.getArtistName(),
                    t.getAlbumName(),
                    t.getGenre(),
                    t.getPopularity(),
                    t.getDuration()
            };

            model.addRow(row);
        }
    }
    }
}