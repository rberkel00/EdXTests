import java.util.Random;

public class BloodBank {

    double[][] donations;

    public BloodBank() {
        double[][] d = { { 5.5, 100.5 }, { 200.6 }, { 100.2, 220.7, 33.4 }, { 300.0 } };
        donations = d;
    }

    public int findTopDonor() {
        /*** TODO: Read each value in donations, noting the row and column of the maximum
               Calculate the final return value, row * 10 + column ***/
        double highest = 0;
        int high = 0;
        int count = 0;
        for (int i = 0; i < donations.length; i++) {
            for (int j = 0; j < donations[i].length; j++) {
                if (donations[i][j] > highest) {
                    high = count;
                    highest = donations[i][j];
                }
                count++;
            }
        }
        return high;
    }

    public int findTopState() {
        /*** TODO: Sum all values in a row of donations, noting the row of the maximum
               Return the maximum row ***/
        int high = 0;
        double highest = 0;
        double sum = 0;
        for (int i = 0; i < donations.length; i++) {
            sum = 0;
            for (int j = 0; j < donations[i].length; j++) {
                sum += donations[i][j];
            }
            if (sum > highest) {
                highest = sum;
                high = i;
            }
        }
        return high;
    }

    public void makeDonation(double amt, int row, int col) {
        /*** TODO: Update the donations value located at row and col by amt ***/
        donations[row][col] += amt;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < donations.length; i++) {
            for (int j = 0; j < donations[i].length; j++) {
                s = s + donations[i][j] + "\t";
            }
            s = s + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        BloodBank bd = new BloodBank();
        System.out.println(bd);
        int topDonor = bd.findTopDonor();
        System.out.println("The top donor is " + topDonor);
        System.out.println("The top state is " + bd.findTopState());
        bd.makeDonation(1.5, 0, 0);
        System.out.println("New value is " + bd.donations[0][0]);
    }
}
