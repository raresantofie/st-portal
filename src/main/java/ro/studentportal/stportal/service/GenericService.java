package ro.studentportal.stportal.service;

public interface GenericService<T, ID> {

    <S extends T> S save(S var1);
    <S extends T> S update(S var1);
    void delete(T entity);
    <S extends T> S findById(Long id);
}
