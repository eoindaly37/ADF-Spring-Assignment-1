package eoin.appdevproj.service;

import eoin.appdevproj.model.Occupant;

import java.util.List;

public interface OccupantService {
    int countTheOccupants();
    Occupant findOccupantById(int id);
    List<Occupant> findOccupantByEircode(String eircode);
    List<Occupant> findOccupants();
    int deleteOccupant(int id);
    int deleteOccupant(String name);
    int deleteOccupantEircode(String eircode);
    int changeOccupantName(String oldName, String newName);
    int changeOccupantEircode(String name, String eircode);
    Occupant addOccupant(String name, int age, String occupation, String eircode);
    int averageAge();
    int numberStudents();
    int numberOAP();
}
