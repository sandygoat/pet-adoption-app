package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PetList petList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAdoptionList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAdoptionList.json");
        try {
            PetList petList = reader.read();
            assertEquals(0, petList.numberOfPets());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNonEmptyAdoptionList() {
        JsonReader reader = new JsonReader("./data/testReaderNonEmptyAdoptionList.json");
        Date date1 = new Date(10,14,2020);
        Date date2 = new Date(10,15,2014);
        Owner owner1 = new Owner("ben", "1-778-456-7980");
        Owner owner2 = new Owner("N/A", "N/A");

        try {
            PetList petlist = reader.read();
            assertEquals(2, petlist.numberOfPets());
            checkPet("daenerys", 0, 0, 3, 0, date1, 4, true,
                    true, owner1, date1, petlist.getPet(0));
            checkPet("dolores", 1, 6, 0, 1, date2, 2, true,
                    false, owner2, date1, petlist.getPet(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
