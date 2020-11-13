package daotests;

import eoin.appdevproj.dao.OccupantDao;
import eoin.appdevproj.model.Occupant;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOccupantDao {
    @Autowired
    OccupantDao occupantDao;

    @Test
    @Order(1)
    public void testCountOccupants(){
        int count = occupantDao.getOccupantCount();
        assertEquals(4,count);
    }

    @Test
    public void testAvgAge(){
        int avg = occupantDao.getAverageAge();
        assertEquals(32,avg);
    }

    @Test
    public void testNumberStudents(){
        int total = occupantDao.getNumberStudents();
        assertEquals(2,total);
    }

    @Test
    public void testNumberOAPs(){
        int total = occupantDao.getNumberOAPs();
        assertEquals(1,total);
    }

    @Test
    @Order(2)
    public void testFindOccupantByEircode(){
        List<Occupant> occupants = occupantDao.findOccupantByEircode("V93");
        for(Occupant occupant:occupants){
            assertEquals("V93",occupant.getEircode());
        }
    }

    @Test
    @Order(3)
    public void testAddOccupant(){
        String name = "Clodagh Daly";
        int newId = occupantDao.addOccupant(name,12,"Scholar","V93");
        Occupant clo = occupantDao.findOccupantById(newId);
        assertEquals(name,clo.getName());
    }

}
