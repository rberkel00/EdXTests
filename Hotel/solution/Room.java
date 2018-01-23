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
}
