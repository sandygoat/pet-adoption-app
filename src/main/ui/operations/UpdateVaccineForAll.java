package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;

import java.util.List;

public class UpdateVaccineForAll extends UpdateCondition {

    // EFFECTS: initialize a JFrame window with list panel and confirm button, overwriting dialog string messages,
    //          and to ask user to select a pet
    public UpdateVaccineForAll(PetAdoptionApp app, List<Pet> petListOfInterest) {
        super(app, petListOfInterest);
        frameTitle = "Vaccine Alert";
        dialogMessageForEmpty = "All pets' vaccine is up to date!";
        dialogMessageNonEmpty = "Vaccine shots are given to:";
        dialogTitle = "Vaccine updated Successfully";
        checkCondition(petListOfInterest);
    }
}
