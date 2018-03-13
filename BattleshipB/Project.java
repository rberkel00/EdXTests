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
				new TestCase("Test 1", "Attacker is an interface", "Completion", 1),
				new TestCase("Test 2", "Attacker attack method is written correctly (abstract)", "Completion", 1),
				new TestCase("Test 3", "ScoutBoat is an abstract class", "Completion", 1),
				new TestCase("Test 4", "ScoutBoat extends Boat", "Completion", 1),
				new TestCase("Test 5", "ScoutBoat constructor makes call to super constructor", "Completion", 1),
				new TestCase("Test 6", "ScoutBoat takeHit method makes call to super takeHit method", "Completion", 1)

			};
			File it = new File("Attacker.java");
			if (!it.exists()) {
				System.out.println("Attacker.java was not found. Did you name your file correctly?");
			}
			File si = new File("ScoutBoat.java");
			if (!si.exists()) {
				System.out.println("ScoutBoat.java was not found. Did you name your file correctly?");
			}
			if (!TestCase.compile(new File("Attacker.java"))) {
				System.out.println("Attacker.java does not compile.");
			}
			if (!TestCase.compile(new File("ScoutBoat.java"))) {
				System.out.println("ScoutBoat.java does not compile.");
			}
			Parser itp = new Parser();
			if (itp.parse("Attacker.java")) {
				for (MethodDeclaration m : itp.methods) {
					if (m.getDeclarationAsString(true, false, false).equals("public abstract String attack(World)")) {
						tests[1].setResult(true);
					}
				}
				tests[0].setResult(itp.isInterface);
			}

			Parser sip = new Parser();
			if (sip.parse("ScoutBoat.java")) {
				String[] methods = {"ScoutBoat(int, Coordinates, int, int, int)","String takeHit(int)"};
				File[] files = sip.replace(methods, "asnlib/ScoutBoat.java");
				tests[2].setResult(sip.isAbstract);
				for (ClassOrInterfaceType c : sip.extendedTypes) {
					if (c.toString().equals("Boat")) {
						tests[3].setResult(true);
					}
				}
				List<Node> nodes = sip.findPieces("ScoutBoat(int, Coordinates, int, int, int)");
				for (Node n : nodes) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						tests[4].setResult(true);
					}
				}
				for (Node n : sip.findPieces("String takeHit(int)")) {
					if (n instanceof SuperExpr) {
						tests[5].setResult(true);
					}
				}
			}


			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
