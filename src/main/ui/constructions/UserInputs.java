package ui.constructions;

import model.Date;
import model.Owner;
import model.Pet;
import ui.PetAdoptionApp;

import ui.operations.DecryptIntegerToString;
import ui.operations.SelectOnePet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

// processes and prompts user input and modifies data in parent PetAdoptionApp
public class UserInputs {

    public String name;
    public int species;
    public int age;
    public int breed;
    public int gender;
    public int colour;
    private Date birthday;
    public int birthdayDay;
    public int birthdayMonth;
    public int birthdayYear;
    public Owner owner;
    public Pet pet;

    private static final String[] DOG_BREEDS = {"American Eskimo", "Golden Retriever", "German Shepherd", "Shiba Inu",
            "Poodle", "Chihuahua", "Labrador Retriever", "French Bulldog", "Other Dog"};
    private static final String[] CAT_BREEDS = {"Siamese", "Persian", "Maine Coon", "Ragdoll",
            "Bengal", "Abyssinian", "Birman", "British Shorthair", "Other Cat"};
    private static final String[] GENDERS = {"Female", "Male"};
    private static final String[] SPECIES = {"Dog", "Cat"};
    private static final String[] COLOURS = {"White", "Black", "Brown", "Gold",
            "Grey", "Cream", "Yellow", "Mixed", "Other Color"};

    // EFFECTS: creates a user input
    public UserInputs() {
    }


    // EFFECTS: creates a real pet in parent's adoption list based on user's complete input by starting with EnterName
    public void makeAPet(PetAdoptionApp parent) {
        new EnterName("defined", parent);
    }

    // EFFECTS: creates a made-up pet based on user's implicit input by starting with ChooseAge
    public void madeUpPet(PetAdoptionApp parent) {
        new ChooseAge(30, -1, "Age","random", parent);
    }

    // prompt user to enter name of the pet
    public class EnterName extends JFrame implements ActionListener {

        private JFrame window;
        private JLabel nameLabel;
        private static final String NAME_LABEL = "Enter name: ";
        private JTextField nameField;
        private JButton confirm;
        private String keyword;
        private List<PetAdoptionApp> safeBox = new ArrayList<>();

        // EFFECTS: construct an EnterName Class, create JFrame window for user to enter information
        public EnterName(String keyword, PetAdoptionApp parent) {
            this.keyword = keyword;
            safeBox.add(parent);
            window = new JFrame("Enter Pet's Name");
            window.setVisible(true);
            window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.PAGE_AXIS));
            window.setMinimumSize(new Dimension(300, 100));
            window.setLocationRelativeTo(null);
            JPanel textFieldPane = new JPanel();
            nameField = new JTextField(8);
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

        // MODIFIES: this.name in outer class
        // EFFECTS: when confirm button is hit, modify name based on user's input, then close window and call a
        //          ChooseAge constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("confirm".equals(e.getActionCommand())) {
                name = nameField.getText();
                window.dispose();
                new ChooseAge(30, 0, "Age", keyword, safeBox.get(0));
            }
        }
    }

    public class ChooseAge extends ComboBoxChooser {

        // EFFECTS: construct an ChooseAge Class, create JFrame window for user to enter information
        public ChooseAge(int maxNumber, int minNumber, String type, String keyword, PetAdoptionApp parent) {
            super(maxNumber, minNumber, type, keyword, parent);
            if (keyword == "random") {
                this.type = "Age, choose -1 if not applicable";
            }
            this.frameTitle = "Enter Age";
            displayComboBox();
        }

        // MODIFIES: this.age in outer class
        // EFFECTS: when confirm button is hit, modify age based on user's input, then close window and call a
        //          ChooseSpecies constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("confirm".equals(e.getActionCommand())) {
                age = Integer.parseInt((String)chooseBox.getSelectedItem());
                window.dispose();
                new ChooseSpecies(SPECIES, keyword, safeBox.get(0));
            }
        }
    }

    public class ChooseSpecies extends ChooseButton {

        // EFFECTS: construct an ChooseSpecies Class, create JFrame window for user to enter information
        public ChooseSpecies(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
            super(listOfButtonTitle, keyword, parent);
            type = "Species";
            createButtonLayout(listOfButton);
        }

        // MODIFIES: this.species in outer class
        // EFFECTS: when a button is hit, modify species based on user's input, then close window and
        //          call a ChooseDogBreed constructor if species == 0
        //          call a ChooseCatBreed constructor if species == 1
        //          set breed = -1 and then call a ChooseGender constructor if species == -1
        @Override
        public void actionPerformed(ActionEvent e) {
            species = Integer.parseInt(e.getActionCommand());
            window.dispose();
            if (species == 0) {
                new ChooseDogBreed(DOG_BREEDS, keyword, safeBox.get(0));
            } else if (species == 1) {
                new ChooseCatBreed(CAT_BREEDS, keyword, safeBox.get(0));
            } else {
                breed = -1;
                new ChooseGender(GENDERS, keyword, safeBox.get(0));
            }

        }
    }

    public class ChooseDogBreed extends ChooseButton {

        // EFFECTS: construct an ChooseDogBreed Class, create JFrame window for user to enter information
        public ChooseDogBreed(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
            super(listOfButtonTitle, keyword, parent);
            type = "Breed";
            createButtonLayout(listOfButton);
        }

        // MODIFIES: this.breed in outer class
        // EFFECTS: when a button is hit, modify breed based on user's input, then close window and call a
        //          ChooseGender constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            breed = Integer.parseInt(e.getActionCommand());
            window.dispose();
            new ChooseGender(GENDERS, keyword, safeBox.get(0));
        }
    }

    public class ChooseCatBreed extends ChooseButton {

        // EFFECTS: construct an ChooseCatBreed Class, create JFrame window for user to enter information
        public ChooseCatBreed(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
            super(listOfButtonTitle, keyword, parent);
            type = "Breed";
            createButtonLayout(listOfButton);
        }

        // MODIFIES: this.breed in outer class
        // EFFECTS: when a button is hit, modify breed based on user's input, then close window and call a
        //          ChooseGender constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            breed = Integer.parseInt(e.getActionCommand());
            window.dispose();
            new ChooseGender(GENDERS, keyword, safeBox.get(0));
        }
    }

    public class ChooseGender extends ChooseButton {

        // EFFECTS: construct an ChooseGender Class, create JFrame window for user to enter information
        public ChooseGender(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
            super(listOfButtonTitle, keyword, parent);
            type = "Gender";
            createButtonLayout(listOfButton);
        }

        // MODIFIES: this.gender in outer class
        // EFFECTS: when a button is hit, modify gender based on user's input, then close window and call a
        //          ChooseColor constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            gender = Integer.parseInt(e.getActionCommand());
            window.dispose();
            new ChooseColor(COLOURS, keyword, safeBox.get(0));
        }
    }

    public class ChooseColor extends ChooseButton {

        // EFFECTS: construct an ChooseColor Class, create JFrame window for user to enter information
        public ChooseColor(String[] listOfButtonTitle, String keyword, PetAdoptionApp parent) {
            super(listOfButtonTitle, keyword, parent);
            type = "Colour";
            createButtonLayout(listOfButton);
        }

        // MODIFIES: this.colour in outer class
        // EFFECTS: when a button is hit, modify colour based on user's input, then close window and
        //          call a ChooseYear constructor if the keyword is "defined";
        //          if the keyword is "random" create a made-up pet based on user's input
        //          and find matches in parent's adoptionlist, then call a SelectOnPet constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            colour = Integer.parseInt(e.getActionCommand());
            window.dispose();
            if (keyword.equals("defined")) {
                new ChooseYear(2030, 2000, "Year", keyword, safeBox.get(0));
            } else {
                Date someday = new Date(10,10,2020);
                List<Pet> nameMatches;
                pet = new Pet("whatever", species, age, breed, gender, someday, colour);
                nameMatches = safeBox.get(0).adoptionList.searchPetsByDescription(pet);
                new SelectOnePet(safeBox.get(0), nameMatches);
            }
        }
    }

    public class ChooseDay extends ComboBoxChooser {

        // EFFECTS: construct an ChooseDay Class, create JFrame window for user to enter information
        public ChooseDay(int maxNumber, int minNumber, String type, String keyword, PetAdoptionApp parent) {
            super(maxNumber, minNumber, type, keyword, parent);
            displayComboBox();
        }

        // MODIFIES: this.birthdayDay in outer class
        // EFFECTS: when a button is hit, modify birthdayDay based on user's input;
        //          create a pet based on all user's inputs and add this pet into parent's adoption list;
        //          and then print this new pet's information
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("confirm".equals(e.getActionCommand())) {
                birthdayDay = Integer.parseInt((String)chooseBox.getSelectedItem());
                window.dispose();
                birthday = new Date(birthdayMonth, birthdayDay, birthdayYear);
                pet = new Pet(name, species, age, breed, gender, birthday, colour);
                safeBox.get(0).adoptionList.addPet(pet);
                DecryptIntegerToString decryption = new DecryptIntegerToString();
                decryption.printInformation(pet);
            }
        }
    }

    public class ChooseMonth extends ComboBoxChooser {

        // EFFECTS: construct an ChooseMonth Class, create JFrame window for user to enter information
        public ChooseMonth(int maxNumber, int minNumber, String type, String keyword, PetAdoptionApp parent) {
            super(maxNumber, minNumber, type, keyword, parent);
            displayComboBox();
        }

        // MODIFIES: this.birthdayMonth in outer class
        // EFFECTS: when a button is hit, modify birthdayMonth based on user's input, then close window and call a
        //          ChooseDay constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("confirm".equals(e.getActionCommand())) {
                birthdayMonth = Integer.parseInt((String)chooseBox.getSelectedItem());
                window.dispose();
                YearMonth yearMonthObject = YearMonth.of(birthdayYear, birthdayMonth);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                new ChooseDay(daysInMonth, 1, "Day", keyword, safeBox.get(0));
            }
        }
    }

    public class ChooseYear extends ComboBoxChooser {

        // EFFECTS: construct an ChooseYear Class, create JFrame window for user to enter information
        public ChooseYear(int maxNumber, int minNumber, String type, String keyword, PetAdoptionApp parent) {
            super(maxNumber, minNumber, type, keyword, parent);
            displayComboBox();
        }

        // MODIFIES: this.birthdayYear in outer class
        // EFFECTS: when a button is hit, modify birthdayYear based on user's input, then close window and call a
        //          ChooseMonth constructor
        @Override
        public void actionPerformed(ActionEvent e) {
            if ("confirm".equals(e.getActionCommand())) {
                birthdayYear = Integer.parseInt((String)chooseBox.getSelectedItem());
                window.dispose();
                new ChooseMonth(12, 1, "Month", keyword, safeBox.get(0));
            }
        }
    }
}
