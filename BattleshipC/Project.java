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
				new TestCase("Test 1", "AircraftCarrier has all required methods", "Completion", 1),
				new TestCase("Test 2", "Battleship has all required methods", "Completion", 1),
				new TestCase("Test 3", "Cruiser has all required methods", "Completion", 1),
				new TestCase("Test 4", "Destroyer has all required methods", "Completion", 1),
				new TestCase("Test 5", "Submarine has all required methods", "Completion", 1),
				new TestCase("Test 6", "AircraftCarrier constructor sets hasPlanes to true", "Completion", 1),
				new TestCase("Test 7", "AircraftCarrier act works correctly", "Completion", 1),
				new TestCase("Test 8", "AircraftCarrier getID works correctly", "Completion", 1),
				new TestCase("Test 9", "AircraftCarrier getActions works correctly", "Completion", 1),
				new TestCase("Test 10", "Battleship attack works correctly", "Completion", 1),
				new TestCase("Test 11", "Cruiser act works correctly", "Completion", 1),
				new TestCase("Test 12", "Destroyer attack works correctly", "Completion", 1),
				new TestCase("Test 13", "Submarine attack works correctly", "Completion", 1),
				new TestCase("Test 14", "Submarine submerge works correctly", "Completion", 1),
				new TestCase("Test 15", "AircraftCarrier implements and/or extends the correct interfaces and classes", "Completion", 1),
				new TestCase("Test 16", "Battleship implements and/or extends the correct interfaces and classes", "Completion", 1),
				new TestCase("Test 17", "Cruiser implements and/or extends the correct interfaces and classes", "Completion", 1),
				new TestCase("Test 18", "Destroyer implements and/or extends the correct interfaces and classes", "Completion", 1),
				new TestCase("Test 19", "Submarine implements and/or extends the correct interfaces and classes", "Completion", 1),

			};
			File a1 = new File("AircraftCarrier.java");
			if (!a1.exists()) {
				System.out.println("AircraftCarrier.java was not found. Did you name your file correctly?");
			}
			File b1 = new File("Battleship.java");
			if (!b1.exists()) {
				System.out.println("Battleship.java was not found. Did you name your file correctly?");
			}
			File c1 = new File("Cruiser.java");
			if (!c1.exists()) {
				System.out.println("Cruiser.java was not found. Did you name your file correctly?");
			}
			File d1 = new File("Destroyer.java");
			if (!d1.exists()) {
				System.out.println("Destroyer.java was not found. Did you name your file correctly?");
			}
			File s1 = new File("Submarine.java");
			if (!s1.exists()) {
				System.out.println("Submarine.java was not found. Did you name your file correctly?");
			}
			if (!TestCase.compile(new File("AircraftCarrier.java"))) {
				System.out.println("AircraftCarrier.java does not compile.");
			}
			if (!TestCase.compile(new File("Battleship.java"))) {
				System.out.println("Battleship.java does not compile.");
			}
			if (!TestCase.compile(new File("Cruiser.java"))) {
				System.out.println("Cruiser.java does not compile.");
			}
			if (!TestCase.compile(new File("Destroyer.java"))) {
				System.out.println("Destroyer.java does not compile.");
			}
			if (!TestCase.compile(new File("Submarine.java"))) {
				System.out.println("Submarine.java does not compile.");
			}
			Parser a = new Parser();
			if (a.parse("AircraftCarrier.java")) {
				String[] methods = {"AircraftCarrier(int, Coordinates, int)","String getID()","String getActions()", "String act(int[], World)", "String attack(World)"};
				File[] files = a.replace(methods, "asnlib/AircraftCarrier.java");
				boolean[] ms = new boolean[5];
				if (files[0] != null) {
					ms[0] = true;
					//todo
					File z = new File(files[0].getParent() + "/Battleship.java");
					File y = new File(files[0].getParent() + "/Cruiser.java");
					File x = new File(files[0].getParent() + "/Destroyer.java");
					File w = new File(files[0].getParent() + "/Submarine.java");
					File v = new File(files[0].getParent() + "/Runner.java");
					File u = new File(files[0].getParent() + "/World.java");
					File t = new File(files[0].getParent() + "/Coordinates.java");
					File zz = new File(files[0].getParent() + "/Boat.java");
					File yy = new File(files[0].getParent() + "/Attacker.java");
					File xx = new File(files[0].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[5].setResult(TestCase.runMain(files[0].getParent(), "Runner", null, "(?s).*01 passed.*"));
				}
				if (files[1] != null) {
					ms[1] = true;
					//todo
					File z = new File(files[1].getParent() + "/Battleship.java");
					File y = new File(files[1].getParent() + "/Cruiser.java");
					File x = new File(files[1].getParent() + "/Destroyer.java");
					File w = new File(files[1].getParent() + "/Submarine.java");
					File v = new File(files[1].getParent() + "/Runner.java");
					File u = new File(files[1].getParent() + "/World.java");
					File t = new File(files[1].getParent() + "/Coordinates.java");
					File zz = new File(files[1].getParent() + "/Boat.java");
					File yy = new File(files[1].getParent() + "/Attacker.java");
					File xx = new File(files[1].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[7].setResult(TestCase.runMain(files[1].getParent(), "Runner", null, "(?s).*03 passed.*"));
				}
				if (files[2] != null) {
					ms[2] = true;
					//todo
					File z = new File(files[2].getParent() + "/Battleship.java");
					File y = new File(files[2].getParent() + "/Cruiser.java");
					File x = new File(files[2].getParent() + "/Destroyer.java");
					File w = new File(files[2].getParent() + "/Submarine.java");
					File v = new File(files[2].getParent() + "/Runner.java");
					File u = new File(files[2].getParent() + "/World.java");
					File t = new File(files[2].getParent() + "/Coordinates.java");
					File zz = new File(files[2].getParent() + "/Boat.java");
					File yy = new File(files[2].getParent() + "/Attacker.java");
					File xx = new File(files[2].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[8].setResult(TestCase.runMain(files[2].getParent(), "Runner", null, "(?s).*01 passed.*"));
 				}
				if (files[3] != null) {
					ms[3] = true;
					//todo
					File z = new File(files[3].getParent() + "/Battleship.java");
					File y = new File(files[3].getParent() + "/Cruiser.java");
					File x = new File(files[3].getParent() + "/Destroyer.java");
					File w = new File(files[3].getParent() + "/Submarine.java");
					File v = new File(files[3].getParent() + "/Runner.java");
					File u = new File(files[3].getParent() + "/World.java");
					File t = new File(files[3].getParent() + "/Coordinates.java");
					File zz = new File(files[3].getParent() + "/Boat.java");
					File yy = new File(files[3].getParent() + "/Attacker.java");
					File xx = new File(files[3].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[6].setResult(TestCase.runMain(files[3].getParent(), "Runner", null, "(?s).*02 passed.*"));
				}
				if (files[4] != null) {
					ms[4] = true;
				}

				tests[0].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) tests[0].setResult(false);
				}

				boolean found = false;
				for (ClassOrInterfaceType co : a.extendedTypes) {
					if (co.toString().equals("Boat")) {
						found = true;
					}
				}
				for (ClassOrInterfaceType co : a.implementedTypes) {
					if (co.toString().equals("Attacker") && found) {
						tests[14].setResult(true);
					}
				}
			}

			Parser b = new Parser();
			if (b.parse("Battleship.java")) {
				String[] methods = {"Battleship(int, Coordinates, int)","String getID()","String getActions()", "String act(int[], World)", "String attack(World)"};
				File[] files = b.replace(methods, "asnlib/Battleship.java");
				boolean[] ms = new boolean[5];
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
					//todo
					File z = new File(files[4].getParent() + "/AircraftCarrier.java");
					File y = new File(files[4].getParent() + "/Cruiser.java");
					File x = new File(files[4].getParent() + "/Destroyer.java");
					File w = new File(files[4].getParent() + "/Submarine.java");
					File v = new File(files[4].getParent() + "/Runner.java");
					File u = new File(files[4].getParent() + "/World.java");
					File t = new File(files[4].getParent() + "/Coordinates.java");
					File zz = new File(files[4].getParent() + "/Boat.java");
					File yy = new File(files[4].getParent() + "/Attacker.java");
					File xx = new File(files[4].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/AircraftCarrier.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[9].setResult(TestCase.runMain(files[4].getParent(), "Runner", null, "(?s).*04 passed.*"));
				}

				tests[1].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) tests[1].setResult(false);
				}

				boolean found = false;
				for (ClassOrInterfaceType co : b.extendedTypes) {
					if (co.toString().equals("Boat")) {
						found = true;
					}
				}
				for (ClassOrInterfaceType co : b.implementedTypes) {
					if (co.toString().equals("Attacker") && found) {
						tests[15].setResult(true);
					}
				}
			}

			Parser c = new Parser();
			if (c.parse("Cruiser.java")) {
				String[] methods = {"Cruiser(int, Coordinates, int)","String getID()","String getActions()", "String act(int[], World)"};
				File[] files = c.replace(methods, "asnlib/Cruiser.java");
				boolean[] ms = new boolean[4];
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
					//todo
					File z = new File(files[3].getParent() + "/Battleship.java");
					File y = new File(files[3].getParent() + "/AircraftCarrier.java");
					File x = new File(files[3].getParent() + "/Destroyer.java");
					File w = new File(files[3].getParent() + "/Submarine.java");
					File v = new File(files[3].getParent() + "/Runner.java");
					File u = new File(files[3].getParent() + "/World.java");
					File t = new File(files[3].getParent() + "/Coordinates.java");
					File zz = new File(files[3].getParent() + "/Boat.java");
					File yy = new File(files[3].getParent() + "/Attacker.java");
					File xx = new File(files[3].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/AircraftCarrier.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[10].setResult(TestCase.runMain(files[3].getParent(), "Runner", null, "(?s).*05 passed.*"));
				}

				tests[2].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) tests[2].setResult(false);
				}

				boolean found = false;
				if (c.extendedTypes != null) {
					for (ClassOrInterfaceType co : c.extendedTypes) {
						if (co.toString().equals("ScoutBoat")) {
							tests[16].setResult(true);
						}
					}
				}
			}

			Parser d = new Parser();
			if (d.parse("Destroyer.java")) {
				String[] methods = {"Destroyer(int, Coordinates, int)","String getID()","String getActions()", "String act(int[], World)", "String attack(World)", "String takeHit(int)"};
				File[] files = d.replace(methods, "asnlib/Destroyer.java");
				boolean[] ms = new boolean[6];
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
					//todo
					File z = new File(files[4].getParent() + "/Battleship.java");
					File y = new File(files[4].getParent() + "/Cruiser.java");
					File x = new File(files[4].getParent() + "/AircraftCarrier.java");
					File w = new File(files[4].getParent() + "/Submarine.java");
					File v = new File(files[4].getParent() + "/Runner.java");
					File u = new File(files[4].getParent() + "/World.java");
					File t = new File(files[4].getParent() + "/Coordinates.java");
					File zz = new File(files[4].getParent() + "/Boat.java");
					File yy = new File(files[4].getParent() + "/Attacker.java");
					File xx = new File(files[4].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/AircraftCarrier.java");
					File w1 = new File("asnlib/Submarine.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[11].setResult(TestCase.runMain(files[4].getParent(), "Runner", null, "(?s).*06 passed.*"));
				}
				if (files[5] != null) {
					ms[5] = true;
				}

				tests[3].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) tests[3].setResult(false);
				}

				boolean found = false;
				for (ClassOrInterfaceType co : d.extendedTypes) {
					if (co.toString().equals("Boat")) {
						found = true;
					}
				}
				for (ClassOrInterfaceType co : d.implementedTypes) {
					if (co.toString().equals("Attacker") && found) {
						tests[17].setResult(true);
					}
				}
			}

			Parser s = new Parser();
			if (s.parse("Submarine.java")) {
				String[] methods = {"Submarine(int, Coordinates, int, int)","String getID()","String getActions()", "String act(int[], World)", "String attack(World)", "String submerge(World)"};
				File[] files = s.replace(methods, "asnlib/Submarine.java");
				boolean[] ms = new boolean[6];
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
					//todo
					File z = new File(files[4].getParent() + "/Battleship.java");
					File y = new File(files[4].getParent() + "/Cruiser.java");
					File x = new File(files[4].getParent() + "/Destroyer.java");
					File w = new File(files[4].getParent() + "/AircraftCarrier.java");
					File v = new File(files[4].getParent() + "/Runner.java");
					File u = new File(files[4].getParent() + "/World.java");
					File t = new File(files[4].getParent() + "/Coordinates.java");
					File zz = new File(files[4].getParent() + "/Boat.java");
					File yy = new File(files[4].getParent() + "/Attacker.java");
					File xx = new File(files[4].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/AircraftCarrier.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[12].setResult(TestCase.runMain(files[4].getParent(), "Runner", null, "(?s).*07 passed.*"));
				}
				if (files[5] != null) {
					ms[5] = true;
					//todo
					File z = new File(files[5].getParent() + "/Battleship.java");
					File y = new File(files[5].getParent() + "/Cruiser.java");
					File x = new File(files[5].getParent() + "/Destroyer.java");
					File w = new File(files[5].getParent() + "/AircraftCarrier.java");
					File v = new File(files[5].getParent() + "/Runner.java");
					File u = new File(files[5].getParent() + "/World.java");
					File t = new File(files[5].getParent() + "/Coordinates.java");
					File zz = new File(files[5].getParent() + "/Boat.java");
					File yy = new File(files[5].getParent() + "/Attacker.java");
					File xx = new File(files[5].getParent() + "/ScoutBoat.java");
					File z1 = new File("asnlib/Battleship.java");
					File y1 = new File("asnlib/Cruiser.java");
					File x1 = new File("asnlib/Destroyer.java");
					File w1 = new File("asnlib/AircraftCarrier.java");
					File v1 = new File("asnlib/Runner.java");
					File u1 = new File("asnlib/World.java");
					File t1 = new File("asnlib/Coordinates.java");
					File zz1 = new File("asnlib/Boat.java");
					File yy1 = new File("asnlib/Attacker.java");
					File xx1 = new File("asnlib/ScoutBoat.java");
					Files.copy(z1.toPath(), z.toPath());
					Files.copy(y1.toPath(), y.toPath());
					Files.copy(x1.toPath(), x.toPath());
					Files.copy(w1.toPath(), w.toPath());
					Files.copy(v1.toPath(), v.toPath());
					Files.copy(u1.toPath(), u.toPath());
					Files.copy(t1.toPath(), t.toPath());
					Files.copy(zz1.toPath(), zz.toPath());
					Files.copy(yy1.toPath(), yy.toPath());
					Files.copy(xx1.toPath(), xx.toPath());
					TestCase.compile(t);
					TestCase.compile(zz);
					TestCase.compile(u);
					TestCase.compile(yy);
					TestCase.compile(xx);
					TestCase.compile(z);
					TestCase.compile(y);
					TestCase.compile(x);
					TestCase.compile(w);
					TestCase.compile(v);
					//TestCase.compile(files[0]);
					tests[13].setResult(TestCase.runMain(files[5].getParent(), "Runner", null, "(?s).*08 passed.*"));
				}

				tests[4].setResult(true);
				for (int i = 0; i < ms.length; i++) {
					if (!ms[i]) tests[4].setResult(false);
				}

				boolean found = false;
				for (ClassOrInterfaceType co : s.extendedTypes) {
					if (co.toString().equals("ScoutBoat")) {
						found = true;
					}
				}
				for (ClassOrInterfaceType co : s.implementedTypes) {
					if (co.toString().equals("Attacker") && found) {
						tests[18].setResult(true);
					}
				}
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
