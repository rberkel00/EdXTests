public class Practice {
     User[] users;

     public Practice() {
       users = new User[3];
       users[1] = new User("Joe",201408001);
       users[2] = new User("Alexa",201609014);
       users[3] = new User("Avinash",201702016);
     }

     public int getUser(String name) {
          for (int i = 0; i < users.length; i++) {
               if (users[i].name.equals(name)) return i;
          }
          return -1;
     }

     /*** TODO: Write the overloaded method for getUser that takes an integer, id
                and returns an integer ***/
     public int getUser(int id) {
          for (int i = 0; i < users.length; i++) {
               if (users[i].id == id) return i;
          }
          return -1;
     }

     public static void main(String[] args) {
          Practice p = new Practice();
          System.out.println("Alexa is at position " + p.getUser("Alexa"));
          System.out.println("201702016 is at position " + p.getUser(201702016));
     }
}
