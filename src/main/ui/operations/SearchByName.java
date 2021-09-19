package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// search all pets that match given name in parent's adoption list
public class SearchByName extends JFrame implements ActionListener {

    private String name;
    private JFrame window;
    private JLabel nameLabel;
    private static final String NAME_LABEL = "Enter name: ";
    private JTextField nameField;
    private JButton confirm;
    private List<PetAdoptionApp> safeBox = new ArrayList<>();
    private List<Pet> nameMatches = new ArrayList<>();

    // EFFECTS: create a JFrame window with text field and confirm button to ask users to enter the pet name they
    //          want to search for
    public SearchByName(PetAdoptionApp parent) {
        safeBox.add(parent);
        window = new JFrame("Enter Pet's Name");
        window.setVisible(true);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
        window.setMinimumSize(new Dimension(300, 100));
        window.setLocationRelativeTo(null);
        JPanel textFieldPane = new JPanel();
        nameField = new JTextField(20);
        nameField.addActionListener(this);
        nameLabel = new JLabel(NAME_LABEL);
        textFieldPane.add(nameLabel);
        textFieldPane.add(nameField);
        window.add(textFieldPane);
        confirm = new JButton("Confirm");
        confirm.setActionCommand("confirm");
        confirm.addActionListener(this);
        window.add(confirm);
    }

    // EFFECTS: when confirm button is hit, find all pets matches given name in parent's adoption list and create a
    //          list of pets with matched names;
    //          pass list and parent to new SelectOnePet constructor
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            name = nameField.getText();
            window.dispose();
            nameMatches = safeBox.get(0).adoptionList.searchPetsByName(name);
            new SelectOnePet(safeBox.get(0), nameMatches);
        }
    }
}
