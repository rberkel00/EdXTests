import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.github.javaparser.ast.comments.*;
import com.github.javaparser.ast.body.*;
import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import com.github.javaparser.ast.expr.*;
import com.github.javaparser.metamodel.*;
import com.github.javaparser.ast.stmt.*;
import com.github.javaparser.ast.type.*;
public class Project {
	public static void main(String[] args) {
		try {
			boolean script = false;
			if (args.length > 0) script = true;
			TestCase[] tests = {
				new TestCase("Test 1", "Coordinates constructor sets values to zero in case of no parameters", "Completion", 1),
				new TestCase("Test 2", "Coordinates method signatures exist", "Completion", 1),
				new TestCase("Test 3", "Coordinates toString formats correctly", "Completion", 1),

				//30 * 2
				new TestCase("Test 4", "World constructor checks validity of parameters and corrects if necessary", "Completion", 1),

				//40 *3
				new TestCase("Test 5", "World isLocationValid works correctly", "Completion", 1),

				new TestCase("Test 6", "World method signatures exist", "Completion", 1),
				//50 *3
				new TestCase("Test 7", "World setOccupant makes the appropriate checks before setting values", "Completion", 1),
				//60
				new TestCase("Test 8", "World getAdjacentLocation returns correct value", "Completion", 1),
				//70
				new TestCase("Test 9", "World getAdjacentLocation checks that location is valid and returns null if not", "Completion", 1),
				//80
				new TestCase("Test 10", "World drawTeamMap works when view=1", "Completion", 1),
				//90
				new TestCase("Test 11", "World drawTeamMap works when view=2", "Completion", 1),
				//21
				new TestCase("Test 12", "World drawTeamMap works when view=3", "Completion", 1),

				new TestCase("Test 13", "Boat has three abstract methods declared correctly", "Completion", 1),
				new TestCase("Test 14", "Boat non-abstract method signatures are correct", "Completion", 1),
				new TestCase("Test 15", "Boat move method returns appropriate message if move will take it off the map", "Completion", 1),
				new TestCase("Test 16", "Boat move method returns appropriate message if move is on occupied spot", "Completion", 1),
				new TestCase("Test 17", "Boat move method moves boat and returns appropriate message in case of success", "Completion", 1),
				new TestCase("Test 18", "Boat turn method works correctly", "Completion", 1),
				new TestCase("Test 19", "Boat takeHit makes correct checks", "Completion", 1),
			};
			File it = new File("Coordinates.java");
			if (!it.exists()) {
				System.out.println("Coordinates.java was not found. Did you name your file correctly?");
			}
			File si = new File("World.java");
			if (!si.exists()) {
				System.out.println("World.java was not found. Did you name your file correctly?");
			}
			File us = new File("Boat.java");
			if (!us.exists()) {
				System.out.println("Boat.java was not found. Did you name your file correctly?");
			}
			if (!TestCase.compile(new File("Coordinates.java"))) {
				System.out.println("Coordinates.java does not compile.");
			}
			if (!TestCase.compile(new File("World.java"))) {
				System.out.println("World.java does not compile.");
			}
			if (!TestCase.compile(new File("Boat.java"))) {
				System.out.println("Boat.java does not compile.");
			}

			Parser itp = new Parser();
			if (itp.parse("Coordinates.java")) {
				String[] methods = {"Coordinates()","Coordinates(int, int)","int getX()", "int getY()", "void setCoordinates(int, int)", "String toString()"};
				File[] files = itp.replace(methods, "asnlib/Coordinates.java");
				boolean[] ms = new boolean[5];
				if (files[0] != null) {
					ms[0] = true;
					File a = new File(files[0].getParent() + "/Boat.java");
					File b = new File(files[0].getParent() + "/BoatA.java");
					File c = new File(files[0].getParent() + "/World.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/World.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[0]);
					TestCase.compile(a);
					TestCase.compile(c);
					TestCase.compile(b);
					tests[0].setResult(TestCase.runMain(files[0].getParent(), "BoatA", null, "(?s).*Pass 20.*"));
				}
				if (files[1] != null) {
					ms[1] = true;
				}
				if (files[2] != null) {
					ms[2] = true;
 				}
				if (files[3] != null) {
					ms[3] = true;
				}
				if (files[4] != null) {
					ms[4] = true;
					File a = new File(files[4].getParent() + "/Boat.java");
					File b = new File(files[4].getParent() + "/BoatA.java");
					File c = new File(files[4].getParent() + "/World.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/World.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[4]);
					TestCase.compile(a);
					TestCase.compile(c);
					TestCase.compile(b);
					tests[2].setResult(TestCase.runMain(files[4].getParent(), "BoatA", null, "(?s).*Pass 31.*"));
				}

				tests[1].setResult(true);
				for (int i = 0; i < 5; i++) {
					if (!ms[i]) {
						tests[1].setResult(false);
					}
				}
			}

			Parser sip = new Parser();
			if (sip.parse("Boat.java")) {
				String[] methods = {"Boat(int, Coordinates, int, int, int, int)","int getTeam()","Coordinates getLocation()", "int getDirection()", "int getHealth()", "int getStrength()", "int getVision()", "String move(World)", "String turn(int)", "String takeHit(int)", "void setLocation(Coordinates)", "String toString()"};
				File[] files = sip.replace(methods, "asnlib/Boat.java");
				boolean[] ms = new boolean[12];
				if (files[0] != null) {
					ms[0] = true;
				}
				if (files[1] != null) {
					ms[1] = true;
				}
				if (files[2] != null) {
					ms[2] = true;
	 			}
				if (files[3] != null) {
					ms[3] = true;
				}
				if (files[4] != null) {
					ms[4] = true;
				}
				if (files[5] != null) {
					ms[5] = true;
				}
				if (files[6] != null) {
					ms[6] = true;
				}
				if (files[7] != null) {
					ms[7] = true;
					File a = new File(files[7].getParent() + "/Coordinates.java");
					File b = new File(files[7].getParent() + "/BoatA.java");
					File c = new File(files[7].getParent() + "/World.java");
					File a1 = new File("asnlib/Coordinates.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/World.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[7]);
					TestCase.compile(a);
					TestCase.compile(c);
					TestCase.compile(b);
					tests[14].setResult(TestCase.runMain(files[7].getParent(), "BoatA", null, "(?s).*Pass 41.*"));
					tests[15].setResult(TestCase.runMain(files[7].getParent(), "BoatA", null, "(?s).*Pass 51.*"));
					tests[16].setResult(TestCase.runMain(files[7].getParent(), "BoatA", null, "(?s).*Pass 61.*"));
				}
				if (files[8] != null) {
					ms[8] = true;
					File a = new File(files[8].getParent() + "/Coordinates.java");
					File b = new File(files[8].getParent() + "/BoatA.java");
					File c = new File(files[8].getParent() + "/World.java");
					File a1 = new File("asnlib/Coordinates.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/World.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[8]);
					TestCase.compile(a);
					TestCase.compile(c);
					TestCase.compile(b);
					tests[17].setResult(TestCase.runMain(files[8].getParent(), "BoatA", null, "(?s).*Pass 71.*"));
				}
				if (files[9] != null) {
					ms[9] = true;
					File a = new File(files[9].getParent() + "/Coordinates.java");
					File b = new File(files[9].getParent() + "/BoatA.java");
					File c = new File(files[9].getParent() + "/World.java");
					File a1 = new File("asnlib/Coordinates.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/World.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[9]);
					TestCase.compile(a);
					TestCase.compile(c);
					TestCase.compile(b);
					tests[18].setResult(TestCase.runMain(files[9].getParent(), "BoatA", null, "(?s).*Pass 81.*Pass 81.*"));
				}
				if (files[10] != null) {
					ms[10] = true;
				}
				if (files[11] != null) {
					ms[11] = true;
				}

				tests[13].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) {
						tests[13].setResult(false);
					}
				}

				//three abstract methods
				boolean[] ams = new boolean[3];
				for (MethodDeclaration m : sip.methods) {
					if (m.getDeclarationAsString(true, false, false).equals("public abstract String getID()")) {
						ams[0] = true;
					} else if (m.getDeclarationAsString(true, false, false).equals("public abstract String act(int[], World)")) {
						ams[1] = true;
					} else if (m.getDeclarationAsString(true, false, false).equals("public abstract String getActions()")) {
						ams[2] = true;
					}
				}
				tests[12].setResult(true);
				for (int i = 0; i < ams.length; i++) {
					if (!ams[i]) {
						tests[12].setResult(false);
					}
				}

			}

			Parser usp = new Parser();
			if (usp.parse("World.java")) {
				String[] methods = {"World(int, int)","int getWidth()","int getHeight()", "Boat getOccupant(Coordinates)", "boolean isLocationValid(Coordinates)", "boolean isLocationOccupied(Coordinates)", "void empty(Coordinates)", "boolean setOccupant(Boat, Coordinates)", "Coordinates getAdjacentLocation(Coordinates, int)", "String drawTeamMap(Boat[], int)"};
				File[] files = usp.replace(methods, "asnlib/World.java");
				boolean[] ms = new boolean[10];
				if (files[0] != null) {
					ms[0] = true;
					File a = new File(files[0].getParent() + "/Boat.java");
					File b = new File(files[0].getParent() + "/BoatA.java");
					File c = new File(files[0].getParent() + "/Coordinates.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/Coordinates.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[0]);
					TestCase.compile(c);
					TestCase.compile(a);
					TestCase.compile(b);
					tests[3].setResult(TestCase.runMain(files[0].getParent(), "BoatA", null, "(?s).*Pass 30.*Pass 30.*"));
				}
				if (files[1] != null) {
					ms[1] = true;
				}
				if (files[2] != null) {
					ms[2] = true;
 				}
				if (files[3] != null) {
					ms[3] = true;
				}
				if (files[4] != null) {
					ms[4] = true;
					File a = new File(files[4].getParent() + "/Boat.java");
					File b = new File(files[4].getParent() + "/BoatA.java");
					File c = new File(files[4].getParent() + "/Coordinates.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/Coordinates.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[4]);
					TestCase.compile(c);
					TestCase.compile(a);
					TestCase.compile(b);
					tests[4].setResult(TestCase.runMain(files[4].getParent(), "BoatA", null, "(?s).*Pass 40.*Pass 40.*Pass 40.*"));
				}
				if (files[5] != null) {
					ms[5] = true;
				}
				if (files[6] != null) {
					ms[6] = true;
				}
				if (files[7] != null) {
					ms[7] = true;
					File a = new File(files[7].getParent() + "/Boat.java");
					File b = new File(files[7].getParent() + "/BoatA.java");
					File c = new File(files[7].getParent() + "/Coordinates.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/Coordinates.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[7]);
					TestCase.compile(c);
					TestCase.compile(a);
					TestCase.compile(b);
					tests[6].setResult(TestCase.runMain(files[7].getParent(), "BoatA", null, "(?s).*Pass 50.*Pass 50.*Pass 50.*"));
				}
				if (files[8] != null) {
					ms[8] = true;
					File a = new File(files[8].getParent() + "/Boat.java");
					File b = new File(files[8].getParent() + "/BoatA.java");
					File c = new File(files[8].getParent() + "/Coordinates.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/Coordinates.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[8]);
					TestCase.compile(c);
					TestCase.compile(a);
					TestCase.compile(b);
					tests[7].setResult(TestCase.runMain(files[8].getParent(), "BoatA", null, "(?s).*Pass 60.*"));
					tests[8].setResult(TestCase.runMain(files[8].getParent(), "BoatA", null, "(?s).*Pass 70.*"));
				}
				if (files[9] != null) {
					ms[9] = true;
					File a = new File(files[9].getParent() + "/Boat.java");
					File b = new File(files[9].getParent() + "/BoatA.java");
					File c = new File(files[9].getParent() + "/Coordinates.java");
					File a1 = new File("asnlib/Boat.java");
					File b1 = new File("asnlib/BoatA.java");
					File c1 = new File("asnlib/Coordinates.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					TestCase.compile(files[9]);
					TestCase.compile(c);
					TestCase.compile(a);
					TestCase.compile(b);
					tests[9].setResult(TestCase.runMain(files[9].getParent(), "BoatA", null, "(?s).*Pass 80.*"));
					tests[10].setResult(TestCase.runMain(files[9].getParent(), "BoatA", null, "(?s).*Pass 90.*"));
					tests[11].setResult(TestCase.runMain(files[9].getParent(), "BoatA", null, "(?s).*Pass 21.*"));
				}

				tests[5].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) {
						tests[5].setResult(false);
					}
				}
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
