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

    public void changeOrder(int numT, int numI, int numV, int numB) {
        if (numT >= 0)
            numTurkey = numT;
        else
            numTurkey = 0;
        if (numI >= 0)
            numItalian = numI;
        else
            numItalian = 0;
        if (numV >= 0)
            numVeggie = numV;
        else
            numVeggie = 0;
        if (numB >= 0)
            numBLT = numB;
        else
            numBLT = 0;
        totalCost = Math.round(100 * ((numTurkey + numItalian + numBLT) * 4.99 + numVeggie * 3.99)) / 100.0;
        tax = totalCost * TAX_RATE;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getTax() {
        return tax;
    }

    public String toString() {
        String temp = orderName + ", your order comes to $" + (totalCost + tax);
        return temp;
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
