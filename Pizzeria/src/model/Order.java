package model;

public class Order {
    private int CustumerID;
    private int PizzaIDs[];
    private int sizeID[];
    private float finalPrice;
    private int PaymentMethodID;

    public Order(int CustumerID) {
        this.CustumerID = CustumerID;
        this.PizzaIDs = new int[100];
        this.sizeID = new int[100];
        this.finalPrice = 0;
        this.PaymentMethodID = 0;
    }

    //GETTES & SETTERS
    //Custumer ID
    public void setCustumerID(int custumerID) { CustumerID = custumerID; }
    public int getCustumerID() { return CustumerID; }
    //Pizza ID's
    public void setPizzaIDs(int[] pizzaIDs) { PizzaIDs = pizzaIDs; }
    public int[] getPizzaIDs() { return PizzaIDs; }
    //Get size ID
    public int[] getSizeID() { return sizeID; }
    public void setSizeID(int[] sizeID) { this.sizeID = sizeID; }
    //Final Price
    public void setFinalPrice(float finalPrice) { this.finalPrice = finalPrice; }
    public float getFinalPrice() { return finalPrice; }
    //Payment Method ID
    public void setPaymentMethod(int paymentMethodID) { PaymentMethodID = paymentMethodID; }
    public int getPaymentMethod() { return PaymentMethodID; }

    public void setPizzaAndSize(int pizzaID, String size, int OrderedPizzaIndex){
        this.PizzaIDs[OrderedPizzaIndex] = pizzaID;
        if(size == "25")
            this.sizeID[OrderedPizzaIndex] = 1;
        else if(size == "35")
            this.sizeID[OrderedPizzaIndex] = 2;
        else
            this.sizeID[OrderedPizzaIndex] = 3;
    }









}
