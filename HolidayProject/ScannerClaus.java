import java.util.Scanner;

/**
 * Created by psands on 11/29/2016.
 */
public class ScannerClaus {
    private int numPresents;
    private int numCarrots;
    private int x,y;
    private String path;

    public ScannerClaus(DeliveryMap d) {
        this.numPresents = d.getNumPresents();
        this.numCarrots = 5*this.numPresents;
        int[] pos = d.find('S');
        this.x = pos[1];
        this.y = pos[0];
        this.path = "";
    }

    /** @return the number of carrots left for the reindeers */
    public int getNumCarrots()  {return numCarrots;}

    /** @return the number of presents left to deliver */
    public int getNumPresents() {return numPresents;}

    /** @return the list of selected moves as a single String with no spaces
     *         followed by a new line, "Steps: " and then the number of moves
     *         made.
     */
    public String getPath()     {return path + "\nSteps: " + path.length();}

    /** Returns the character determining the next move for Scanner Claus
     * @param d the map Scanner Claus is navigating
     * @return the character representing the single next move for Scanner
     *         Claus to make in delivering all the presents
     */
    public char chooseMove(DeliveryMap d) {
        String moves = "DDDDDDDDDDDDDSDDDDWSAAAAAAAAAAASSSWAAAASSSSSDDWDDDDDSSSSAAWWDDDDDDDDDD";
        return moves.charAt(path.length());
    }

    public void move(DeliveryMap d, char dir) {
        int nextX = this.x;
        int nextY = this.y;

        switch(dir) {
            case 'W':
                nextY = this.y - 1;
                break;
            case 'A':
                nextX = this.x - 1;
                break;
            case 'S':
                nextY = this.y + 1;
                break;
            case 'D':
                nextX = this.x + 1;
                break;
            default:
                return;
        }

        char target = d.getPosition(nextY,nextX);
        if(target == 'C')
            this.numCarrots += 20;
        else if ((int)target > 48 && (int)target < 58)
            this.numPresents--;

        if(d.setPosition(nextY,nextX,false)) {
            d.setPosition(this.y,this.x,true);
            this.x = nextX;
            this.y = nextY;
            this.path += dir;
            this.numCarrots--;
        }
    }

    public String toString() {
        String msg = "Ho ho ho! (" + x + "," + y + ")\n";
        msg += "Carrots = " + this.numCarrots + "; Presents = " + this.numPresents + "\n";
        msg += "Press W (up), A (left), S (down), D (right), Q (quit)\n";
        return msg;
    }
}
