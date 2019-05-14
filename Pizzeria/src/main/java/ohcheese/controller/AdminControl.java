package ohcheese.controller;

public class AdminControl extends GeneralWindowControl{

    private int AdminID;

    public void getIDFirst(int ID){
        this.AdminID = ID;
        System.out.println("Got Ad");
    }
}
