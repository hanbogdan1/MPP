package Domain;

/**
 * Created by Bogdan on 23-Mar-17.
 */
public class Programare extends UniqueById<Integer> {
    Integer _id_participant;
    Integer _id_proba;

    public Programare(Integer _id_participant, Integer _id_proba) {
        this._id_participant = _id_participant;
        this._id_proba = _id_proba;
    }

    public Integer get_id_participant() {
        return _id_participant;
    }

    public void set_id_participant(Integer _id_participant) {
        this._id_participant = _id_participant;
    }

    public Integer get_id_proba() {
        return _id_proba;
    }

    public void set_id_proba(Integer _id_proba) {
        this._id_proba = _id_proba;
    }
}
