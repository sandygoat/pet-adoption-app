package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

// ask user to select a pet from given list
public class SelectOnePet extends UpdateCondition {

    private List<PetAdoptionApp> safeBox = new ArrayList<>();

    // EFFECTS: initialize a JFrame window with list panel and confirm button to ask user to select a pet
    public SelectOnePet(PetAdoptionApp app, List<Pet> petListOfInterest) {
        super(app, petListOfInterest);
        safeBox.add(app);
        frameTitle = "Select a Pet";
        dialogMessageForEmpty = "No such pet can be found!";
        dialogMessageNonEmpty = "Select one from below:";
        dialogTitle = "Oops! Nothing found!";
        checkCondition(petListOfInterest);
    }

    // EFFECTS: when close button is hit, close window;
    //          otherwise, pass the selected pet and parent to new ActOnPet constructor
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Close".equals(e.getActionCommand())) {
            message.dispose();
        } else {
            int index = list.getSelectedIndex();
            message.dispose();
            new ActOnPet(petListOfInterest.get(index), safeBox.get(0));
        }
    }
}
