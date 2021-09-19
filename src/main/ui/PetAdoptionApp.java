package ui;

import model.Pet;
import model.PetList;
import model.Date;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.constructions.UserInputs;
import ui.operations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.NO_OPTION;


public class PetAdoptionApp extends JFrame implements WindowListener, MouseListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 750;
    private static final String JSON_STORE = "./data/adoptionList.json";
    public PetList adoptionList;
    private JFrame mainWindow = new JFrame();
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);

    // EFFECTS: runs the pet adoption application
    public PetAdoptionApp() {
        super("Pet Adoption Application");
        initializeFields();
        initializeGraphics();
    }

    // EFFECTS: initialize graphics display
    public void initializeGraphics() {
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        displayMenu();
        mainWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes adoption list and main window listener
    public void initializeFields() {
        adoptionList = new PetList();
        mainWindow.addWindowListener(this);
    }

    // EFFECTS: displays main menu to user
    public void displayMenu() {
        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(new GridLayout(0,1));
        JLabel background = new JLabel(createImageIcon("constructions/images/MainMenuBackground.jpg"));
        mainWindow.add(background, BorderLayout.PAGE_START);
        mainWindow.add(mainMenu, BorderLayout.SOUTH);


        new LoadFile(this, mainMenu, "Load existing adoption list");
        new AddPet(this, mainMenu, "Add a new pet for adoption");
        new UpdateInformation(this, mainMenu, "Update pet information");
        new SearchPet(this, mainMenu, "Search for pet");
        new SaveFile(this, mainMenu, "Overwrite and save adoption list");
    }

    // EFFECTS: ask user to save current adoption list to JSON file when quiting
    public void askToSave() {
        int userChoice;
        JFrame message = new JFrame("How would you like to exit?");
        userChoice = JOptionPane.showConfirmDialog(message, "Do you want to save current adoption list?",
                "How would you like to exit?", JOptionPane.YES_NO_CANCEL_OPTION);

        if (userChoice == YES_OPTION) {
            saveAdoptionList();
            System.exit(0);
        } else if (userChoice == NO_OPTION) {
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads existing adoption list from file
    public void loadAdoptionList() {
        JFrame message = new JFrame("Log Message");
        try {
            adoptionList = jsonReader.read();
            JOptionPane.showMessageDialog(message, "Loaded adoption list from " + JSON_STORE,
                    "List Loaded Successfully", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(message, "Unable to read from file: " + JSON_STORE,
                    "List Loaded Unsuccessfully", JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: saves the adoption list to file
    public void saveAdoptionList() {
        JFrame message = new JFrame("Log message");
        try {
            jsonWriter.open();
            jsonWriter.write(adoptionList);
            jsonWriter.close();
            JOptionPane.showMessageDialog(message, "The adoption list has been saved.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(message, "The adoption list has not been saved.");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a pet to adoption list
    public void addPetToList() {
        UserInputs userInputs = new UserInputs();
        userInputs.makeAPet(this);
    }

    // MODIFIES: this
    // EFFECTS: prompts user whether to update pets' age or vaccine information if applicable
    public void updatePetInformation() {
        JFrame message = new JFrame("Update Information");
        String[] options = {"Cancel", "Update Vaccine", "Update Age"};
        int choice = JOptionPane.showOptionDialog(message, "What information would you like to update?",
                "Update Information",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[2]);

        LocalDate now = LocalDate.now();
        Date today = new Date(now.getMonthValue(), now.getDayOfMonth(), now.getYear());

        if (choice == 2) {
            new UpdateAgeForAll(this, adoptionList.updateAgeForAll(today));
        } else if (choice == 1) {
            new UpdateVaccineForAll(this, adoptionList.giveVaccineForAll(today));
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose whether search for pets by name or description
    public void searchPetInList() {
        JFrame message = new JFrame("How would you like to search the pet?");
        String[] options = {"Cancel", "Search by description", "Search by name", "View all"};
        int choice = JOptionPane.showOptionDialog(message, "How would you like to search the pet?",
                "Search a Pet",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, options, options[3]);
        UserInputs userInputs = new UserInputs();
        if (choice == 3) {
            new SelectOnePet(this, adoptionList.getPetList());
        } else if (choice == 2) {
            new SearchByName(this);
        } else if (choice == 1) {
            userInputs.madeUpPet(this);
        }
    }

    // Returns an ImageIcon, or null if the path was invalid.
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = PetAdoptionApp.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        askToSave();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
