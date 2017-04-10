package Controller;

import Domain.*;
import Repozitory.*;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Test on 10/23/2016.
 */
public class Controller {
    ParticipantRepo participantRep;
    ProbaRepo probaRepo;
    ProgramareRepo programareRepo;
    AdminRepo adminRepo ;
    public Controller() {
        participantRep = new ParticipantRepo();
        probaRepo = new ProbaRepo();
        programareRepo = new ProgramareRepo();
        adminRepo = new AdminRepo();
    }

    public void add_proba(Proba x) {
            probaRepo.add(x);

    }

    public void add_admin(Admin x)
    {
            adminRepo.add(x);

    }

    public void add_participant(Participant x)
    {
            participantRep.add(x);

    }

    public void add_programare(Programare x)
    {
            programareRepo.add(x);
    }


    public void Update_proba(Proba x)
    {
            probaRepo.update(x);

    }

    public void Update_admin(Admin x)
    {
            adminRepo.update(x);

    }
    public void update_participant(Participant x)
    {
            participantRep.update(x);

    }

    public void update_programare(Programare x)
    {
            programareRepo.update(x);
    }


    public void delete_proba(Proba x)
    {
            programareRepo.delete(x.get_id());

    }

    public void delete_participant(Participant x)
    {
            participantRep.delete(x.get_id());

    }

    public void delete_programare(Programare x)
    {
            programareRepo.delete(x.get_id());
    }

    public void delete_admin(Admin x)
    {
            adminRepo.delete(x);

    }


    public List<Proba> get_all_probe()
    {
        return probaRepo.getAll();
    }

    public List<Programare> get_all_programari()
    {
        return programareRepo.getAll();
    }
    public List<Participant> get_all_participanti()
    {
        return participantRep.getAll();
    }


    public boolean check_user(String s, String s1) throws Exception {
       return adminRepo.check_user(s,s1);
    }


    public List<Participant> get_all_participant_inscrisi(String s1, String s2){
        return adminRepo.get_random(s1,s2);
    }

    public void add_part_proba(String nume, int varsta , String Stil, int distanta) throws Exception {
        programareRepo.add_part_proba(nume,varsta,Stil,distanta);
    }
}
