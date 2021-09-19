package ui.constructions;

import ui.PetAdoptionApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Create a JFrame window with JComboBox components
public abstract class ComboBoxChooser extends JFrame implements ActionListener {

    protected JFrame window;
    protected int maxNumber;
    protected int minNumber;
    protected String type;
    protected String keyword;
    protected String frameTitle;
    protected JButton confirm;
    protected JLabel openingLine;
    protected String[] range;
    protected JComboBox chooseBox;
    protected List<PetAdoptionApp> safeBox = new ArrayList<>();

    // REQUIRES: keyword is one of "random" and "defined"
    // EFFECTS: construct a ComboBoxChooser object, initialize JFrame window and create an array of options
    public ComboBoxChooser(int maxNumber, int minNumber, String type, String keyword, PetAdoptionApp parent) {
        this.keyword = keyword;
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
        this.type = type;
        this.frameTitle = "Enter Birthday";
        safeBox.add(parent);
        int[] intRange = java.util.stream.IntStream.rangeClosed(minNumber, maxNumber).toArray();
        range = new String[intRange.length];
        for (int i = 0; i < intRange.length; i++) {
            range[i] = String.valueOf(intRange[i]);
        }
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: display a window with JComboBox and other components on it
    protected void displayComboBox() {
        openingLine = new JLabel("Select " + type + ":");
        window = new JFrame(frameTitle);
        chooseBox = new JComboBox(range);
        chooseBox.addActionListener(this);
        window.setVisible(true);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
        window.setMinimumSize(new Dimension(300, 100));
        window.setLocationRelativeTo(null);
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setActionCommand("confirm");
        openingLine = new JLabel("Select " + type + ":");
        JPanel chooseBoxPane = new JPanel();
        chooseBoxPane.add(openingLine);
        chooseBoxPane.add(chooseBox);
        window.add(chooseBoxPane);
        window.add(confirm);

    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
