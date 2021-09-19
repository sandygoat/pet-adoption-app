package ui.operations;

import ui.PetAdoptionApp;

import javax.swing.*;

// creates option buttons for the main menu
public abstract class Option {
    protected JButton button;
    protected PetAdoptionApp app;
    protected boolean active;

    // EFFECTS: construct a button, then add that button to parent JComponent and add a listener
    public Option(PetAdoptionApp app, JComponent parent, String title) {
        this.app = app;
        createButton(parent, title);
        addToParent(parent);
        active = false;
        addListener();
    }

    // EFFECTS: creates button
    protected void createButton(JComponent parent, String title) {
        button = new JButton(title);
        addToParent(parent);
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    protected abstract void addListener();

}
