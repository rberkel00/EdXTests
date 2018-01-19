public class Hotel {

    private String hotelName;

    public Room[] rooms;

    private int totalRooms;

    public Hotel(String hotelName, int totalRooms, int totalFloors) {
        int roomsperfloor = totalRooms / totalFloors;
        this.totalRooms = totalRooms;
        rooms = new Room[totalRooms];
        for (int i = 0; i < totalFloors; i++) {
            for (int j = 0; j < roomsperfloor; j++) {
                if (j + 1 == roomsperfloor) {
                    rooms[i * roomsperfloor + j] = new Room(i * 100 + j, "suite");
                } else if (j + 5 >= roomsperfloor) {
                    rooms[i * roomsperfloor + j] = new Room(i * 100 + j, "single king");
                } else {
                    rooms[i * roomsperfloor + j] = new Room(i * 100 + j, "double queen");
                }
            }
        }
        this.hotelName = hotelName;
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
        return Math.round(100 * (double) getNumberOccupied() / getTotalRooms()) / 100.0;
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

    public static void main(String[] args) {
        Hotel h = new Hotel("hotel", 100, 10);
        //check that constructor worked correctly
        int roomsperfloor = 10;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < roomsperfloor; j++) {
                if (h.rooms[i * roomsperfloor + j].getRoomNumber() != (i + 1) * 100 + j) {
                    System.out.println("Constructor Failed");
                }
                if (j + 1 == roomsperfloor) {
                    if (!h.rooms[i * roomsperfloor + j].getRoomType().equals("suite")) {
                        System.out.println("Constructor Failed");
                    }
                } else if (j + 5 >= roomsperfloor) {
                    if (!h.rooms[i * roomsperfloor + j].getRoomType().equals("single king")) {
                        System.out.println("Constructor Failed");
                    }
                } else {
                    if (!h.rooms[i * roomsperfloor + j].getRoomType().equals("double queen")) {
                        System.out.println("Constructor Failed");
                    }
                }
            }
        }
        //check accessor methods
        h.rentRoom("suite", "r", 5);
        h.rentRoom("double queen", "b", 5);
        if (!h.rooms[9].getOccupantName().equals("r")) {
            System.out.println("RentRoom 1 Failed");
        }
        if (h.getTotalRooms() != 100) {
            System.out.println("Accessor Failed");
        }
        if (h.getNumberOccupied() != 2) {
            System.out.println("Accessor Failed");
        }
        if (h.getOccupancyRate() != 0.02) {
            System.out.println(h.getOccupancyRate());
            System.out.println("Accessor Failed");
        }
        h.rentRoom("suite", "a", 5);
        h.rentRoom("suite", "j", 5);
        h.rentRoom("suite", "c", 5);
        h.rentRoom("suite", "d", 5);
        h.rentRoom("suite", "e", 5);
        h.rentRoom("suite", "f", 5);
        h.rentRoom("suite", "g", 5);
        h.rentRoom("suite", "h", 5);
        h.rentRoom("suite", "i", 5);
        if (h.rentRoom("suite", "r", 5)) {
            System.out.println("RentRoom 2 Failed");
        }
        h.advanceDay();
        for (int j = 0; j < h.rooms.length; j++) {
            if (h.rooms[j].getOccupantName() != null && h.rooms[j].getDaysRented() != 4) {
                System.out.println("AdvanceDay Failed");
            }
        }
        System.out.println(h.toString());
    }
}
