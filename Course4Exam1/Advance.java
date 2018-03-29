public class Advance extends Ticket {

  private int days;

  public Advance(int days) {
    super();
    this.days = days;
  }

  public double getPrice() {
    return (days >= 10) ? 30 : 40;
  }
}
