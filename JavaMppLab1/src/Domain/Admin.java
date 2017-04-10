package Domain;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class Admin extends UniqueById<Integer> {
    String _username;
    String _nume;
    String _parola;

    public Admin(String _username, String _nume, String _parola) {
        this._username = _username;
        this._nume = _nume;
        this._parola = _parola;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_nume() {
        return _nume;
    }

    public void set_nume(String _nume) {
        this._nume = _nume;
    }

    public String get_parola() {
        return _parola;
    }

    public void set_parola(String _parola) {
        this._parola = _parola;
    }
}
