public class DigitalClock {
     int hours;
     int minutes;

     /*** TODO: Write the method header for the constructor method that takes
                two arguments, hours and minutes ***/
     public DigitalClock(int hours, int minutes) {
          /*** TODO: Set the corresponding instance variables using the parameters ***/
          this.hours = hours;
          this.minutes = minutes;
     }

     public String toString() {
          return "" + hours + ":" + (minutes < 10?"0":"") + minutes;
     }

     public static void main(String[] args) {
          DigitalClock dc = new DigitalClock(3,45);
          System.out.println(dc);
     }
}
