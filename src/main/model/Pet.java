package model;

import org.json.JSONObject;
import persistence.WritableForModel;

import java.util.Objects;

// Represents a pet having a name, type of species, age, breed, gender, birthday, colour, vaccine condition, adoption
// condition, and owner's information (if adopted)
public class Pet implements WritableForModel {
    private String name;           // name of the pet
    private int species;           // 0 for dog, 1 for cat
    private int age;               // age of the pet
    private int breed;             // breed of the pet represented by integer, see DecryptIntegerToString in ui package
    private int gender;            // 0 for female, 1 for male
    private Date birthday;         // birthday of the pet
    private int colour;            // coat colour of the pet
    private boolean vaccinated;    // false for new object
    private Date lastVaccineDate;  // Date of last vaccine
    private boolean adopted;       // false for new object
    private Owner owner;           // pet's owner if adopted

    // REQUIRES: all parameter integers are greater than -1. -1 for undefined information
    //           all integer parameter should fall into ranges indicated in DecryptIntegerToString class in ui package
    // EFFECTS: creates a pet with its name, type, age, breed, gender, birthday, colour, vaccine condition,
    //          and adoption condition
    public Pet(String name, int species, int age, int breed, int gender, Date birthday, int colour) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.birthday = birthday;
        this.colour = colour;
        this.vaccinated = false;
        this.adopted = false;
        this.owner = new Owner("N/A", "N/A");
        this.lastVaccineDate = new Date(1, 1,2000);    // a default date indicating no vaccine shot yet
    }

    // REQUIRES: all parameter integers are greater than -1. -1 for undefined information
    //           all integer parameter should fall into ranges indicated in DecryptIntegerToString class in ui package
    // EFFECTS: creates a pet with its name, type, age, breed, gender, birthday, colour, vaccine condition,
    //          and adoption condition
    public Pet(String name, int species, int age, int breed, int gender, Date birthday, int colour, boolean vaccinated,
               boolean adopted, Owner owner, Date lastVaccineDate) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.birthday = birthday;
        this.colour = colour;
        this.vaccinated = vaccinated;
        this.adopted = adopted;
        this.owner = owner;
        this.lastVaccineDate = lastVaccineDate;    // a default date indicating no vaccine shot yet
    }

    // EFFECTS: returns true if parameter pet is an implicit match (for all defined information) with the variable,
    //          false otherwise
    public boolean implicitMatchPet(Pet pet) {
        return ((species == pet.getSpecies()) | (pet.getSpecies() == -1))
                && ((age == pet.getAge()) | (pet.getAge() == -1))
                && ((breed == pet.getBreed()) | (pet.getBreed() == -1))
                && ((gender == pet.getGender()) | (pet.getGender() == -1))
                && ((colour == pet.getColour()) | (pet.getColour() == -1));
    }

    // MODIFIES: this
    // EFFECTS: updates pet's age according to today's date if applicable
    public void updateAge(Date today) {
        Date ageToDate = new Date(birthday.getMonth(), birthday.getDay(), birthday.getYear() + age);
        if (ageToDate.passOneYear(today)) {
            this.age = today.getYear() - birthday.getYear();
        }
    }

    // EFFECTS: returns true if today is the pet's birthday
    public boolean happyBirthday(Date today) {
        return birthday.sameMonthSameDay(today);
    }

    // MODIFIES: this
    // EFFECTS: changes field vaccinated to false if it has been more than one year since last vaccine was taken,
    //          then return vaccinated boolean value (true for has vaccine, false for vaccine not up to date)
    public boolean checkVaccine(Date today) {
        if (lastVaccineDate.passOneYear(today)) {
            vaccinated = false;
        }
        return vaccinated;
    }

    // REQUIRES: vaccinated field of the pet variable is false
    // MODIFIES: this
    // EFFECTS: gives vaccine to the pet by changing field vaccinated to true and modifies last vaccine date to today
    public void giveVaccine(Date today) {
        vaccinated = true;
        lastVaccineDate = today;
    }

    // MODIFIES: this
    // EFFECTS: if the pet is not yet adopted, changes field adopted to true, and changes field owner to given variable
    //          owner, then returns true; returns false if pet has already been adopted.
    public boolean adopt(Owner owner) {
        if (!adopted) {
            adopted = true;
            this.owner = owner;
            return true;
        }
        return false;
    }

    // EFFECTS: returns name of the pet
    public String getName() {
        return name;
    }

    // EFFECTS: returns species of the pet
    public int getSpecies() {
        return species;
    }

    // EFFECTS: returns age of the pet
    public int getAge() {
        return age;
    }

    // EFFECTS: returns breed of the pet
    public int getBreed() {
        return breed;
    }

    // EFFECTS: returns gender of the pet
    public int getGender() {
        return gender;
    }

    // EFFECTS: returns owner of the pet
    public Owner getOwner() {
        return owner;
    }

    // EFFECTS: returns colour of the pet
    public int getColour() {
        return colour;
    }

    // EFFECTS: returns birthday of the pet
    public Date getBirthday() {
        return birthday;
    }

    // EFFECTS: returns last vaccine date of the pet
    public Date getLastVaccineDate() {
        return lastVaccineDate;
    }

    // EFFECTS: returns true if pet has been adopted, false otherwise
    public boolean getAdoptionCondition() {
        return adopted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pet)) {
            return false;
        }
        Pet pet = (Pet) o;
        return (species == pet.species
                && age == pet.age
                && breed == pet.breed
                && gender == pet.gender
                && colour == pet.colour
                && vaccinated == pet.vaccinated
                && adopted == pet.adopted
                && name.equals(pet.name)
                && birthday.sameDate(pet.birthday)
                && lastVaccineDate.sameDate(pet.lastVaccineDate)
                && owner.equals(pet.owner));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, species, age, breed, gender,
                birthday, colour, vaccinated, lastVaccineDate, adopted, owner);
    }

    // EFFECTS: write this into JSON format and return JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("species", species);
        json.put("age", age);
        json.put("breed", breed);
        json.put("gender", gender);
        json.put("colour", colour);
        json.put("adoption condition", adopted);
        json.put("vaccine condition", vaccinated);
        json.put("owner", owner.toJson());
        json.put("birthday", birthday.toJson());
        json.put("last vaccine date", lastVaccineDate.toJson());

        return json;
    }
}
