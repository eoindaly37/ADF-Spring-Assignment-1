package servicetests;

import eoin.appdevproj.model.Occupant;
import eoin.appdevproj.service.OccupantService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestOccupantService {
    @Autowired
    OccupantService occupantService;

    @Test
    @Order(1)
    public void testFindOccupantByIdExists(){
        Occupant occupant = occupantService.findOccupantById(1);
        assertEquals("Killian Rice", occupant.getName());
    }

    @Test
    @Order(2)
    public void testFindOccupantByIdDoesntExist(){
        Occupant occupant = occupantService.findOccupantById(20);
        assertEquals(occupant,null);
    }

    @Test
    @Order(3)
    public void testOccupantInsert(){
        String name = "Clodagh Daly";
        Occupant occupant = occupantService.addOccupant(name, 12, "Scholar", "V93");
        assertEquals(occupant.getName(),name);
    }
    @Test
    @Order(4)
    public void testOccupantInsertAlreadyThere(){
        Occupant occupant = occupantService.addOccupant("Eoin Daly", 12, "Scholar", "V93");
        assertEquals(occupant,null);
    }
}
