package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;
import ui.constructions.EnterOwner;

import javax.swing.*;

// do operations on the selected pet
public class ActOnPet extends JFrame {

    private JFrame message;

    // EFFECTS: creates a message dialog asking users to choose the operation they want to do on pet in
    //          petAdoptionApp's adoption list
    public ActOnPet(Pet pet, PetAdoptionApp petAdoptionApp) {
        message = new JFrame("Update Information");
        String[] options = {"Remove", "Adopt", "View Information"};
        int choice = JOptionPane.showOptionDialog(message, "What would you like to do with "
                        + pet.getName() + "?", "Update Information",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
        readChoices(pet, petAdoptionApp, choice);
    }

    // MODIFIES: petAdoptionApp
    // EFFECTS: if user choose view information, print out the information;
    //          if user choose adopt, adopt pet in petAdoptionApp's adoption list, then show a message dialog
    //          indicating successfully adopted or the pet has been previously adopted (cannot adopt);
    //          if user choose remove, remove pet in petAdoptionApp's adoption list, then show a message dialog
    //          indicating successfully removed
    private void readChoices(Pet pet, PetAdoptionApp petAdoptionApp, int choice) {
        if (choice == 2) {
            DecryptIntegerToString petInformation = new DecryptIntegerToString();
            petInformation.printInformation(pet);
        } else if (choice == 1) {
            if (pet.getAdoptionCondition()) {
                JOptionPane.showMessageDialog(message, pet.getName() + " has already been adopted.",
                        "Cannot Adopt pet", JOptionPane.INFORMATION_MESSAGE);
            } else {
                new EnterOwner(pet, petAdoptionApp);
            }
        } else {
            petAdoptionApp.adoptionList.removePet(pet);
            JOptionPane.showMessageDialog(message, pet.getName() + " has been removed from the adoption list.",
                    "Remove Successfully", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
