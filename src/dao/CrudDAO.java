package dao;

import java.util.ArrayList;

public interface CrudDAO<T> {

    ArrayList<T> findAll();

    void add(T t);

    void update(T t);

    void delete(int id);
}