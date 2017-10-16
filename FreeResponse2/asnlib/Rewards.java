/** @author your_name
*/
import java.util.Scanner;

public class Rewards {

  public int points;

  /** @param points int to record current point level
  */
  public Rewards(int points) {

  	this.points = points;

  }

  /** @return String of possible choices
  */
  public String showRewards() {
  	//TODO: showRewards definition
    String str = "";
    if (points >= 50) {
      str += "1) free coffee - 50 pts";
      if (points >= 100) {
        str += "\n2) free baked good - 100 pts";
        if (points >= 200) {
          str += "\n3) free specialty coffee - 200 pts";
          if (points >= 5000) {
            str += "\n4) 1% store ownership - 5000 pts";
          }
        }
      }
    }
    return str;
  }

  /** @param choice int to
  *	  @return String reward chosen
  */
  public String getReward(int choice) {
	//TODO: getReward definition
    if (choice == 1 && points >= 50) {
      points -= 50;
      return "You have selected a free coffee!";
    }
    if (choice == 2 && points >= 100) {
      points -= 100;
      return "You have selected a free baked good!";
    }
    if (choice == 3 && points >= 200) {
      points -= 200;
      return "You have selected a free specialty coffee!";
    }
    if (choice == 4 && points >= 5000) {
      points -= 5000;
      return "You have selected a 1% store ownership!";
    }
    return "You do not have enough points for that reward.";
  }

  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Rewards r = new Rewards(read.nextInt());
    System.out.println(r.showRewards());
    if (read.nextInt() == 0) return;
    System.out.println(r.getReward(read.nextInt()));
  }

}
