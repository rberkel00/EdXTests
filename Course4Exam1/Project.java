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
			boolean script = false;
			if (args.length > 0) script = true;
			TestCase[] tests = {
				new TestCase("Test 1", "Walkup extends Ticket", "Completion", 1), //done
				new TestCase("Test 2", "Advance extends Ticket", "Completion", 1), //done
				new TestCase("Test 3", "StudentAdvance extends Advance", "Completion", 1), //done
				new TestCase("Test 4", "Walkup constructor calls super constructor", "Completion", 1), //done
				new TestCase("Test 5", "Walkup overwrites getPrice", "Completion", 1), //done
				new TestCase("Test 6", "Advance constructor calls super", "Completion", 1), //done
				new TestCase("Test 7", "Advance overwrites getPrice", "Completion", 1), //done
				new TestCase("Test 8", "StudentAdvance constructor calls super", "Completion", 1), //done
				new TestCase("Test 9", "StudentAdvance overwrites toString", "Completion", 1),
				new TestCase("Test 10", "StudentAdvance getPrice calls super", "Completion", 1),
				new TestCase("Test 11", "StudentAdvance toString calls super", "Completion", 1),
				new TestCase("Test 12", "StudentAdvance overwrites getPrice", "Completion", 1)

			};

			if (!TestCase.compile(new File("Walkup.java"))) {
				System.out.println("Walkup.java does not compile.");
			}

			if (!TestCase.compile(new File("Advance.java"))) {
				System.out.println("Advance.java does not compile.");
			}

			if (!TestCase.compile(new File("StudentAdvance.java"))) {
				System.out.println("StudentAdvance.java does not compile.");
			}


			Parser w = new Parser();
			if (w.parse("Walkup.java")) {
				String[] methods = {"double getPrice()"};
				File[] files = w.replace(methods, "asnlib/Walkup.java");

				if (files[0] != null) {
					tests[4].setResult(true);
				}

				for (Node n : w.findPieces("Walkup()")) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						tests[3].setResult(true);
					}
				}

				for (ClassOrInterfaceType c : w.extendedTypes) {
					if (c.toString().equals("Ticket")) {
						tests[0].setResult(true);
					}
				}

			}

			Parser a = new Parser();
			if (a.parse("Advance.java")) {
				String[] methods = {"double getPrice()"};
				File[] files = a.replace(methods, "asnlib/Advance.java");

				if (files[0] != null) {
					tests[6].setResult(true);
				}

				for (Node n : a.findPieces("Advance(int)")) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						tests[5].setResult(true);
					}
				}

				for (ClassOrInterfaceType c : a.extendedTypes) {
					if (c.toString().equals("Ticket")) {
						tests[1].setResult(true);
					}
				}

			}

			Parser s = new Parser();
			if (s.parse("StudentAdvance.java")) {
				String[] methods = {"double getPrice()", "String toString()"};
				File[] files = s.replace(methods, "asnlib/StudentAdvance.java");

				if (files[0] != null) {
					tests[11].setResult(true);
				}

				if (files[1] != null) {
					tests[8].setResult(true);
				}

				for (Node n : s.findPieces("StudentAdvance(int)")) {
					if (n instanceof ExplicitConstructorInvocationStmt) {
						tests[7].setResult(true);
					}
				}

				for (Node n : s.findPieces("double getPrice()")) {
					if (n instanceof SuperExpr) {
						tests[9].setResult(true);
					}
				}

				for (Node n : s.findPieces("String toString()")) {
					if (n instanceof SuperExpr) {
						tests[10].setResult(true);
					}
				}

				for (ClassOrInterfaceType c : s.extendedTypes) {
					if (c.toString().equals("Advance")) {
						tests[2].setResult(true);
					}
				}

			}

			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
