import java.util.Random;

public class Image {
  String[][] pixels;

  public Image(int width, int height) {
    /*** TODO: Create an empty two-dimensional String array, with height rows
               and width columns ***/
    pixels = new String[height][width];
    loadPixels();
  }

  public void filterRed() {
    /*** TODO: Iterate over every String element in pixels
               If element is mostly red, set red to "00", do not change green or blue ***/
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        if (mostlyRed(pixels[i][j])) {
          pixels[i][j] = "00" + pixels[i][j].substring(2);
        }
      }
    }
  }

  public void loadPixels() {
    /*** initialization of pixels is not shown
         data stored as hexadecimal color string, i.e. "C28E0E" ***/
    Random r = new Random();
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        pixels[i][j] = makeHex(r.nextInt(256)) + makeHex(r.nextInt(256)) + makeHex(r.nextInt(256));
      }
    }
  }

  public String makeHex(int x) {
    String s = Integer.toHexString(x);
    if (s.length() < 2) s = "0" + s;
    return s;
  }

  public boolean mostlyRed(String p) {
    int r = hexToDecimal(p.substring(0,2));
    int g = hexToDecimal(p.substring(2,4));
    int b = hexToDecimal(p.substring(4));
    return (r > g && r > b);
  }

  public int hexToDecimal(String hex) {
    char first = hex.charAt(0);
    char second = hex.charAt(1);
    int dec = (int)((first < 65) ? (first - 47) : (first - 65)) * 10;
    dec = dec + (int)((second < 65) ? (second - 47) : (second - 65));
    return dec;
  }

  public String toString() {
    String s = "<html><head></head><body><table style='border-collapse: collapse; padding: 0px;'>";
    for (int i = 0; i < pixels.length; i++) {
      s = s +  "<tr>";
      for (int j = 0; j < pixels[i].length; j++) {
        s = s + "<td style='height: 1px; width: 1px; background-color: #" + pixels[i][j] + ";'></td>";
      }
      s = s + "</tr>";
    }
    s = s + "</table></body></html>";
    return s;
  }

  public static void main(String[] args) {
    Image img = new Image(10,12);
    //System.out.println("<html><head></head><body>Before Filter</body></html>");
    //System.out.println(img);
    img.filterRed();
    System.out.println("1d: " + pixels.length + " 2d: " + pixels[0].length);
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        if (mostlyRed(pixels[i][j])) {
          System.out.println("failed");
        }
      }
    }
    //System.out.println("<html><head></head><body>After Filter</body></html>");
    //System.out.println(img);
  }
}
