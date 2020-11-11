package eoin.appdevproj.service;

import eoin.appdevproj.dao.HouseholdDao;
import eoin.appdevproj.dao.OccupantDao;
import eoin.appdevproj.model.Household;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdServiceImp implements HouseholdService{
    @Autowired
    HouseholdDao householdDao;
    @Autowired
    OccupantDao occupantDao;

    @Override
    public int countTheHouseholds() {
        return householdDao.getHouseholdCount();
    }

    @Override
    public Household findHouseholdByEircode(String eircode) {
        return householdDao.findHouseholdByEircode(eircode);
    }

    @Override
    public List<Household> findHouseholds() {
        return householdDao.findAllHouseholds();
    }

    @Override
    public int deleteHousehold(String eircode) {
        System.out.println(occupantDao.deleteOccupantByEircode(eircode) + " occupants of a house with that eircode have been deleted");
        int numberDeleted = householdDao.deleteHouseholdByEircode(eircode);
        if(numberDeleted == 0){
            System.out.println("There is no household with ID " + eircode + " in the database");
        }
        return numberDeleted;
    }

    @Override
    public Household addHousehold(String eircode, String address) {
        if (! householdDao.exists(eircode)) {
            return householdDao.findHouseholdByEircode(householdDao.addHousehold(eircode, address));
        }
        System.out.println("There is already an occupant with the eircode " + eircode);
        return null;
    }
}
