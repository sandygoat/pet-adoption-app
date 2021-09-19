package ui.operations;

import model.Date;
import model.Owner;
import model.Pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Decrypt integer into string information it represents
public class DecryptIntegerToString extends JFrame implements ActionListener {

    private JFrame window = new JFrame("Pet Information");

    // EFFECTS: initialize JFrame for displaying pet information
    public DecryptIntegerToString() {
        window.setLayout(new BorderLayout());
        window.setMinimumSize(new Dimension(350, 225));
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    // EFFECTS: decrypt integers that represent species
    public String decryptSpecies(int code) {
        if (code == 0) {
            return "dog";
        } else if (code == 1) {
            return "cat";
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt integers that represent ages
    public String decryptAge(int code) {
        if (code != -1) {
            return String.valueOf(code);
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt integers that represent an animal's breed
    public String decryptPetBreed(int species, int breed) {
        if (species == 0) {
            return decryptDogBreed(breed);
        } else if (species == 1) {
            return decryptCatBreed(breed);
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt integers that represent dog breeds
    public String decryptDogBreed(int code) {
        if (code == 0) {
            return "american eskimo";
        } else if (code == 1) {
            return "golden retriever";
        } else if (code == 2) {
            return "german shepherd";
        } else if (code == 3) {
            return "shiba inu";
        } else if (code == 4) {
            return "poodle";
        } else if (code == 5) {
            return "chihuahua";
        } else if (code == 6) {
            return "labrador retriever";
        } else if (code == 7) {
            return "french bulldog";
        } else if (code == 8) {
            return "other dog";
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt integers that represent cat breeds
    public String decryptCatBreed(int code) {
        if (code == 0) {
            return "siamese";
        } else if (code == 1) {
            return "persian";
        } else if (code == 2) {
            return "maine coon";
        } else if (code == 3) {
            return "ragdoll";
        } else if (code == 4) {
            return "bengal";
        } else if (code == 5) {
            return "abyssinian";
        } else if (code == 6) {
            return "birman";
        } else if (code == 7) {
            return "british shorthair";
        } else if (code == 8) {
            return "other cat";
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt integers that represent genders
    public String decryptGender(int code) {
        if (code == 0) {
            return "female";
        } else if (code == 1) {
            return "male";
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt Date class into a string message
    public String decryptDate(Date date) {
        return date.getYear() + "." + date.getMonth() + "." + date.getDay();
    }

    // EFFECTS: decrypt integers that represent colours
    public String decryptColour(int code) {
        if (code == 0) {
            return "white";
        } else if (code == 1) {
            return "black";
        } else if (code == 2) {
            return "brown";
        } else if (code == 3) {
            return "gold";
        } else if (code == 4) {
            return "grey";
        } else if (code == 5) {
            return "cream";
        } else if (code == 6) {
            return "yellow";
        } else if (code == 7) {
            return "mixed";
        } else if (code == 8) {
            return "other colour";
        } else {
            return "n/a";
        }
    }

    // EFFECTS: decrypt boolean into string message yes or no
    public String decryptBoolean(Boolean b) {
        if (b) {
            return "yes";
        } else {
            return "no";
        }
    }

    // EFFECTS: decrypt Owner class into a string message
    public String decryptOwner(Owner owner) {
        return owner.getOwnerName() + " @ " + owner.getPhoneNumber();
    }

    // EFFECTS: print all information for a pet after decryption
    public void printInformation(Pet pet) {
        JButton close = new JButton("Close");
        close.addActionListener(this);

        String[] columnNames = {"Type", "Details"};
        Object[][] data = {
                {"Name:", pet.getName()},
                {"Species:", decryptSpecies(pet.getSpecies())},
                {"Breed:", decryptPetBreed(pet.getSpecies(), pet.getBreed())},
                {"Gender:", decryptGender(pet.getGender())},
                {"Age:", decryptAge(pet.getAge())},
                {"Birthday:", decryptDate(pet.getBirthday())},
                {"Color:", decryptColour(pet.getColour())},
                {"Last Vaccine Date:", decryptDate(pet.getLastVaccineDate())},
                {"Adopted:", decryptBoolean(pet.getAdoptionCondition())},
                {"Owner:", decryptOwner(pet.getOwner())}
        };
        JTable table = new JTable(data, columnNames);
        window.add(table, BorderLayout.PAGE_START);
        window.add(close, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
    }
}
