package eoin.appdevproj.service;

import eoin.appdevproj.model.Household;

import java.util.List;

public interface HouseholdService {
    int countTheHouseholds();
    Household findHouseholdByEircode(String eircode);
    List<Household> findHouseholds();
    int deleteHousehold(String eircode);
    Household addHousehold(String eircode, String address);
}
