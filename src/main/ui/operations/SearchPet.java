package ui.operations;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPet extends Option {
    public SearchPet(PetAdoptionApp app, JComponent parent, String title) {
        super(app, parent, title);
    }

    // EFFECTS: add action listener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new SearchPetClickHandler());
    }

    private class SearchPetClickHandler implements ActionListener {

        // EFFECTS: if the button is clicked, call searchPetInList() method in app
        public void actionPerformed(ActionEvent e) {
            app.searchPetInList();
        }
    }
}
