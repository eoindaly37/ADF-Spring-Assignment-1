package eoin.appdevproj.service;

import eoin.appdevproj.dao.HouseholdDao;
import eoin.appdevproj.dao.OccupantDao;
import eoin.appdevproj.model.Occupant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupantServiceImp implements OccupantService{
    @Autowired
    OccupantDao occupantDao;
    @Autowired
    HouseholdDao householdDao;

    @Override
    public int countTheOccupants() {
        return occupantDao.getOccupantCount();
    }

    @Override
    public Occupant findOccupantById(int id) {
        return occupantDao.findOccupantById(id);
    }

    @Override
    public List<Occupant> findOccupantByEircode(String eircode) {
        return occupantDao.findOccupantByEircode(eircode);
    }

    @Override
    public List<Occupant> findOccupants() {
        return occupantDao.findAllOccupants();
    }

    @Override
    public int deleteOccupant(int id) {
        int numberDeleted = occupantDao.deleteOccupantById(id);
        if(numberDeleted == 0){
            System.out.println("There is no occupant with ID " + id + " in the database");
        }
        return numberDeleted;
    }

    @Override
    public int deleteOccupant(String name) {
        int numberDeleted = occupantDao.deleteOccupantByName(name);
        if(numberDeleted == 0){
            System.out.println("There is no occupant with name " + name + " in the database");
        }
        return numberDeleted;
    }

    @Override
    public int deleteOccupantEircode(String eircode) {
        int numberDeleted = occupantDao.deleteOccupantByEircode(eircode);
        if(numberDeleted == 0){
            System.out.println("There is no occupant with eircode " + eircode + " in the database");
        }
        return numberDeleted;
    }

    @Override
    public int changeOccupantName(String oldName, String newName) {
        if(! occupantDao.exists(oldName)){
            System.out.println("There is no occupant with the name " + oldName);
            return 0;
        }else if(occupantDao.exists(newName)){
            System.out.println("There is already an occupant with the name " + newName);
            return 0;
        }else{
            return occupantDao.changeOccupantName(oldName, newName);
        }
    }

    //Check to see if eircode exists
    @Override
    public int changeOccupantEircode(String name, String eircode) {
        if(! occupantDao.exists(name)){
            System.out.println("There is no occupant with the name " + name);
            return 0;
        }else{
            if(householdDao.exists(eircode)){
                return occupantDao.changeOccupantEircode(name, eircode);
            }else{
                System.out.println("There is no eircode " + eircode);
                return 0;
            }
        }
    }

    //added a check to see if eircode exists
    @Override
    public Occupant addOccupant(String name, int age, String occupation, String eircode) {
        if (! occupantDao.exists(name)) {
            if(householdDao.exists(eircode)){
                return occupantDao.findOccupantById(occupantDao.addOccupant(name, age, occupation, eircode));
            }else{
                System.out.println("No such eircode");
                return null;
            }
        }
        System.out.println("There is already an occupant with the name " + name);
        return null;
    }

    @Override
    public int averageAge() {
        return occupantDao.getAverageAge();
    }

    @Override
    public int numberStudents() {
        return occupantDao.getNumberStudents();
    }

    @Override
    public int numberOAP() {
        return occupantDao.getNumberOAPs();
    }

}
