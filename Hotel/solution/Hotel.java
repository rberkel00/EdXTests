public class Hotel {

    private String hotelName;
    private Room[] rooms;
    private int totalRooms;

    public Hotel(String hotelName, int totalRooms, int numFloors){
        this.hotelName = hotelName;
        this.totalRooms = totalRooms;
        rooms = new Room[totalRooms];
        int roomsPerFloor = totalRooms/numFloors;
        int count = 0;
        for (int f = 0; f < numFloors; f++ ) {
            for (int r = 0; r < roomsPerFloor ; r++) {
                if (r < roomsPerFloor - 5) {
                    rooms[count] = new Room(100*(f + 1) + r, "double queen");
                }
                else if (r < roomsPerFloor - 1) {
                    rooms[count] = new Room(100*(f+1) + r, "single king");
                }
                else {
                    rooms[count] = new Room(100*(f+1) + r, "suite");
                }
                count++;
            }
        }
    }

    public Room[] getRooms() {
        return rooms;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public int getNumberOccupied() {
        int numOccupied = 0;
        for (Room r : rooms) {
            if (r.getOccupantName() != null) {
                numOccupied++;
            }
        }
        return numOccupied;
    }

    public double getOccupancyRate() {
        return Math.round(100*(double)getNumberOccupied()/getTotalRooms())/100.0;
    }

    public boolean rentRoom(String roomType, String renterName, int daysRented) {
        for (Room r : rooms) {
            if (r.getOccupantName() == null && r.getRoomType().equals(roomType)) {
                r.setOccupant(renterName, daysRented);
                return true;
            }
        }
        return false;
    }

    public void advanceDay() {
        for (Room r : rooms) {
            if (r.getOccupantName() != null) {
                r.advanceDay();
            }
        }
    }

    public String toString() {
        return hotelName + ": " + Math.round(100*getOccupancyRate()) + "% occupied";
    }

}
