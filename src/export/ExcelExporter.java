package export;

import model.Track;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

import java.util.List;

public class ExcelExporter {

    public static void exportTracks(
            List<Track> tracks
    ) {

        try {

            Workbook workbook =
                    new XSSFWorkbook();

            Sheet sheet =
                    workbook.createSheet(
                            "Tracks"
                    );

            // HEADER
            Row header =
                    sheet.createRow(0);

            header.createCell(0)
                    .setCellValue("ID");

            header.createCell(1)
                    .setCellValue("Track Name");

            header.createCell(2)
                    .setCellValue("Artist");

            header.createCell(3)
                    .setCellValue("Album");

            header.createCell(4)
                    .setCellValue("Popularity");

            header.createCell(5)
                    .setCellValue("Duration");

            // DATA
            int rowNum = 1;

            for(Track t : tracks) {

                Row row =
                        sheet.createRow(rowNum++);

                row.createCell(0)
                        .setCellValue(t.getId());

                row.createCell(1)
                        .setCellValue(t.getName());

                row.createCell(2)
                        .setCellValue(
                                t.getArtistName()
                        );

                row.createCell(3)
                        .setCellValue(
                                t.getAlbumName()
                        );

                row.createCell(4)
                        .setCellValue(
                                t.getPopularity()
                        );

                row.createCell(5)
                        .setCellValue(
                                t.getDuration()
                        );
            }

            // AUTO SIZE
            for(int i = 0; i < 6; i++) {

                sheet.autoSizeColumn(i);
            }

            // SAVE
            FileOutputStream fos =
                    new FileOutputStream(
                            "tracks.xlsx"
                    );

            workbook.write(fos);

            workbook.close();

            fos.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}