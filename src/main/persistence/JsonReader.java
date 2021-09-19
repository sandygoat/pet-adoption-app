package persistence;

import model.Date;
import model.Owner;
import model.Pet;
import model.PetList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PetList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePetList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8);
        stream.forEach(s -> contentBuilder.append(s));

        return contentBuilder.toString();
    }

    // EFFECTS: parses adoptionList from JSON object and returns it
    private PetList parsePetList(JSONObject jsonObject) {
        PetList adoptionList = new PetList();
        addPets(adoptionList, jsonObject);
        return adoptionList;
    }

    // MODIFIES: adoptionList
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addPets(PetList adoptionList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("pets");
        for (Object json : jsonArray) {
            JSONObject nextPet = (JSONObject) json;
            addPet(adoptionList, nextPet);
        }
    }

    // MODIFIES: adoptionList
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addPet(PetList adoptionList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int species = jsonObject.getInt("species");
        int age = jsonObject.getInt("age");
        int breed = jsonObject.getInt("breed");
        int gender = jsonObject.getInt("gender");
        int colour = jsonObject.getInt("colour");
        boolean adopted = jsonObject.getBoolean("adoption condition");
        boolean vaccinated = jsonObject.getBoolean("vaccine condition");
        Date birthday = readDate("birthday", jsonObject);
        Date lastVaccineDate = readDate("last vaccine date", jsonObject);
        Owner owner = readOwner(jsonObject);

        Pet pet = new Pet(name, species, age, breed, gender, birthday, colour, vaccinated,
                adopted, owner, lastVaccineDate);
        adoptionList.addPet(pet);
    }

    // EFFECTS: parses Owner from JSON object and returns it
    private Owner readOwner(JSONObject jsonObject) {
        Owner owner;
        JSONObject jsonOwner = jsonObject.getJSONObject("owner");
        String name = jsonOwner.getString("name");
        String phoneNumber = jsonOwner.getString("phoneNumber");

        owner = new Owner(name, phoneNumber);
        return owner;
    }

    // EFFECTS: parses Date from JSON object and returns it
    private Date readDate(String keyword, JSONObject jsonObject) {
        Date date;
        JSONObject jsonDate = jsonObject.getJSONObject(keyword);
        int day = jsonDate.getInt("day");
        int month = jsonDate.getInt("month");
        int year = jsonDate.getInt("year");

        date = new Date(month, day, year);
        return date;
    }
}
