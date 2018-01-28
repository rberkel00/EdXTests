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
		if (numT >= 0) numTurkey = numT;
		else numTurkey = 0;
		if (numI >= 0) numItalian = numI;
		else numItalian = 0;
		if (numV >= 0) numVeggie = numV;
		else numVeggie = 0;
		if (numB >= 0) numBLT = numB;
		else numBLT = 0;
		totalCost = Math.round(100*((numTurkey + numItalian + numBLT) * 4.99 + numVeggie * 3.99))/100.0;
		tax = totalCost * TAX_RATE;
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

}
