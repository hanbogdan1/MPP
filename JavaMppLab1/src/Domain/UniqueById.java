package Domain;

import java.io.Serializable;

/**
 * Created by Test on 10/22/2016.
 */
public abstract class UniqueById<T> implements Serializable {
    protected T _id;
    public T get_id(){
        return _id;
    }

    public void  set_id( T id){
        _id=id;
    }
}
