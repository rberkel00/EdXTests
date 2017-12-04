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

		//define tests
		try {
			TestCase[] tests = {
				new TestCase("Test 1", "Room.java exists", "Completion", 1),
				new TestCase("Test 2", "Hotel.java exists", "Completion", 1),
				new TestCase("Test 3", "Room.java compiles", "Completion", 1),
				new TestCase("Test 4", "Hotel.java compiles", "Completion", 1),
				new TestCase("Test 5", "Room instance variables exist", "Completion", 1),
				new TestCase("Test 6", "Room instance variables are private", "Completion", 1),
				new TestCase("Test 7", "Room accessor method signatures are correct", "Completion", 1),
				new TestCase("Test 8", "Room advanceDay method signature", "Completion", 1),
				new TestCase("Test 9", "Room setOccupant method signature", "Completion", 1),
				new TestCase("Test 10", "Room toString method signature", "Completion", 1),
				new TestCase("Test 11", "Room accessor methods work correctly", "Completion", 1),
				new TestCase("Test 12", "Room setOccupant works if room is empty", "Completion", 1),
				new TestCase("Test 13", "Room setOccupant works if room is full", "Completion", 1),
				new TestCase("Test 14", "Room advanceDay works if daysRented becomes 0", "Completion", 1),
				new TestCase("Test 15", "Room advanceDays works if daysRented becomes greater than 0", "Completion", 1),
				new TestCase("Test 16", "Room toString formats output correctly", "Completion", 1),
				new TestCase("Test 17", "Room constructor exists", "Completion", 1),

				new TestCase("Test 18", "ParkingSpace setOccupied signature", "Completion", 1),
				new TestCase("Test 19", "ParkingSpace toString signature", "Completion", 1),
				new TestCase("Test 20", "", "Completion", 1),
				new TestCase("Test 21", "", "Completion", 1),
				new TestCase("Test 22", "", "Completion", 1)
			};
			File lot = new File("Room.java");
			if (!lot.exists()) {
				System.out.println("Room.java was not found. Did you name your file correctly?");
			}
			else tests[0].setResult(true);
			File space = new File("Hotel.java");
			if (!space.exists()) {
				System.out.println("Hotel.java was not found. Did you name your file correctly?");
			}
			else tests[1].setResult(true);

			if (!TestCase.compile(new File("Room.java"))) {
				System.out.println("Room.java does not compile.");
			} else {
				tests[2].setResult(true);
			}

			if (!TestCase.compile(new File("Hotel.java"))) {
				System.out.println("Hotel.java does not compile.");
			} else {
				tests[3].setResult(true);
			}


			Parser lparser = new Parser();
			if (lparser.parse("Room.java")) {
				String[] methods = {"Room(int, String)","int getRoomNumber()","String getRoomType()", "String getOccupantName()", "int getDaysRented()", "boolean setOccupant(String, int)", "void advanceDay()", "String toString()"};
				File[] files = lparser.replace(methods, "asnlib/Room.java");
				boolean[] acc = new boolean[4];
				boolean[] acc2 = new boolean[4];


				if (files[0] != null) {
					tests[16].setResult(true);
					//tests[10].setResult(TestCase.runMain(files[0].getParent(), "ParkingLot", null, "(?s).*1.*25.*2.*"));
					//tests[11].setResult(TestCase.runMain(files[0].getParent(), "ParkingLot", null, "(?s).*2.*0 1 2 3 5 6 7 8 10 11 12 13 15 16 17 18 20 21 22 23.*3.*"));
				}
				if (files[1] != null) {
					acc[0] = true;
					acc2[0] = TestCase.runMain(files[1].getParent(), "Room", null, "(?s).*111.*4.*222.*");
				}
				if (files[2] != null) {
					acc[1] = true;
					acc2[1] = TestCase.runMain(files[2].getParent(), "Room", null, "(?s).*333.*single king.*444.*");
				}
				if (files[3] != null) {
					acc[2] = true;
					acc2[2] = TestCase.runMain(files[3].getParent(), "Room", null, "(?s).*444.*null.*555.*");
				}
				if (files[4] != null) {
					acc[3] = true;
					acc2[3] = TestCase.runMain(files[4].getParent(), "Room", null, "(?s).*222.*0.*333.*");
				}
				if (files[5] != null) {
					tests[8].setResult(true);
					tests[11].setResult(TestCase.runMain(files[5].getParent(), "Room", null, "(?s).*555.*true.*666.*"));
					tests[12].setResult(TestCase.runMain(files[5].getParent(), "Room", null, "(?s).*666.*false.*777.*"));
				}
				if (files[6] != null) {
					tests[7].setResult(true);
					tests[13].setResult(TestCase.runMain(files[6].getParent(), "Room", null, "(?s).*999.*null.*101.*"));
					tests[14].setResult(TestCase.runMain(files[6].getParent(), "Room", null, "(?s).*888.*1.*999.*"));
				}
				if (files[7] != null) {
					tests[9].setResult(true);
					tests[15].setResult(TestCase.runMain(files[7].getParent(), "Room", null, "(?s).*101.*Room.*4.*.*single.*king.*free.*"));
				}

				tests[6].setResult(true);
				tests[10].setResult(true);
				for (int i = 0; i < 4; i++) {
					if (!acc[i]) {
						tests[6].setResult(false);
					}
					if (!acc2[i]) {
						tests[10].setResult(false);
					}
				}

				int[] vars = new int[4];
				boolean check;
				for (FieldDeclaration f : lparser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("private")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (vd.getName().asString().equals("roomNumber") && vd.getType().asString().equals("int")) {
							if (check) vars[0] = 2;
							else vars[0] = 1;
						} else if (vd.getName().asString().equals("roomType") && vd.getType().asString().equals("String")) {
							if (check) vars[1] = 2;
							else vars[1] = 1;
						} else if (vd.getName().asString().equals("daysRented") && vd.getType().asString().equals("int")) {
							if (check) vars[2] = 2;
							else vars[2] = 1;
						} else if (vd.getName().asString().equals("occupantName") && vd.getType().asString().equals("String")) {
							if (check) vars[3] = 2;
							else vars[3] = 1;
						}
					}
				}

				tests[4].setResult(true);
				tests[5].setResult(true);
				for (int i : vars) {
					if (i < 2) tests[5].setResult(false);
					if (i < 1) tests[4].setResult(false);
				}
			}

			/*Parser sparser = new Parser();
			if (sparser.parse("ParkingSpace.java")) {
				String[] methods = {"ParkingSpace(int, char, boolean)","int getSpaceNum()","char getFloor()","boolean getCompact()","boolean getOccupied()","void setOccupied(boolean)","String toString()"};
				File[] files = sparser.replace(methods, "asnlib/ParkingSpace.java");
				File tempf = new File("asnlib/ParkingLot.java");
				File temps;
				int count = 0;
				if (files[0] != null) {
					temps = new File(files[0].getParent() + "/ParkingLot.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[12].setResult(true);
					tests[20].setResult(TestCase.runMain(files[0].getParent(), "ParkingSpace", null, "(?s).*6.*A2 \\(C X\\).*"));
				}
				if (files[1] != null) {
					tests[13].setResult(true);
				}
				if (files[2] != null) {
					tests[14].setResult(true);
				}
				if (files[3] != null) {
					tests[15].setResult(true);
				}
				if (files[4] != null) {
					tests[16].setResult(true);
				}
				if (files[5] != null) {
					tests[17].setResult(true);
				}
				if (files[6] != null) {
					temps = new File(files[6].getParent() + "/ParkingLot.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[18].setResult(true);
					tests[19].setResult(TestCase.runMain(files[6].getParent(), "ParkingSpace", null, "(?s).*6.*A2 \\(C X\\).*"));
				}
				if (count == 4) tests[19].setResult(true);
				int[] vars = new int[4];
				boolean check;
				for (FieldDeclaration f : sparser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("static")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (!check && vd.getType().asString().equals("int")) vars[0]++;
						if (!check && vd.getType().asString().equals("char")) vars[1]++;
						if (!check && vd.getType().asString().equals("boolean")) vars[2]++;
					}
				}
				tests[21].setResult(vars[0] >= 1 && vars[1] >= 1 && vars[2] >= 2);
			}

 			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

}
}
