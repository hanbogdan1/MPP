package Repozitory;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Test on 10/22/2016.
 */
public interface iRepo<T,ID> {
    void add(T item);
    void delete(ID key);
    void update(T newItem);
    List<T> getAll();
}
