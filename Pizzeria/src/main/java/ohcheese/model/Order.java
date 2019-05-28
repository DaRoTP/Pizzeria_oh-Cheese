package ohcheese.model;


import java.util.ArrayList;

public class Order {
    private int small_price;
    private int medium_price;
    private int large_price;
    private ArrayList<Integer> pizzasIDs = new ArrayList<Integer>();
    private ArrayList<Integer> SizeIDs = new ArrayList<Integer>();
    private float finalPrice;
    private int PaymentMethodID;

    public Order(int sprice, int mprice, int lprice) {

        this.small_price = sprice;
        this.medium_price = mprice;
        this.large_price = lprice;
        this.finalPrice = 0;
        this.PaymentMethodID = 0;
    }

    //GETTES & SETTERS
    //Final Price
    public void setFinalPrice(float finalPrice) { this.finalPrice = finalPrice; }
    public float getFinalPrice() { return finalPrice; }
    //Payment Method ID
    public void setPaymentMethod(int paymentMethodID) { PaymentMethodID = paymentMethodID; }
    public int getPaymentMethod() { return PaymentMethodID; }


    public ArrayList<Integer> getPizzasIDs() {
        return pizzasIDs;
    }

    public ArrayList<Integer> getSizeIDs() {
        return SizeIDs;
    }

    public void setPizzaAndSize(int pizzaID, String size){
        pizzasIDs.add(pizzaID);
        if(size == "small")
            SizeIDs.add(0);
        else if(size == "medium")
            SizeIDs.add(1);
        else
            SizeIDs.add(2);
    }

    public void subFrom_FinalPrice(Integer size){
        if(size == 0)
            this.finalPrice -= small_price;
        else if(size == 1)
            this.finalPrice -= medium_price;
        else
            this.finalPrice -= large_price;
    }

    public void removedFromOrdered(int index){
        pizzasIDs.remove(index);
        SizeIDs.remove(index);
    }











}
