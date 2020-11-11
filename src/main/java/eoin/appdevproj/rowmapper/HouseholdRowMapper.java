package eoin.appdevproj.rowmapper;

import eoin.appdevproj.model.Household;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseholdRowMapper implements RowMapper<Household> {
    @Override
    public Household mapRow(ResultSet resultSet, int i) throws SQLException {
        Household household = new Household();
        household.setEircode(resultSet.getString("eircode"));
        household.setAddress(resultSet.getString("address"));
        return household;
    }
}
