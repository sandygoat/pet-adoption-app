package ui.constructions;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Create a JFrame window with assorted JButtons components
public abstract class ChooseButton extends JFrame implements ActionListener {

    protected JFrame window;
    protected int numberOfButton;
    protected List<JButton> listOfButton;
    protected String[] listOfButtonTitle;
    protected String type;
    protected String keyword;
    protected List<PetAdoptionApp> safeBox = new ArrayList<>();

    // REQUIRES: keyword is one of "random" and "defined"
    // EFFECTS: construct a ChooseButton object, initialize JFrame window and create a list of JButton
    public ChooseButton(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
        this.numberOfButton = listOfButtonTitle.length;
        this.listOfButtonTitle = listOfButtonTitle;
        this.keyword = keyword;
        safeBox.add(parent);
        listOfButton = new ArrayList<>();
        window = new JFrame("Choose One of the Following Options to Describe the Pet");
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        for (int i = 0; i < numberOfButton; i++) {
            JButton button = new JButton(listOfButtonTitle[i],
                    createImageIcon("images/"
                            + listOfButtonTitle[i].replaceAll(" ", "_") + ".jpeg"));
            button.setVerticalTextPosition(AbstractButton.BOTTOM);
            button.setHorizontalTextPosition(AbstractButton.CENTER);
            button.setActionCommand(Integer.toString(i));
            button.addActionListener(this);
            listOfButton.add(button);
        }
    }

    // REQUIRES: listOfButton is not empty
    // MODIFIES: this
    // EFFECTS: display a window with all applicable JButtons on it
    protected void createButtonLayout(List<JButton> listOfButton) {
        JPanel buttonPane = new JPanel();
        buttonPane.setVisible(true);
        JLabel openingLine = new JLabel("Choose " + type + " from below:");
        JButton notApplicable = new JButton("Not Applicable");
        notApplicable.setVisible(true);
        notApplicable.addActionListener(this);
        notApplicable.setActionCommand("-1");
        if (numberOfButton == 9) {
            buttonPane.setLayout(new GridLayout(3, 3));
        }
        if (numberOfButton == 2) {
            buttonPane.setLayout(new GridLayout(1, 2));
        }
        for (JButton b : listOfButton) {
            buttonPane.add(b);
        }
        window.add(openingLine, BorderLayout.PAGE_START);
        window.add(buttonPane, BorderLayout.CENTER);
        window.add(notApplicable, BorderLayout.PAGE_END);
    }

    // REQUIRES: valid path
    // EFFECTS: create an ImageIcon and return it
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ChooseButton.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
