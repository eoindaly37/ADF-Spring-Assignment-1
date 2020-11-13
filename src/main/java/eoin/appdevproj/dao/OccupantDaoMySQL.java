package eoin.appdevproj.dao;

import eoin.appdevproj.model.Occupant;
import eoin.appdevproj.rowmapper.OccupantRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OccupantDaoMySQL  implements OccupantDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int getOccupantCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM occupant", Integer.class);
    }

    @Override
    public int getAverageAge() {
        return jdbcTemplate.queryForObject("SELECT AVG(age) FROM occupant", Integer.class);
    }

    @Override
    public int getNumberStudents() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM occupant WHERE occupation='Scholar'", Integer.class);
    }

    @Override
    public int getNumberOAPs() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM occupant WHERE age>=65", Integer.class);
    }

    @Override
    public boolean exists(String name) {
        return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM occupant WHERE occupant.name = ?", new Object[]{name}, Integer.class);
    }

    @Override
    public Occupant findOccupantById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM occupant WHERE occupant.id = ?", new Object[]{id}, new OccupantRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Occupant> findOccupantByEircode(String eircode) {
        try {
            return jdbcTemplate.query("SELECT * FROM occupant WHERE occupant.eircode = ?", new Object[]{eircode}, new OccupantRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Occupant> findAllOccupants() {
        try {
            return jdbcTemplate.query("SELECT * FROM occupant ", new OccupantRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteOccupantById(int id) {
        final String SQL = "DELETE FROM occupant WHERE occupant.id = ?";
        return jdbcTemplate.update(SQL, new Object[]{id});
    }

    @Override
    public int deleteOccupantByName(String name) {
        final String SQL = "DELETE FROM occupant WHERE occupant.name = ?";
        return jdbcTemplate.update(SQL, new Object[]{name});
    }

    @Override
    public int deleteOccupantByEircode(String eircode) {
        final String SQL = "DELETE FROM occupant WHERE occupant.eircode = ?";
        return jdbcTemplate.update(SQL, new Object[]{eircode});
    }

    @Override
    public int changeOccupantName(String oldName, String newName) {
        final String SQL = "UPDATE occupant set occupant.name = ? WHERE occupant.name = ?";
        int numberChanged = jdbcTemplate.update(SQL, new Object[]{newName, oldName});
        if (numberChanged == 0) {
            System.out.println("Failed to change the occupant name, perhaps the occupant " + oldName + " doesn't exist ?");
        }
        return numberChanged;
    }

    @Override
    public int changeOccupantEircode(String name, String eircode) {
        final String SQL = "UPDATE occupant set occupant.eircode = ? WHERE occupant.name = ?";
        int numberChanged = jdbcTemplate.update(SQL, new Object[]{eircode, name});
        if (numberChanged == 0) {
            System.out.println("Failed to change the occupant eircode, perhaps the occupant " + name + " doesn't exist ?");
        }
        return numberChanged;
    }

    @Override
    public int addOccupant(final String name, final int age, final String occupation, final String eircode) {
        final String INSERT_SQL = "INSERT INTO occupant(name, age, occupation, eircode)  VALUES (?,?,?,?)";
        KeyHolder KeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
                    {
                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
                        ps.setString(1, name);
                        ps.setInt(2, age);
                        ps.setString(3,occupation);
                        ps.setString(4,eircode);
                        return ps;
                    }
                }, KeyHolder);

        return KeyHolder.getKey().intValue();
    }
}
