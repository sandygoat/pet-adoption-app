package ui.operations;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFile extends Option {
    public SaveFile(PetAdoptionApp app, JComponent parent, String title) {
        super(app, parent, title);
    }

    // EFFECTS: add action listener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new SaveFileClickHandler());
    }

    private class SaveFileClickHandler implements ActionListener {

        // EFFECTS: if the button is clicked, call saveAdoptionList() method in app
        public void actionPerformed(ActionEvent e) {
            app.saveAdoptionList();
        }
    }
}
