package eoin.appdevproj.rowmapper;

import eoin.appdevproj.model.Occupant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OccupantRowMapper implements RowMapper<Occupant> {
    @Override
    public Occupant mapRow(ResultSet resultSet, int i) throws SQLException {
        Occupant occupant = new Occupant();
        occupant.setId(resultSet.getInt("id"));
        occupant.setName(resultSet.getString("name"));
        occupant.setAge(resultSet.getInt("age"));
        occupant.setOccupation(resultSet.getString("occupation"));
        occupant.setEircode(resultSet.getString("eircode"));
        return occupant;
    }
}
