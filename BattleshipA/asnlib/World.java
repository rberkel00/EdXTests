public class World {
	private Boat[][] map;
	public final int NORTH = 0;
	public final int NORTHEAST = 1;
	public final int EAST = 2;
	public final int SOUTHEAST = 3;
	public final int SOUTH = 4;
	public final int SOUTHWEST = 5;
	public final int WEST = 6;
	public final int NORTHWEST = 7;

	public World(int width, int height) {
		if (width < 4) width = 4;
		if (width > 10) width = 10;
		if (height < 4) height = 4;
		if (height > 10) height = 10;
		map = new Boat[width][height];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = null;
			}
		}
	}

	public int getWidth() {
		return map.length;
	}

	public void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == null) System.out.println(i + " " + j + " " + "null");
				else System.out.println(i + " " + j + " " + map[i][j].toString());
			}
		}
	}

	public int getHeight() {
		return map[0].length;
	}

	public Boat getOccupant(Coordinates c) {
		return map[c.getX()][c.getY()];
	}

	public boolean isLocationValid(Coordinates c) {
		if (c.getX() >= 0 && c.getX() < getWidth()
			&& c.getY() >= 0 && c.getY() < getHeight()) {
			return true;
		}
		return false;
	}

	public boolean isLocationOccupied(Coordinates c) {
		if (map[c.getX()][c.getY()] == null) return false;
		return true;
	}

	public void empty(Coordinates c) {
		map[c.getX()][c.getY()] = null;
	}

	public boolean setOccupant(Boat o, Coordinates c) {
		if (isLocationValid(c) && !isLocationOccupied(c)) {
			map[c.getX()][c.getY()] = o;
			return true;
		}
		return false;
	}

	public Coordinates getAdjacentLocation(Coordinates c, int direction) {
		int x = c.getX();
		int y = c.getY();
		if (direction == NORTH) y--;
		if (direction == NORTHEAST) {
			y--;
			x++;
		}
		if (direction == NORTHWEST) {
			y--;
			x--;
		}
		if (direction == WEST) x--;
		if (direction == EAST) x++;
		if (direction == SOUTH) y++;
		if (direction == SOUTHEAST) {
			y++;
			x++;
		}
		if (direction == SOUTHWEST) {
			y++;
			x--;
		}
		Coordinates c2 = new Coordinates(x, y);
		if (isLocationValid(c2)) {
			return c2;
		}
		return null;
	}

	private char getChar(int dir) {
		char c;
		switch (dir) {
			case 0: c = '\u2191';
					break;
			case 1: c = '\u2197';
					break;
			case 2: c = '\u2192';
					break;
			case 3: c = '\u2198';
					break;
			case 4: c = '\u2193';
					break;
			case 5: c = '\u2199';
					break;
			case 6: c = '\u2190';
					break;
			case 7: c = '\u2196';
					break;
			default: c = '|';
		}
		return c;
	}

  public String drawTeamMap(Boat[] teamBoats, int view)
    {
		/*** NOTE: For this implementation, we will provide a general strategy and ask you to complete
		           certain tasks. The basic procedure of this method is as follows:

				   1) Create a two-dimensional map with all spaces marked as invisible
				   2) If the view is a player view (i.e., not the in-between view):
				         a) Loop through all of the current team's boats
						 b) For each boat that is still alive, get the endpoints for that boat's vision
						 c) Use the game map to determine whether to draw the water, or another boat for
						    each position within that boat's vision
						 d) Write the proper information (direction or health) along with the boat's
						    standard output
				   3) Adding the row and column headers, use your temporary map to build the map String
				      that is returned to the function caller
		***/
        String drawMap = "";
        String[][] teamView = new String[this.getWidth()][this.getHeight()];
        int yStart, yEnd, xStart, xEnd;
        Coordinates boatLocation;

        /*** TODO: Write a for-loop that repeats for each row in the map ***/
        for (int x = 0; x < this.getWidth(); x++){
            /*** TODO: Write a for-loop that repeats for each column in the map ***/
          for (int y = 0; y < this.getHeight(); y++)  {
                /*** TODO: Set each spot in the teamView array to the non-visible String (i.e., "###") ***/
                teamView[x][y] = "###";
            }
        }

        if (view != 1)
        {
            /*** TODO: Write a for-loop to repeat over all of the current team's boats, storing the current
                       boat in a Boat variable, b
			***/
            for (int i = 0; i < teamBoats.length; i++) {
              Boat b = teamBoats[i];
                /*** TODO: Write a conditional statement checking if the current boat has more than 0 health ***/
                if (b.getHealth() > 0) {
                    /*** TODO: Set yStart, yEnd, xStart and xEnd such that the for-loops below will iterate
					           over each visible space for the current boat, and will not produce an
							   ArrayIndexOutOfBoundsException (i.e., if a boat has vision 2, check 2 spaces
							   to the left and 2 to the right, but make sure not to index beyond the map
							   boundaries)
					***/
                Coordinates c = b.getLocation();
                if (b.getLocation().getY()-b.getVision()>=0) yStart = b.getLocation().getY()-b.getVision();
                else yStart = 0;
                if (b.getLocation().getY()+b.getVision()<=this.getHeight()-1) yEnd = b.getLocation().getY()+b.getVision();
                else yEnd = this.getHeight()-1;
                if (b.getLocation().getX()-b.getVision()>=0) xStart = b.getLocation().getX()-b.getVision();
                else xStart = 0;
                if (b.getLocation().getX()+b.getVision()<=this.getWidth()-1) xEnd = b.getLocation().getX()+b.getVision();
                else xEnd = this.getWidth()-1;

					for (int y = yStart; y <= yEnd; y++)
                    {
                        for (int x = xStart; x <= xEnd; x++)
                        {
                            /*** TODO: Write a conditional to check if the game map has nothing stored in the
							           current space (in which case, we'll store the water String)
                            ***/
                            if (!isLocationOccupied(new Coordinates(x,y)))
                                teamView[x][y] = "~~~";
                            else if (((Boat)map[x][y]).getHealth() <= 0)
                                teamView[x][y] = " ~ ";
                            else
                            {
                                /*** TODO: Write a conditional and the corresponding branches that add the boat
								           String with the appropriate information (location or direction) for
										   each boat that can be seen by the current boat
								***/
                                if (view == 2) teamView[x][y] = ((Boat)map[x][y]).getDirection() + "" + ((Boat)map[x][y]).getID();
                                else if (view == 3) teamView[x][y] = ((Boat)map[x][y]).getHealth() + "" + ((Boat)map[x][y]).getID();
                            }
                        }
                    }
                }
            }
        }

		/*** NOTE: The code below stores the map as a complete String. You should make sure that you understand
		           its functionality, but you do not need to make changes if you follow the instruction above
		***/
        for (int y = -1; y < this.getHeight(); y++)
        {
            drawMap += (char)(y + 65) + " ";
            for (int x = 0; x < this.getWidth(); x++)
            {
                if (y == -1)
                {
                    drawMap += " " + (x + 1) + ((x < 9) ? " " : "");
                }
                else
                {
                    drawMap += teamView[x][y];
                }
            }
            drawMap += "\n";
        }

        return drawMap;
    }

    public static void main(String[] args) {

    }
}
