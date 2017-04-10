package Domain;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class Proba extends  UniqueById<Integer> {

    Integer _distanta;
    String _stil;
    Integer _nr_participanti;

    public Proba(Integer _distanta, String _stil, Integer _nr_participanti) {
        this._distanta = _distanta;
        this._stil = _stil;
        this._nr_participanti = _nr_participanti;
    }

    public Integer get_distanta() {
        return _distanta;
    }

    public void set_distanta(Integer _distanta) {
        this._distanta = _distanta;
    }

    public String get_stil() {
        return _stil;
    }

    public void set_stil(String _stil) {
        this._stil = _stil;
    }

    public Integer get_nr_participanti() {
        return _nr_participanti;
    }

    public void set_nr_participanti(Integer _nr_participanti) {
        this._nr_participanti = _nr_participanti;
    }
}
