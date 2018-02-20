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
				new TestCase("Test 1", "PayCommuter.java compiles", "Completion", 1),
				new TestCase("Test 2", "BusCommuter.java compiles", "Completion", 1),
				new TestCase("Test 3", "PayCommuter.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 4", "BusCommuter.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 5", "PayCommuter extends Commuter", "Completion", 1),
				new TestCase("Test 6", "BusCommuter extends PayCommuter", "Completion", 1),
				new TestCase("Test 7", "PayCommuter constructor uses the super constructor", "Completion", 1),
				new TestCase("Test 8", "PayCommuter addMiles works correctly", "Completion", 1),
				new TestCase("Test 9", "PayCommuter payFare works correctly", "Completion", 1),
				new TestCase("Test 10", "BusCommuter constructor uses the super constructor", "Completion", 1),
				new TestCase("Test 11", "BusCommuter addMiles works correctly ", "Completion", 1),
				new TestCase("Test 12", "BusCommuter buyBusPass works correctly", "Completion", 1)
			};
			File pc = new File("PayCommuter.java");
			if (!pc.exists()) {
				System.out.println("PayCommuter.java was not found. Did you name your file correctly?");
			}
			File bc = new File("BusCommuter.java");
			if (!bc.exists()) {
				System.out.println("BusCommuter.java was not found. Did you name your file correctly?");
			}
			if (!TestCase.compile(new File("PayCommuter.java"))) {
				System.out.println("PayCommuter.java does not compile.");
			} else {
				tests[0].setResult(true);
			}
			if (!TestCase.compile(new File("BusCommuter.java"))) {
				System.out.println("BusCommuter.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser itp = new Parser();
			if (itp.parse("PayCommuter.java")) {
				String[] methods = {"PayCommuter(String, double)","boolean getPaid()","double getFare()", "void payFare(double)", "void addMiles(int)", "String toString()"};
				File[] files = itp.replace(methods, "asnlib/PayCommuter.java");
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
					File a = new File(files[3].getParent() + "/Commuter.java");
					File b = new File(files[3].getParent() + "/BusCommuter.java");
					File a1 = new File("asnlib/Commuter.java");
					File b1 = new File("asnlib/BusCommuter.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					TestCase.compile(a);
					TestCase.compile(b);
					tests[7].setResult(TestCase.runMain(files[3].getParent(), "PayCommuter", null, "(?s).*check1: false 0.*check2: true 5.*"));
				}
				if (files[4] != null) {
					ms[4] = true;
					File a = new File(files[4].getParent() + "/Commuter.java");
					File b = new File(files[4].getParent() + "/BusCommuter.java");
					File a1 = new File("asnlib/Commuter.java");
					File b1 = new File("asnlib/BusCommuter.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					TestCase.compile(a);
					TestCase.compile(b);
					tests[8].setResult(TestCase.runMain(files[4].getParent(), "PayCommuter", null, "(?s).*check1: false 0.*check2: true 5.*"));
				}
				if (files[5] != null) {
					ms[5] = true;
				}
				tests[2].setResult(true);
				for (int i = 0; i < 6; i++) {
					if (!ms[i]) {
						tests[2].setResult(false);
					}
				}
				for(ClassOrInterfaceType t : itp.extendedTypes) {
					if (t.asString().equals("Commuter")) tests[4].setResult(true);
				}
				List<Node> list = itp.findPieces("PayCommuter(String, double)");
				for (Node n : list) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						ExplicitConstructorInvocationStmt ecis = (ExplicitConstructorInvocationStmt)n;
						if (!ecis.isThis()) {
							tests[6].setResult(true);
						}
					}
				}
			}

			Parser sip = new Parser();
			if (sip.parse("BusCommuter.java")) {
				String[] methods = {"BusCommuter(String, double, double)","void buyBusPass(double)","void addMiles(int)", "String toString()"};
				File[] files = sip.replace(methods, "asnlib/BusCommuter.java");
				boolean[] ms = new boolean[4];
				if (files[0] != null) {
					ms[0] = true;
				}
				if (files[1] != null) {
					ms[1] = true;
					File a = new File(files[1].getParent() + "/Commuter.java");
					File b = new File(files[1].getParent() + "/PayCommuter.java");
					File a1 = new File("asnlib/Commuter.java");
					File b1 = new File("asnlib/PayCommuter.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					TestCase.compile(a);
					TestCase.compile(b);
					tests[10].setResult(TestCase.runMain(files[1].getParent(), "BusCommuter", null, "(?s).*check1: 0.*check2: 10.*"));
				}
				if (files[2] != null) {
					ms[2] = true;
					File a = new File(files[2].getParent() + "/Commuter.java");
					File b = new File(files[2].getParent() + "/PayCommuter.java");
					File a1 = new File("asnlib/Commuter.java");
					File b1 = new File("asnlib/PayCommuter.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					TestCase.compile(a);
					TestCase.compile(b);
					tests[11].setResult(TestCase.runMain(files[2].getParent(), "BusCommuter", null, "(?s).*check1: 0.*check2: 10.*"));
	 			}
				if (files[3] != null) {
					ms[3] = true;
	 			}

				tests[3].setResult(true);
				for (int i = 0; i < 4; i++) {
					if (!ms[i]) {
						tests[3].setResult(false);
					}
				}

				for(ClassOrInterfaceType t : sip.extendedTypes) {
					if (t.asString().equals("PayCommuter")) tests[5].setResult(true);
				}
				List<Node> list = sip.findPieces("BusCommuter(String, double, double)");
				for (Node n : list) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						ExplicitConstructorInvocationStmt ecis = (ExplicitConstructorInvocationStmt)n;
						if (!ecis.isThis()) {
							tests[9].setResult(true);
						}
					}
				}
			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
