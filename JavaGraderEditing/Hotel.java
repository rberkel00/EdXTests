public class Hotel {
  private String hotelName;
  private Room[] rooms;
  private int totalRooms;

  public Hotel(String hotelName, int totalRooms, int totalFloors) {
    int roomsperfloor = totalRooms/totalFloors;
    this.totalRooms = totalRooms;
    rooms = new Room[totalRooms];
    for (int i = 0; i < totalFloors; i++) {
      for (int j = 0; j < roomsperfloor; j++) {
        if (j + 1 == roomsperfloor) {
          rooms[i*roomsperfloor+j] = new Room(i*100+j, "suite");
        } else if (j + 5 >= roomsperfloor) {
          rooms[i*roomsperfloor+j] = new Room(i*100+j, "single king");
        } else {
          rooms[i*roomsperfloor+j] = new Room(i*100+j, "double queen");
        }
      }
    }
  }

  public int getTotalRooms() {
    return totalRooms;
  }

  public int getNumberOccupied() {
    int count = 0;
    for (int i = 0; i < rooms.length; i++) {
      if (rooms[i].getOccupantName() != null) {
        count++;
      }
    }
    return count;
  }

  public double getOccupancyRate() {
    return Math.round(((double)getNumberOccupied()/totalRooms)*100) / 100.0;
  }

  public boolean rentRoom(String type, String name, int days) {
    for (int i = 0; i < rooms.length; i++) {
      if (rooms[i].getOccupantName() == null && rooms[i].getRoomType().equals(type)) {
        return rooms[i].setOccupant(name, days);
      }
    }
    return false;
  }

  public void advanceDay() {
    for (int i = 0; i < rooms.length; i++) {
      rooms[i].advanceDay();
    }
  }

  public String toString() {
    return "The " + hotelName + ": " + getOccupancyRate() * 100 + "% occupied";
  }

}
