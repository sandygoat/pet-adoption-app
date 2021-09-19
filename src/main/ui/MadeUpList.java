//package ui;
//
//import model.Date;
//import model.Owner;
//import model.Pet;
//import model.PetList;
//
//// Creates a random list for the application for presentation purpose
//public class MadeUpList {
//    PetList madeUpList;
//
//    Date date1 = new Date(11,14,2020);
//    Date date2 = new Date(11,15,2014);
//    Date date3 = new Date(11,16,2019);
//    Date date4 = new Date(11,17,2018);
//    Date date5 = new Date(11,18,2016);
//    Date date6 = new Date(11,19,2015);
//    Date date7 = new Date(11,20,2014);
//    Date date8 = new Date(11,21,2018);
//    Date date9 = new Date(11,22,2019);
//    Date date10 = new Date(11,23,2017);
//    Date date11 = new Date(11,24,2018);
//    Date date12 = new Date(11,25,2019);
//    Date date13 = new Date(11,26,2015);
//    Date date14 = new Date(11,27,2018);
//    Date date15 = new Date(11,28,2017);
//
//    Pet pet1 = new Pet("daenerys", 0,0,3,0,date1,4);
//    Pet pet2 = new Pet("dolores", 1,0,0,1,date2,2);
//    Pet pet3 = new Pet("andy", 0,0,1,1,date3,1);
//    Pet pet4 = new Pet("jax", 0,0,8,0,date4,5);
//    Pet pet5 = new Pet("maeve", 0,0,4,0,date5,6);
//    Pet pet6 = new Pet("nash", 1,0,5,1,date6,3);
//    Pet pet7 = new Pet("frodo", 0,0,6,0,date7,7);
//    Pet pet8 = new Pet("harry", 0,0,7,0,date8,2);
//    Pet pet9 = new Pet("hermione", 1,0,3,0,date9,1);
//    Pet pet10 = new Pet("luke", 0,0,2,1,date10,0);
//    Pet pet11 = new Pet("anakin", 1,0,2,1,date11,0);
//    Pet pet12 = new Pet("bruce", 1,0,4,0,date12,4);
//    Pet pet13 = new Pet("magneto", 1,0,6,1,date13,5);
//    Pet pet14 = new Pet("Arya", 0,0,5,0,date14,6);
//    Pet pet15 = new Pet("luna", 0,0,1,1,date15,2);
//
//    Owner owner1 = new Owner("ben", "(778) 456-7980");
//    Owner owner2 = new Owner("nick", "(778) 654-7884");
//    Owner owner3 = new Owner("paul", "(778) 234-4532");
//
//    // EFFECTS: creates a made-up adoption list
//    public MadeUpList() {
//        madeUpList = new PetList();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: add random pets into a made-up adoption list, returns the made-up list
//    public PetList makeRandomList() {
//        pet1.adopt(owner1);
//        pet7.adopt(owner2);
//        pet14.adopt(owner3);
//        madeUpList.addPet(pet1);
//        madeUpList.addPet(pet2);
//        madeUpList.addPet(pet3);
//        madeUpList.addPet(pet4);
//        madeUpList.addPet(pet5);
//        madeUpList.addPet(pet6);
//        madeUpList.addPet(pet7);
//        madeUpList.addPet(pet8);
//        madeUpList.addPet(pet9);
//        madeUpList.addPet(pet10);
//        madeUpList.addPet(pet11);
//        madeUpList.addPet(pet12);
//        madeUpList.addPet(pet13);
//        madeUpList.addPet(pet14);
//        madeUpList.addPet(pet15);
//        madeUpList.updateAgeForAll(date1);
//        madeUpList.giveVaccineForAll(date1);
//        return madeUpList;
//    }
//}
