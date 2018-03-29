public class StudentAdvance extends Advance {
  public StudentAdvance(int days) {
    super(days);
  }

  public String toString() {
    return super.toString() + "student id required";
  }

  public double getPrice() {
    return super.getPrice() / 2;
  }
}
