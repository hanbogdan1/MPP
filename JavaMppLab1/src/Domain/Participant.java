package Domain;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class Participant extends UniqueById<Integer> {
    private String _nume;
    private String _varsta;

    public Participant(String _nume, String _varsta) {
        this._nume = _nume;
        this._varsta = _varsta;
    }

    public String get_nume() {
        return _nume;
    }

    public void set_nume(String _nume) {
        this._nume = _nume;
    }

    public String get_varsta() {
        return _varsta;
    }

    public void set_varsta(String _varsta) {
        this._varsta = _varsta;
    }
}
