public class Room {

    private int roomNumber;

    private int daysRented;

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
        if (occupantName != null)
            return false;
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
        //4
        System.out.println("111 " + r.getRoomNumber());
        //0
        System.out.println("222 " + r.getDaysRented());
        //single king
        System.out.println("333 " + r.getRoomType());
        //null
        System.out.println("444 " + r.getOccupantName());
        //true
        System.out.println("555 " + r.setOccupant("Robyn", 2));
        //false
        System.out.println("666 " + r.setOccupant("TJ", 2));
        //Robyn
        System.out.println("777 " + r.getOccupantName());
        r.advanceDay();
        //should be 1
        System.out.println("888 " + r.getDaysRented());
        r.advanceDay();
        //should be null
        System.out.println("999 " + r.getOccupantName());
        //to string
        System.out.println("101 " + r.toString());
    }
}
