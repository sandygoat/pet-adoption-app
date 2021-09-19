package model;

import org.json.JSONObject;
import persistence.WritableForModel;

// Represents an pet owner with his/her name and phone number
public class Owner implements WritableForModel {
    public String name;
    public String phoneNumber;

    // REQUIRES: phoneNum is positive 10-digit-integer string: (XXX) XXX-XXXX
    // EFFECTS: creates an owner with given name and phone number
    public Owner(String name, String phoneNum) {
        this.name = name;
        this.phoneNumber = phoneNum;
    }

    // EFFECTS: returns name of the owner
    public String getOwnerName() {
        return name;
    }

    // EFFECTS: returns phone number of the owner
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Owner owner = (Owner) o;

        if (!name.equals(owner.name)) {
            return false;
        }
        return phoneNumber.equals(owner.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        return result;
    }

    // EFFECTS: write this into JSON format and return JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNumber", phoneNumber);
        return json;
    }
}
