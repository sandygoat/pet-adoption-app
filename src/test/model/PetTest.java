package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PetTest {

    private Pet mayo;
    private static final Date DEFAULT_BD = new Date(9,2, 2018);
    private static final Owner ARIEL = new Owner("ariel", "1-778-456-7890");
    private static final Owner NO_ONE = new Owner("N/A", "N/A");
    private static final Date DEFAULT_DAY = new Date(1, 1,2000);


    @BeforeEach
    void runBefore() {
        mayo = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
    }

    @Test
    public void testEqualsHashCode() {
        Pet pet = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Date date = new Date(2, 2, 2010);
        Pet differentName = new Pet("TT", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentSpecies = new Pet("mayo", 1, 1, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentBreed = new Pet("mayo", 0, 1, 5, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentAge = new Pet("mayo", 0, 5, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentGender = new Pet("mayo", 0, 1, 1, 1, DEFAULT_BD, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentBD = new Pet("mayo", 0, 1, 1, 0, date, 0,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentColour = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 7,
                false, false, NO_ONE, DEFAULT_DAY);
        Pet differentVac = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                true, false, NO_ONE, DEFAULT_DAY);
        Pet differentVacDate= new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, false, NO_ONE, date);
        Pet differentAdp = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, true, NO_ONE, DEFAULT_DAY);
        Pet differentOwner = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0,
                false, false, ARIEL, DEFAULT_DAY);


        assertEquals(mayo, pet);
        assertEquals(mayo, mayo);
        assertFalse(mayo.equals(date));
        assertNotEquals(differentName, mayo);
        assertNotEquals(differentAdp, mayo);
        assertNotEquals(differentAge, mayo);
        assertNotEquals(differentBD, mayo);
        assertNotEquals(differentBreed, mayo);
        assertNotEquals(differentColour, mayo);
        assertNotEquals(differentGender, mayo);
        assertNotEquals(differentOwner, mayo);
        assertNotEquals(differentSpecies, mayo);
        assertNotEquals(differentVac, mayo);
        assertNotEquals(differentVacDate, mayo);
        assertEquals(pet.hashCode(), mayo.hashCode());
    }

    @Test
    public void testImplicitMatchPetFalse() {
        Pet andy = new Pet("andy", 1, 1, 20, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, 2, 1, 0, DEFAULT_BD, 0);
        Pet timo = new Pet("timo", 0, 1, 4, 0, DEFAULT_BD, 0);
        Pet jax = new Pet("jax", 0, 1, 1, 1, DEFAULT_BD, 0);
        Pet nash = new Pet("nash", 0, 1, 1, 0, DEFAULT_BD, 5);

        assertFalse(mayo.implicitMatchPet(andy));
        assertFalse(mayo.implicitMatchPet(mango));
        assertFalse(mayo.implicitMatchPet(timo));
        assertFalse(mayo.implicitMatchPet(jax));
        assertFalse(mayo.implicitMatchPet(nash));
    }

    @Test
    public void testImplicitMatchPetTrue() {
        Pet andy = new Pet("andy", -1, 1, 1, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, -1, 1, 0, DEFAULT_BD, 0);
        Pet timo = new Pet("timo", 0, 1, -1, 0, DEFAULT_BD, 0);
        Pet jax = new Pet("jax", 0, 1, 1, -1, DEFAULT_BD, 0);
        Pet nash = new Pet("nash", 0, 1, 1, 0, DEFAULT_BD, -1);
        Pet uzi = new Pet("uzi", 0, 1, 1, 0, DEFAULT_BD, 0);

        assertTrue(mayo.implicitMatchPet(andy));
        assertTrue(mayo.implicitMatchPet(mango));
        assertTrue(mayo.implicitMatchPet(timo));
        assertTrue(mayo.implicitMatchPet(jax));
        assertTrue(mayo.implicitMatchPet(nash));
        assertTrue(mayo.implicitMatchPet(uzi));
    }

    @Test
    public void testUpdateAgeNoChange() {
        Date today = new Date(12,25,2019);
        mayo.updateAge(today);
        assertEquals(1, mayo.getAge());
    }

    @Test
    public void testUpdateAgeHasChange() {
        Date today = new Date(12,25,2020);
        mayo.updateAge(today);
        assertEquals(2, mayo.getAge());
    }

    @Test
    public void testHappyBirthday() {
        Date someday = new Date(12,25,2020);
        Date birthday = new Date(9,2,2020);

        assertTrue(mayo.happyBirthday(birthday));
        assertFalse(mayo.happyBirthday(someday));
    }

    @Test
    public void testCheckVaccineFalse() {
        // case that field vaccinated is false
        Date today = new Date(8,9,2019);
        assertFalse(mayo.checkVaccine(today));

        // case that vaccine expired
        mayo.giveVaccine(today);
        Date someday = new Date(10,11,2020);
        assertFalse(mayo.checkVaccine(someday));
    }

    @Test
    public void testCheckVaccineTrue() {
        Date today = new Date(8,9,2019);
        mayo.giveVaccine(today);
        Date someday = new Date(10,11,2019);

        assertTrue(mayo.checkVaccine(someday));
    }

    @Test
    public void testGiveVaccine() {
        Date today = new Date(8,9,2019);
        mayo.giveVaccine(today);

        assertTrue(mayo.checkVaccine(today));
        assertTrue(mayo.getLastVaccineDate().sameDate(today));
    }

    @Test
    public void testAdoptTrue() {

        // check whether mayo can be adopted; adopted = false should return true
        assertTrue(mayo.adopt(ARIEL));
        assertEquals("ariel", mayo.getOwner().getOwnerName());
        assertEquals("1-778-456-7890", mayo.getOwner().getPhoneNumber());
        assertTrue(mayo.getAdoptionCondition());
    }

    @Test
    public void testAdoptFalse() {
        mayo.adopt(ARIEL);

        assertFalse(mayo.adopt(ARIEL));
        assertTrue(mayo.getAdoptionCondition());
        assertEquals("ariel", mayo.getOwner().getOwnerName());
        assertEquals("1-778-456-7890", mayo.getOwner().getPhoneNumber());
    }

    @Test
    public void testGetName() {
        assertEquals("mayo", mayo.getName());
    }

    @Test
    public void testGetBirthday() {
        assertTrue(mayo.getBirthday().sameDate(DEFAULT_BD));
    }
}
