public class Playlist {
  String[] nextFive;
  public Playlist() {
    nextFive = new String[5];
  }
  public boolean makeRequest(String s) {
    if (searchList(s) == -1) {
      for (int i = 0; i < 5; i++) {
        if (nextFive[i] == null) {
          addSong(s);
          return true;
        }
      }
    }
    return false;
  }
  public int searchList(String s) {
    for (int i = 0; i < 5; i++) {
      if (nextFive[i].equals(s)) {
        return i;
      }
    }
    return -1;
  }
  public void addSong(String s) {
    for (int i = 0; i < 5; i++) {
      if (nextFive[i] == null) {
        nextFive[i] = s;
        return;
      }
    }
  }
  public String getNextSong() {
    String result =  nextFive[0];
    for (int i = 1; i < 5; i++) {
      nextFive[i-1] = nextFive[i];
      nextFive[i] = null;
    }
    return result;
  }

}
