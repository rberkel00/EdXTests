public class Room {
  private int roomNumber;
  private int	daysRented;
  private String roomType;
  private String occupantName;
  public Room(int roomNumber, String roomType) {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    daysRented = 0;
    occupantName = null;
  }
  public int getRoomNumber() {
    return roomNumber;
  }
  public int getDaysRented() {
    return daysRented;
  }
  public String getRoomType() {
    return roomType;
  }
  public String getOccupantName() {
    return occupantName;
  }
  public boolean setOccupant(String guest, int days) {
    if (occupantName != null) return false;
    occupantName = guest;
    daysRented = days;
    return true;
  }
  public void advanceDay() {
    daysRented--;
    if (daysRented <= 0) {
      occupantName = null;
      daysRented = 0;
    }
  }
  public String toString() {
    return "Room " + roomNumber + ": " + roomType + " – " + (occupantName == null ? "free" : "rented");
  }
  public static void main(String[] args) {
    Room r = new Room(4, "single king");
    System.out.println("111 " + r.getRoomNumber()); //4
    System.out.println("222 " + r.getDaysRented()); //0
    System.out.println("333 " + r.getRoomType()); //single king
    System.out.println("444 " + r.getOccupantName()); //null

    System.out.println("555 " + r.setOccupant("Robyn", 2)); //true
    System.out.println("666 " + r.setOccupant("TJ", 2)); //false
    System.out.println("777 " + r.getOccupantName()); //Robyn

    r.advanceDay();
    System.out.println("888 " + r.getDaysRented()); //should be 1
    r.advanceDay();
    System.out.println("999 " + r.getOccupantName()); //should be null
    System.out.println("101 " + r.toString()); //to string

  }
}
