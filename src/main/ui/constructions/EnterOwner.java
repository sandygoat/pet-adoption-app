package ui.constructions;

import model.Owner;
import model.Pet;
import ui.PetAdoptionApp;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

// prompt user to enter owner's information when adopting a pet
public class EnterOwner extends JFrame implements ActionListener {

    private JFrame window;

    private JLabel nameLabel;
    private JLabel phoneLabel;

    private static final String NAME_LABEL = "Enter name: ";
    private static final String PHONE_LABEL = "Enter Phone Number: ";

    private JTextField nameField;
    private JFormattedTextField phoneNumField;

    private MaskFormatter phoneFormat;

    private JPanel namePane;
    private JPanel phonePane;
    private JButton confirm;

    private List<Pet> petBox = new ArrayList<>();
    private List<PetAdoptionApp> parentBox = new ArrayList<>();

    // EFFECTS: construct an EnterOwner class, initialize a JFrame window and prompt user to enter owner's information
    public EnterOwner(Pet pet, PetAdoptionApp parent) {
        petBox.add(pet);
        parentBox.add(parent);
        window = new JFrame("Owner Information");
        window.setVisible(true);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
        window.setMinimumSize(new Dimension(400, 200));
        window.setLocationRelativeTo(null);
        namePane = createNamePane();
        phonePane = createPhonePane();
        createDisplay();
    }

    // EFFECTS: create a formatted text field for user to enter phone number and returns it
    private JPanel createPhonePane() {
        JPanel phonePane = new JPanel();
        try {
            phoneFormat = new MaskFormatter("(###) ###-####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        phoneNumField = new JFormattedTextField(phoneFormat);
        phoneNumField.setColumns(8);
        phoneNumField.addActionListener(this);
        phoneLabel = new JLabel(PHONE_LABEL);
        phonePane.add(phoneLabel);
        phonePane.add(phoneNumField);
        return phonePane;
    }

    // EFFECTS: create a text field for user to enter name and returns it
    private JPanel createNamePane() {
        JPanel namePane = new JPanel();
        nameField = new JTextField(8);
        nameField.addActionListener(this);
        nameLabel = new JLabel(NAME_LABEL);
        namePane.add(nameLabel);
        namePane.add(nameField);
        return namePane;
    }

    // EFFECTS: create a graphic display for user to enter owner's information
    private void createDisplay() {
        JPanel inputPanel = new JPanel();
        inputPanel.add(namePane);
        inputPanel.add(phonePane);
        window.add(inputPanel);
        confirm = new JButton("Confirm");
        confirm.setActionCommand("confirm");
        confirm.addActionListener(this);
        window.add(confirm);
    }

    // MODIFIES: parent
    // EFFECTS: when confirm button is clicked, create an owner based on information that user entered; and assign
    //          this owner to the pet in parent's adoption list.
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("confirm".equals(e.getActionCommand())) {
            Owner owner = new Owner(nameField.getText(),
                    phoneNumField.getText()
                            .replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)-$2-$3"));
            parentBox.get(0).adoptionList.adoptPet(parentBox.get(0).adoptionList.getPetIndex(petBox.get(0)), owner);
            JOptionPane.showMessageDialog(window, "Congratulation! " + petBox.get(0).getName()
                            + " has been adopted by " + owner.getOwnerName(),
                    "Adopt Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
            window.dispose();
        }
    }
}
