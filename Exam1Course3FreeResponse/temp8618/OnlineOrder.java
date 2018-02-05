public class OnlineOrder {

    String orderName;

    double totalCost;

    double tax;

    int numTurkey;

    int numItalian;

    int numVeggie;

    int numBLT;

    final double TAX_RATE = 0.07;

    public OnlineOrder(String name, int numT, int numI, int numV, int numB) {
        orderName = name;
        changeOrder(numT, numI, numV, numB);
    }

    public void changeOrder(int numTurkey, int numItalian, int numVeggie, int numBLT) {
        if (numTurkey >= 0)
            this.numTurkey = numTurkey;
        else
            this.numTurkey = 0;
        if (numItalian >= 0)
            this.numItalian = numItalian;
        else
            this.numItalian = 0;
        if (numVeggie >= 0)
            this.numVeggie = numVeggie;
        else
            this.numVeggie = 0;
        if (numBLT >= 0)
            this.numBLT = numBLT;
        else
            this.numBLT = 0;
        totalCost = (this.numTurkey * TURKEY_PRICE) + (this.numItalian * ITALIAN_PRICE) + (this.numVeggie * VEGGIE_PRICE) + (this.numBLT * BLT_PRICE);
        //DecimalFormat df = new DecimalFormat("#.##");
        //tax = Double.valueOf(df.format(totalCost * TAX_RATE));
        tax = Math.round((totalCost * TAX_RATE) * 100) / 100.0;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTax() {
        return tax;
    }

    public String toString() {
        return orderName + ", your order come to $" + (totalCost + tax);
    }

    public static void main(String[] args) {
        OnlineOrder oo = new OnlineOrder("Spongebob", 3, 2, 1, 1);
        System.out.println("tc1: " + oo.getTotalCost());
        System.out.println("t1: " + oo.getTax());
        System.out.println("ts1: " + oo.toString());
        oo.changeOrder(1, 5, 2, 1);
        System.out.println("tc2: " + oo.getTotalCost());
        System.out.println("t2: " + oo.getTax());
        System.out.println("ts2: " + oo.toString());
        oo.changeOrder(-1, -5, 0, 2);
        System.out.println("tc3: " + oo.getTotalCost());
        System.out.println("t3: " + oo.getTax());
        System.out.println("ts3: " + oo.toString());
    }
}
