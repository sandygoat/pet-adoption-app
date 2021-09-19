# Animal Adoption Application

## Make Your Furry Friend Your Best Friend

This animal adoption application is designed to help stray dogs and cats find their forever homes. The application
can be used by adoption/fostering/refugee facility workers and potential adoption applicants. Facility workers 
will be able to search, enter and modify the information of newly admitted animals, existing members, or the "lucky 
ones". If adopted, owner's information can be added and found. Potential adoption 
applicants can use this application to find the ideal pet for adoption based on their preference.

The information includes:
 - *name*
 - *species* 
 - *age* 
 - *gender* 
 - *breed* 
 - *birthday* 
 - *coat color* 
 - *vaccine condition*
 - *adoption condition*
 - *owner information (if applicable)*

If adopted:
- owner name
- contact number

As a dog owner myself, I would like to see all animals to have a caring and cozy home like my dog does. Although it
is impossible to accommodate every furry friend, I want to improve their situations as much as I can by designing this 
application. If you want to help more, please visit <https://spca.bc.ca/>.

## User Stories

For this application:
- As a user, I want to be able to create a new pet waited to be adopted.
- As a user, I want to be able to add a new pet to the adoption list.
- As a user, I want to be able to remove a pet from the adoption list.
- As a user, I want to be able to select a pet and modify its age, vaccine, and adoption condition.
- As a user, I want to be able to find a list of pets either by name or description.
- As a user, I want to be able to view all information about a pet.
- As a user, I want to be able to save the adoption list to a file.
- As a user, I want to be able to load the adoption list from a file.
- As a user, I want to be able to be given options whether to save the adoption list to a file or not 
when quiting application.

## Phase 4: Task 2
- The following classes are extensions of a superclass, which utilize type hierarchy:
    - Superclass: ChooseButton
        - ChooseSpecies
        - ChooseGender
        - ChooseColor
        - ChooseDogBreed
        - ChooseCatBreed
    - Superclass: ComboBoxChooser
        - ChooseAge
        - ChooseDay
        - ChooseMonth
        - ChooseYear
    - Superclass: UpdateCondition
        - UpdateAgeForAll
        - UpdateVaccineForAll
        - SelectOnePet
    - Superclass: Option
        - AddPet
        - SaveFile
        - LoadFile
        - SearchPet
        - UpdateInformation

## Phase 4: Task 3
If time allows, I would refactor EnterName class and EnterOwner class, since they are both designed to use a JFrame
window and JTextField to ask user to enter information. I would generate an abstract class EnterText from similar 
implementation in EnterOwner class and EnterName class. While extending EnterText in EnterOwner class, I will create 
an additional method for creating a panel with formatted field for cell phone number. And then, overwrite the display
method to show the phone number panel as well.




