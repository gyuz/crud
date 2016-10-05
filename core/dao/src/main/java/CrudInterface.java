package crud.core.dao;

import java.util.List;

public interface CrudInterface <T> {
    public void add(T t);
    public void update(int idx, T t);
    public void delete(int idx, T t);
    public List<T> loadAll();
}
