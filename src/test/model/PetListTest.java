package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetListTest {

    private PetList adoptionList;
    private Pet mayo;
    private Pet TT;
    private static final Date DEFAULT_BD = new Date(9,2, 2018);

    @BeforeEach
    void runBefore() {
        adoptionList = new PetList();
        mayo = new Pet("mayo", 0, 1, 1, 0, DEFAULT_BD, 0);
        TT = new Pet("TT", 1, 20, 25, 1, DEFAULT_BD, 5);
    }

    @Test
    public void testGetPetList() {
        adoptionList.addPet(mayo);
        adoptionList.addPet(TT);
        assertEquals(mayo, adoptionList.getPetList().get(0));
        assertEquals(TT, adoptionList.getPetList().get(1));
    }

    @Test
    public void testAddPet() {
        adoptionList.addPet(mayo);
        assertEquals(1,adoptionList.numberOfPets());
        assertEquals("mayo",adoptionList.getPet(adoptionList.getPetIndex(mayo)).getName());
    }

    @Test
    public void testRemovePetFalse() {
        // empty list should return false
        assertFalse(adoptionList.removePet(TT));
        assertEquals(0,adoptionList.numberOfPets());

        // pet not found in the list should return false
        adoptionList.addPet(mayo);
        assertFalse(adoptionList.removePet(TT));
        assertEquals(1,adoptionList.numberOfPets());
        assertEquals(mayo, adoptionList.getPet(0));
        assertFalse(TT.equals(adoptionList.getPet(0)));
    }

    @Test
    public void testRemovePetTrue() {
        adoptionList.addPet(mayo);
        adoptionList.addPet(TT);

        assertEquals(2,adoptionList.numberOfPets());
        assertTrue(adoptionList.removePet(TT));
        assertEquals(1,adoptionList.numberOfPets());
        assertEquals("mayo",adoptionList.getPet(0).getName());
    }

    @Test
    public void testSearchPetByNameEmpty() {
        // empty list returns empty list
        assertEquals(0, adoptionList.searchPetsByName("mayo").size());

        // no match pet returns empty list
        Pet andy = new Pet("andy", 1, 1, 1, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, 2, 1, 0, DEFAULT_BD, 0);

        adoptionList.addPet(andy);
        adoptionList.addPet(mango);

        assertEquals(0, adoptionList.searchPetsByName("mayo").size());
    }

    @Test
    public void testSearchPetByNameMatch() {
        Pet mango = new Pet("mango", 0, 2, 1, 0, DEFAULT_BD, 0);

        adoptionList.addPet(mayo);
        adoptionList.addPet(mango);

        assertEquals(1, adoptionList.searchPetsByName("mayo").size());
    }


    @Test
    public void testSearchPetsByDescriptionEmpty() {
        // empty list returns empty list
        assertEquals(0, adoptionList.searchPetsByDescription(mayo).size());

        // no match pet returns empty list
        Pet andy = new Pet("andy", 1, 1, 1, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, 2, 1, 0, DEFAULT_BD, 0);
        Pet timo = new Pet("timo", 0, 1, 4, 0, DEFAULT_BD, 0);
        Pet jax = new Pet("jax", 0, 1, 1, 1, DEFAULT_BD, 0);
        Pet nash = new Pet("nash", 0, 1, 1, 0, DEFAULT_BD, 5);

        adoptionList.addPet(andy);
        adoptionList.addPet(mango);
        adoptionList.addPet(timo);
        adoptionList.addPet(jax);
        adoptionList.addPet(nash);

        assertEquals(0, adoptionList.searchPetsByDescription(mayo).size());
    }

    @Test
    public void testSearchPetsByDescriptionMatch() {
        Pet andy = new Pet("andy", -1, 1, 1, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, -1, 1, 0, DEFAULT_BD, 0);
        Pet timo = new Pet("timo", 0, 1, -1, 0, DEFAULT_BD, 0);
        Pet jax = new Pet("jax", 0, 1, 1, -1, DEFAULT_BD, 0);
        Pet nash = new Pet("nash", 0, 1, 1, 0, DEFAULT_BD, -1);
        Pet uzi = new Pet("uzi", 0, 1, 1, 0, DEFAULT_BD, 0);

        adoptionList.addPet(mayo);

        assertEquals(1, adoptionList.searchPetsByDescription(andy).size());
        assertEquals(1, adoptionList.searchPetsByDescription(mango).size());
        assertEquals(1, adoptionList.searchPetsByDescription(timo).size());
        assertEquals(1, adoptionList.searchPetsByDescription(jax).size());
        assertEquals(1, adoptionList.searchPetsByDescription(nash).size());
        assertEquals(1, adoptionList.searchPetsByDescription(uzi).size());
    }

    @Test
    public void testAdoptPetTrue() {
        Owner owner = new Owner("ariel", "1-778-456-7890");

        adoptionList.addPet(mayo);
        assertTrue(adoptionList.adoptPet(0, owner));
        assertTrue(adoptionList.getPet(0).getAdoptionCondition());

        Owner ownerOfMayo = adoptionList.findOwner(0);
        assertEquals(owner.getOwnerName(), ownerOfMayo.getOwnerName());
        assertEquals(owner.getPhoneNumber(), ownerOfMayo.getPhoneNumber());
    }

    @Test
    public void testAdoptPetFalse() {
        Owner owner = new Owner("ariel", "1-778-456-7890");

        adoptionList.addPet(mayo);
        adoptionList.adoptPet(0, owner);
        assertFalse(adoptionList.adoptPet(0, owner));
        assertTrue(adoptionList.getPet(0).getAdoptionCondition());

        Owner ownerOfMayo = adoptionList.findOwner(0);
        assertEquals(owner.getOwnerName(), ownerOfMayo.getOwnerName());
        assertEquals(owner.getPhoneNumber(), ownerOfMayo.getPhoneNumber());
    }

    @Test
    public void testUpdateAgeForAll() {
        Date today = new Date(9,2,2020);
        Date andyBirthday = new Date(10,10,2018);
        Date mangoBirthday = new Date(8,31,2017);
        Pet andy = new Pet("andy", 1, 1, 1, 0, andyBirthday, 0);
        Pet mango = new Pet("mango", 0, 2, 1, 0, mangoBirthday, 0);


        adoptionList.addPet(mayo);
        adoptionList.addPet(andy);
        adoptionList.addPet(mango);

        List<Pet> birthdayBaby = adoptionList.updateAgeForAll(today);
        assertEquals(1, birthdayBaby.size());
        assertEquals(mayo, birthdayBaby.get(0));
        assertEquals(2, adoptionList.getPet(0).getAge());
        assertEquals(1, adoptionList.getPet(1).getAge());
        assertEquals(3, adoptionList.getPet(2).getAge());
    }

    @Test
    public void testGiveVaccineForAll() {
        Date today = new Date(9,2,2020);
        Date andyLastVaccineDate = new Date(8,20,2019);
        Date mangoLastVaccineDate = new Date(12,20,2019);
        Pet andy = new Pet("andy", 1, 1, 1, 0, DEFAULT_BD, 0);
        Pet mango = new Pet("mango", 0, 2, 1, 0, DEFAULT_BD, 0);
        List<Pet> petsNeedVaccineToday;

        andy.giveVaccine(andyLastVaccineDate);
        mango.giveVaccine(mangoLastVaccineDate);
        adoptionList.addPet(mayo);
        adoptionList.addPet(andy);
        adoptionList.addPet(mango);
        petsNeedVaccineToday = adoptionList.giveVaccineForAll(today);

        // Test if return list is correct
        assertEquals(2, petsNeedVaccineToday.size());
        assertEquals(mayo,petsNeedVaccineToday.get(0));
        assertEquals(andy,petsNeedVaccineToday.get(1));

        // Test if vaccine condition is true for all
        assertTrue(adoptionList.getPet(0).checkVaccine(today));
        assertTrue(adoptionList.getPet(1).checkVaccine(today));
        assertTrue(adoptionList.getPet(2).checkVaccine(today));

        // Test if vaccine date is updated for those have vaccine shots today
        // and if vaccine date remains the same for those do not need vaccine today
        assertTrue(adoptionList.getPet(0).getLastVaccineDate().sameDate(today));
        assertTrue(adoptionList.getPet(1).getLastVaccineDate().sameDate(today));
        assertTrue(adoptionList.getPet(2).getLastVaccineDate().sameDate(mangoLastVaccineDate));
    }





}