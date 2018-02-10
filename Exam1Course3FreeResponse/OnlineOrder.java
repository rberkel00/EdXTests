import java.text.DecimalFormat;

public class OnlineOrder {
    private String orderName;
    private double totalCost, tax;
    private int numTurkey = 0, numItalian = 0, numVeggie = 0, numBLT = 0;
    public static final double TAX_RATE = 0.07;
    public static final double TURKEY_PRICE = 4.99, ITALIAN_PRICE = 4.99, BLT_PRICE = 4.99, VEGGIE_PRICE=3.99;

    public OnlineOrder(String name, int numTurkey, int numItalian, int numVeggie, int numBLT){
        this.orderName = name;
        changeOrder(numTurkey, numItalian, numVeggie, numBLT);
    }

    public void changeOrder(int numTurkey, int numItalian, int numVeggie, int numBLT){
        if (numTurkey >= 0) this.numTurkey = numTurkey; else this.numTurkey=0;
        if (numItalian >= 0) this.numItalian = numItalian; else this.numItalian = 0;
        if (numVeggie >= 0) this.numVeggie = numVeggie; else this.numVeggie = 0;
        if (numBLT >= 0) this.numBLT = numBLT; else this.numBLT = 0;

        totalCost = (this.numTurkey * 4.99) + (this.numItalian * 4.99) +
                (this.numVeggie * 3.99) + (this.numBLT * 4.99);

        //DecimalFormat df = new DecimalFormat("#.##");
        //tax = Double.valueOf(df.format(totalCost * TAX_RATE));
        tax = Math.round((totalCost * TAX_RATE) * 100) / 100.0;
    }

    public double getTotalCost(){ return totalCost;}
    public double getTax() { return tax; }

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");

        return orderName + ", your order comes to $" + (totalCost + tax);
    }
}
