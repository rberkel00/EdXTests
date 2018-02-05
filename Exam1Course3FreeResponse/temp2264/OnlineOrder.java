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

    public void changeOrder(int turk, int ital, int veg, int blt) {
        if (turk >= 0)
            numTurkey = turk;
        else
            numTurkey = 0;
        if (ital >= 0)
            numItalian = ital;
        else
            numItalian = 0;
        if (veg >= 0)
            numVeggie = veg;
        else
            numVeggie = 0;
        if (blt >= 0)
            numBLT = blt;
        else
            numBLT = 0;
        totalCost = MEAT_SANDWICHS * (numTurkey + numItalian + numBLT) + VEG_SANDWICHS * numVeggie;
        ???;
    //rounding to two decimal places
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
