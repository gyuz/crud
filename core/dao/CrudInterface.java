package crud.core.dao;

import java.util.List;

public interface CrudInterface <T> {
    public void add(T);
    public void update(T);
    public void delete(T);
    public List<T> loadAll();
}
