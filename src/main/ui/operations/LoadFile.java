package ui.operations;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFile extends Option {

    public LoadFile(PetAdoptionApp app, JComponent parent, String title) {
        super(app, parent, title);
    }

    // EFFECTS: add action listener to this button
    @Override
    protected void addListener() {
        button.addActionListener(new LoadFileClickHandler());
    }

    private class LoadFileClickHandler implements ActionListener {

        // EFFECTS: if the button is clicked, call loadAdoptionList() method in app
        public void actionPerformed(ActionEvent e) {
            app.loadAdoptionList();
        }
    }

}
