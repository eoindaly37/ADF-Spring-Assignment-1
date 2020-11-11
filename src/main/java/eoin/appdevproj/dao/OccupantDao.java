package eoin.appdevproj.dao;

import eoin.appdevproj.model.Occupant;

import java.util.List;

public interface OccupantDao {

    int getOccupantCount();
    boolean exists(String name);
    Occupant findOccupantById(int id);
    List<Occupant> findOccupantByEircode(String eircode);
    List<Occupant> findAllOccupants();
    int deleteOccupantById(int id);
    int deleteOccupantByName(String name);
    int deleteOccupantByEircode(String eircode);
    int changeOccupantName(String oldName, String newName);
    int changeOccupantEircode(String name, String eircode);
    int addOccupant(String name, int age, String occupation, String eircode);
}
