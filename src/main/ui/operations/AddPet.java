package ui.operations;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// add new pet to parent's adoption list
public class AddPet extends Option {

    public AddPet(PetAdoptionApp app, JComponent parent, String title) {
        super(app, parent, title);
    }

    // EFFECTS: add action listener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new AddPetClickHandler());
    }


    private class AddPetClickHandler implements ActionListener {

        // EFFECTS: if the button is clicked, call addPetToList() method in app
        public void actionPerformed(ActionEvent e) {
            app.addPetToList();
        }
    }
}
