package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.WritableForModel;

import java.util.ArrayList;
import java.util.List;

// Represent a list of pet in an adoption facility
public class PetList implements WritableForModel {

    private List<Pet> petList;

    // EFFECTS: creates a list of Pet objects
    public PetList() {
        petList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a Pet object to the list
    public void addPet(Pet pet) {
        petList.add(pet);
    }

    // EFFECTS: returns number of Pet objects in the list
    public int numberOfPets() {
        return petList.size();
    }

    // REQUIRES: pet list has a least (index + 1) objects
    // EFFECTS: returns the pet with given index in the list
    public Pet getPet(int index) {
        return petList.get(index);
    }

    // EFFECTS: returns the all pets in the list
    public List<Pet> getPetList() {
        return petList;
    }

    // REQUIRES: pet is an object in the pet list
    // EFFECTS: returns the index of pet object in the list
    public int getPetIndex(Pet pet) {
        return petList.indexOf(pet);
    }

    // MODIFIES: this
    // EFFECTS: if the list contains pet, removes pet from the list and returns true; returns false otherwise
    public boolean removePet(Pet pet) {
        if (petList.contains(pet)) {
            petList.remove(pet);
            return true;
        }
        return false;
    }

    // EFFECTS: returns a list of pet that have the same name as given
    public List<Pet> searchPetsByName(String name) {
        List<Pet> searchResults = new ArrayList<>();
        for (Pet p : petList) {
            if (p.getName().equals(name)) {
                searchResults.add(p);
            }
        }
        return searchResults;
    }

    // EFFECTS: returns a list of Pet which share the same or partial match of the features as the given pet
    public List<Pet> searchPetsByDescription(Pet pet) {
        List<Pet> searchResults = new ArrayList<>();
        for (Pet p : petList) {
            if (p.implicitMatchPet(pet)) {
                searchResults.add(p);
            }
        }
        return searchResults;
    }

    // REQUIRES: pet list has at least (index + 1) objects
    // MODIFIES: this
    // EFFECTS: adopts the (index + 1)th pet in the list and changes adoption condition to true,
    //          returns true if the pet has not been adopted yet, false otherwise
    public boolean adoptPet(int index, Owner owner) {
        return petList.get(index).adopt(owner);
    }



    // REQUIRES: pet list has a least (index + 1) objects
    //           pet with index has been adopted
    // EFFECTS: returns the owner of the adopted pet of index
    public Owner findOwner(int index) {
        return petList.get(index).getOwner();
    }

    // MODIFIES: this
    // EFFECTS: updates all pets' age in the list based on their birthday and today's date and return a list of pet's
    //          name that has birthday today
    public List<Pet> updateAgeForAll(Date today) {
        List<Pet> petsHaveBirthdayToday = new ArrayList<>();

        for (Pet p : petList) {
            p.updateAge(today);
            if (p.happyBirthday(today)) {
                petsHaveBirthdayToday.add(p);
            }
        }
        return petsHaveBirthdayToday;
    }

    // MODIFIES: this
    // EFFECTS: updates all pets' vaccine condition in the list based on their last vaccine date and today's date
    //          and returns a list of pet's name that had vaccinated today
    public List<Pet> giveVaccineForAll(Date today) {
        List<Pet> petsWithoutVaccine = new ArrayList<>();

        for (Pet p : petList) {
            if (!p.checkVaccine(today)) {
                petsWithoutVaccine.add(p);
                p.giveVaccine(today);
            }
        }
        return petsWithoutVaccine;
    }

    // EFFECTS: write this into JSON format and return JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("pets", petsToJson());
        return json;
    }


    // EFFECTS: returns pets in adoption list as a JSON array
    public JSONArray petsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Pet p : petList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
