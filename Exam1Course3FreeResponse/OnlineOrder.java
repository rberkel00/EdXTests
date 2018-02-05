/**
 * Kirsten M Martindale
 * 2/2/18
 * This is a program to calculate online sandwich orders
 * */

public class OnlineOrder
{
  private String orderName;
  private double totalCost;
  private double tax;
  private int numTurkey;
  private int numItalian;
  private int numVeggie;
  private int numBLT;
  private final double TAX_RATE = .07;
  private final double MEAT_SANDWICHS = 4.99;
  private final double VEG_SANDWICHS = 3.99;

  public OnlineOrder(String name, int turk, int ital, int veg, int blt)
  {
    orderName = name;
    changeOrder(turk, ital, veg, blt);
  }

  public void changeOrder(int turk, int ital, int veg, int blt)
  {
    if(turk >= 0)
      numTurkey = turk;
		else numTurkey = 0;
    if(ital >= 0)
      numItalian = ital;
		else numItalian = 0;
    if(veg >= 0)
      numVeggie = veg;
		else numVeggie = 0;
    if(blt >= 0)
      numBLT = blt;
		else numBLT = 0;
    totalCost = MEAT_SANDWICHS * (numTurkey + numItalian + numBLT) + VEG_SANDWICHS * numVeggie;
		totalCose = Math.round(totalCost*100)/100.0;

    tax = totalCost * TAX_RATE;  //rounding to two decimal places
  }

  public double getTotalCost()
  {
    return totalCost;
  }

  public double getTax()
  {
    return tax;
  }

  public String toString()
  {
    String temp = orderName + ", your order comes to $" + (totalCost+tax);

    return temp;
  }
}
