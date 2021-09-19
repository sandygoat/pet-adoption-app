package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;

import java.util.List;

public class UpdateAgeForAll extends UpdateCondition {

    // EFFECTS: initialize a JFrame window with list panel and confirm button, overwriting dialog string messages,
    //          and to ask user to select a pet
    public UpdateAgeForAll(PetAdoptionApp app, List<Pet> petListOfInterest) {
        super(app, petListOfInterest);
        frameTitle = "Birthday Alert";
        dialogMessageForEmpty = "All pets' age have been updated!";
        dialogMessageNonEmpty = "Happy Birthday to:";
        dialogTitle = "Age updated Successfully";
        checkCondition(petListOfInterest);
    }

}
