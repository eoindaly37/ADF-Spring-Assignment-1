package eoin.appdevproj.dao;

import eoin.appdevproj.model.Household;
import eoin.appdevproj.rowmapper.HouseholdRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class HouseholdDaoMySql implements HouseholdDao{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int getHouseholdCount() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM household", Integer.class);
    }

    @Override
    public boolean exists(String eircode) {
        return 1 == jdbcTemplate.queryForObject("SELECT COUNT(1) FROM household WHERE household.eircode = ?", new Object[]{eircode}, Integer.class);
    }

    @Override
    public Household findHouseholdByEircode(String eircode) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM household WHERE household.eircode = ?", new Object[]{eircode}, new HouseholdRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Household> findAllHouseholds() {
        try {
            return jdbcTemplate.query("SELECT * FROM household ", new HouseholdRowMapper());
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public int deleteHouseholdByEircode(String eircode) {
        final String SQL = "DELETE FROM household WHERE household.eircode = ?";
        return jdbcTemplate.update(SQL, new Object[]{eircode});
    }

    @Override
    public String addHousehold(String eircode, String address) {
        final String INSERT_SQL = "INSERT INTO household(eircode, address)  VALUES (?,?)";
        jdbcTemplate.update(INSERT_SQL, new Object[] {eircode, address});
        return eircode;

//        KeyHolder KeyHolder = new GeneratedKeyHolder();
//
//        jdbcTemplate.update(
//                new PreparedStatementCreator() {
//                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException
//                    {
//                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id"});
//                        ps.setString(1, eircode);
//                        ps.setString(2, address);
//                        return ps;
//                    }
//                }, KeyHolder);
//
//        return KeyHolder.getKey().intValue();
    }
}
