package sn.javafx_diti4_2026.repository.interfaceRepo;

import java.util.List;

public interface IInterface<T> {

    void insert(T t);
    void update(T t);
    void delete(int id);
    T findById(int id);
    List<T> findAll();
}