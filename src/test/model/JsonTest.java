package model;

import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
    protected void checkPet(String name, int species, int age, int breed, int gender,
                            Date birthday, int colour, boolean vaccinated, boolean adopted,
                            Owner owner, Date lastVaccineDate, Pet pet) {
        assertEquals(name, pet.getName());
        assertEquals(species, pet.getSpecies());
        assertEquals(age, pet.getAge());
        assertEquals(breed, pet.getBreed());
        assertEquals(gender, pet.getGender());
        assertTrue(birthday.sameDate(pet.getBirthday()));
        assertEquals(colour, pet.getColour());
        assertTrue(vaccinated);
        assertEquals(adopted, pet.getAdoptionCondition());
        assertEquals(owner.getOwnerName(), pet.getOwner().getOwnerName());
        assertEquals(owner.getPhoneNumber(), pet.getOwner().getPhoneNumber());
        assertTrue(lastVaccineDate.sameDate(pet.getLastVaccineDate()));
    }
}
