package eoin.appdevproj.dao;

import eoin.appdevproj.model.Household;

import java.util.List;

public interface HouseholdDao {
    int getHouseholdCount();
    boolean exists(String eircode);
    Household findHouseholdByEircode(String eircode);
    List<Household> findAllHouseholds();
    int deleteHouseholdByEircode(String eircode);
    String addHousehold(String eircode, String address);
}
