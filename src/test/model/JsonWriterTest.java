package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            PetList petList = new PetList();

            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAdoptionList() {
        try {
            PetList petList = new PetList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAdoptionList.json");
            writer.open();
            writer.write(petList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAdoptionList.json");
            PetList petListRead = reader.read();
            assertEquals(0, petListRead.numberOfPets());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNonEmptyAdoptionList() {
        try {
            PetList petList = new PetList();

            Date date1 = new Date(10,14,2020);
            Date date2 = new Date(10,15,2014);
            Pet pet1 = new Pet("daenerys", 0,0,3,0,date1,4);
            Pet pet2 = new Pet("dolores", 1,0,0,1,date2,2);
            Owner owner1 = new Owner("ben", "1-778-456-7980");
            pet1.adopt(owner1);
            petList.addPet(pet1);
            petList.addPet(pet2);
            petList.updateAgeForAll(date1);
            petList.giveVaccineForAll(date1);

            JsonWriter writer = new JsonWriter("./data/testWriterNonEmptyAdoptionList.json");
            writer.open();
            writer.write(petList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderNonEmptyAdoptionList.json");
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

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
