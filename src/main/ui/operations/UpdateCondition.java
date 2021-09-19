package ui.operations;

import model.Pet;
import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// ask user to select a pet from a given list
public abstract class UpdateCondition extends JDialog implements ActionListener {
    protected PetAdoptionApp app;
    protected List<Pet> petListOfInterest;
    protected JFrame message;
    protected JButton select;
    protected JButton close;
    protected JList list;
    protected String frameTitle;
    protected String dialogMessageForEmpty;
    protected String dialogMessageNonEmpty;
    protected String dialogTitle;

    // EFFECTS: initialize a JFrame window with list panel and confirm button
    public UpdateCondition(PetAdoptionApp app, List<Pet> petListOfInterest) {
        this.app = app;
        this.petListOfInterest = petListOfInterest;
        message = new JFrame();
        message.setVisible(true);
        message.setLayout(new BorderLayout());
        message.setMinimumSize(new Dimension(200, 200));
        message.setLocationRelativeTo(null);
        select = new JButton("Select");
        select.addActionListener(this);
        select.setActionCommand("Select");
        close = new JButton("Close");
        close.addActionListener(this);
        close.setActionCommand("Close");
    }

    // EFFECTS: if petListOfInterest is empty, display a dialog message;
    //          otherwise, creates a JFrame display with JScrollPane and buttons
    protected void checkCondition(List<Pet> petListOfInterest) {
        if (petListOfInterest.size() == 0) {
            JOptionPane.showMessageDialog(message, dialogMessageForEmpty,
                    dialogTitle, JOptionPane.INFORMATION_MESSAGE);
            message.dispose();
        } else {
            JScrollPane listScrollPane = generateListPane(petListOfInterest);
            JPanel buttonPane = generateButtonPanel();
            JLabel openingLine = new JLabel(dialogMessageNonEmpty);
            listScrollPane.setMaximumSize(new Dimension(50, 50));
            message.setTitle(frameTitle);
            message.add(openingLine, BorderLayout.PAGE_START);
            message.add(listScrollPane, BorderLayout.CENTER);
            message.add(buttonPane, BorderLayout.PAGE_END);
        }
    }

    // EFFECTS: create a JPanel for buttons
    protected JPanel generateButtonPanel() {
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(close);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(select);
        return buttonPane;
    }

    // EFFECTS: create a JScrollPane for displaying a pet list
    protected JScrollPane generateListPane(List<Pet> petListOfInterest) {
        DefaultListModel listModel = new DefaultListModel();
        for (Pet p : petListOfInterest) {
            listModel.addElement(p.getName());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.setVisibleRowCount(20);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }

    // EFFECTS: when close button is hit, close message window
    //          otherwise, print selected pet information
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Close".equals(e.getActionCommand())) {
            message.dispose();
        } else {
            int index = list.getSelectedIndex();
            DecryptIntegerToString decryption = new DecryptIntegerToString();
            decryption.printInformation(petListOfInterest.get(index));
        }
    }
}
