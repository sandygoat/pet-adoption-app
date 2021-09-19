package persistence;

import org.json.JSONObject;

public interface WritableForModel {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
