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
				new TestCase("Test 1", "ParkingLot.java exists", "Completion", 1),
				new TestCase("Test 2", "ParkingSpace.java exists", "Completion", 1),
				new TestCase("Test 3", "ParkingLot.java compiles", "Completion", 1),
				new TestCase("Test 4", "ParkingSpace.java compiles", "Completion", 1),
				new TestCase("Test 5", "ParkingLot instance variables", "Completion", 1),
				new TestCase("Test 6", "ParkingLot enterLot method signature", "Completion", 1),
				new TestCase("Test 7", "ParkingLot leaveLot method signature", "Completion", 1),
				new TestCase("Test 8", "ParkingLot constructor signature", "Completion", 1),
				new TestCase("Test 9", "ParkingLot enterLot works correctly", "Completion", 1),
				new TestCase("Test 10", "ParkingLot leaveLot works correctly", "Completion", 1),
				new TestCase("Test 11", "ParkingLot constructor works correctly (initializes parkingLot)", "Completion", 1),
				new TestCase("Test 12", "ParkingLot constructor works correctly (places compact spaces correctly)", "Completion", 1),
				new TestCase("Test 13", "ParkingSpace constructor signature", "Completion", 1),
				new TestCase("Test 14", "ParkingSpace getSpaceNum signature", "Completion", 1),
				new TestCase("Test 15", "ParkingSpace getFloor signature", "Completion", 1),
				new TestCase("Test 16", "ParkingSpace getCompact signature", "Completion", 1),
				new TestCase("Test 17", "ParkingSpace getOccupied signature", "Completion", 1),
				new TestCase("Test 18", "ParkingSpace setOccupied signature", "Completion", 1),
				new TestCase("Test 19", "ParkingSpace toString signature", "Completion", 1),
				new TestCase("Test 20", "ParkingSpace toString works correctly", "Completion", 1),
				new TestCase("Test 21", "ParkingSpace constructor works correctly", "Completion", 1),
				new TestCase("Test 22", "ParkingSpace instance variables", "Completion", 1)
			};
			File lot = new File("ParkingLot.java");
			if (!lot.exists()) {
				System.out.println("ParkingLot.java was not found. Did you name your file correctly?");
				return;
			}
			tests[0].setResult(true);
			File space = new File("ParkingSpace.java");
			if (!space.exists()) {
				System.out.println("ParkingSpace.java was not found. Did you name your file correctly?");
				return;
			}
			tests[1].setResult(true);

			if (!TestCase.compile(new File("ParkingLot.java"))) {
				System.out.println("ParkingLot.java does not compile.");
			} else {
				tests[2].setResult(true);
			}

			if (!TestCase.compile(new File("ParkingSpace.java"))) {
				System.out.println("ParkingSpace.java does not compile.");
			} else {
				tests[3].setResult(true);
			}

			Parser lparser = new Parser();
			if (lparser.parse("ParkingLot.java")) {
				String[] methods = {"ParkingLot(int, int)","ParkingSpace enterLot(boolean)","void leaveLot(ParkingSpace)"};
				File[] files = lparser.replace(methods, "asnlib/ParkingLot.java");
				File tempf = new File("asnlib/ParkingSpace.java");
				File temps;
				if (files[0] != null) {
					temps = new File(files[0].getParent() + "/ParkingSpace.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[7].setResult(true);
					tests[10].setResult(TestCase.runMain(files[0].getParent(), "ParkingLot", null, "(?s).*1.*25.*2.*"));
					tests[11].setResult(TestCase.runMain(files[0].getParent(), "ParkingLot", null, "(?s).*2.*0 1 2 3 5 6 7 8 10 11 12 13 15 16 17 18 20 21 22 23.*3.*"));
				}
				if (files[1] != null) {
					temps = new File(files[1].getParent() + "/ParkingSpace.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[8].setResult(TestCase.runMain(files[1].getParent(), "ParkingLot", null, "(?s).*3.*A4 \\(N X\\).*A0 \\(C X\\).*B4 \\(N X\\).*C4 \\(N X\\).*"));
					tests[5].setResult(true);
				}
				if (files[2] != null) {
					tests[6].setResult(true);
					temps = new File(files[2].getParent() + "/ParkingSpace.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[9].setResult(TestCase.runMain(files[2].getParent(), "ParkingLot", null, "(?s).*4.*A4 \\(N O\\).*"));
				}
				boolean check;
				int[] vars = new int[3];
				for (FieldDeclaration f : lparser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("static")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (!check && vd.getType().asString().equals("ParkingSpace[]")) vars[0]++;
						if (!check && vd.getType().asString().equals("int")) vars[1]++;
					}
				}
				tests[4].setResult(vars[0] >= 1 && vars[1] >= 2);
			}

			Parser sparser = new Parser();
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
		}

}
}
