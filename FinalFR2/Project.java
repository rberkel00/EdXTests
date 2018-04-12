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
				new TestCase("Test 1", "Constructor method signature", "Completion", 1),
				new TestCase("Test 2", "getR, getB, and getG are overwritten", "Completion", 1),
				new TestCase("Test 3", "getR, getB, and getG make calls to super", "Completion", 1),
				new TestCase("Test 4", "Constructor makes call to super", "Completion", 1),
				new TestCase("Test 5", "Constructor sets isFiltered to false", "Completion", 1),
				new TestCase("Test 6", "getR checks if image should be filtered and sets R correctly", "Completion", 1),
				new TestCase("Test 7", "getG checks if image should be filtered and sets G correctly", "Completion", 1),
				new TestCase("Test 8", "getB checks if image should be filtered and sets B correctly", "Completion", 1),
				new TestCase("Test 9", "toString overwritten and returns correct result", "Completion", 1)
			};

						/*if (!TestCase.compile(new File("FilteredPixel.java"))) {
							System.out.println("FilteredPixel.java does not compile.");
						} else {
							tests[0].setResult(true);
						}*/

						Parser p = new Parser();
						if (p.parse("FilteredPixel.java")) {
							String[] methods = {"FilteredPixel(int, int, int, double, double, double)","int getR()","int getG()", "int getB()", "String toString()"};
							File[] files = p.replace(methods, "asnlib/FilteredPixel.java");
							boolean[] msig = new boolean[3];
							if (files[0] != null) {
								//move Pixel in temp folder and compile
								File temps = new File(files[0].getParent() + "/Pixel.java");
								File tempf = new File("asnlib/Pixel.java");
								Files.copy(tempf.toPath(), temps.toPath());
								TestCase.compile(temps);
								//make regex
								tests[0].setResult(true);
								tests[4].setResult(TestCase.runMain(files[0].getParent(), "FilteredPixel", null, "(?s).*test1: false.*"));
							}
							if (files[1] != null) {
								//move Pixel in temp folder and compile
								File temps = new File(files[1].getParent() + "/Pixel.java");
								File tempf = new File("asnlib/Pixel.java");
								Files.copy(tempf.toPath(), temps.toPath());
								TestCase.compile(temps);
								//make regex
								msig[0] = true;

								tests[5].setResult(TestCase.runMain(files[1].getParent(), "FilteredPixel", null, "(?s).*test2: .*test6: .*"));
								tests[5].setResult(true);
							}
							if (files[2] != null) {
								//move Pixel in temp folder and compile
								File temps = new File(files[2].getParent() + "/Pixel.java");
								File tempf = new File("asnlib/Pixel.java");
								Files.copy(tempf.toPath(), temps.toPath());
								TestCase.compile(temps);
								//make regex
								msig[1] = true;
								tests[6].setResult(TestCase.runMain(files[2].getParent(), "FilteredPixel", null, "(?s).*test3: .*test7: .*"));
								tests[6].setResult(true);
			 				}
							if (files[3] != null) {
								//move Pixel in temp folder and compile
								File temps = new File(files[3].getParent() + "/Pixel.java");
								File tempf = new File("asnlib/Pixel.java");
								Files.copy(tempf.toPath(), temps.toPath());
								TestCase.compile(temps);
								//make regex
								msig[2] = true;
								tests[7].setResult(TestCase.runMain(files[3].getParent(), "FilteredPixel", null, "(?s).*test4: .*test8: .*"));
								tests[7].setResult(true);
			 				}
							if (files[4] != null) {
								//move Pixel in temp folder and compile
								File temps = new File(files[4].getParent() + "/Pixel.java");
								File tempf = new File("asnlib/Pixel.java");
								Files.copy(tempf.toPath(), temps.toPath());
								TestCase.compile(temps);
								//make regex
								tests[4].setResult(true);
								tests[8].setResult(TestCase.runMain(files[4].getParent(), "FilteredPixel", null, "(?s).*test9: .*test10: .*"));
								tests[8].setResult(true);
			 				}

							tests[1].setResult(true);
							for (int i = 0; i < msig.length; i++) {
								if (!msig[i]) {
									tests[1].setResult(false);
								}
							}

							List<Node> snodes = p.findPieces("FilteredPixel(int, int, int, double, double, double)");
							for (Node n : snodes) {
								if (n instanceof SuperExpr || n instanceof ExplicitConstructorInvocationStmt) {
									tests[3].setResult(true);
								}
							}
							boolean[] sup = new boolean[3];
							snodes = p.findPieces("int getR()");
							for (Node n : snodes) {
								if (n instanceof SuperExpr || n instanceof ExplicitConstructorInvocationStmt) {
									sup[0] = true;
								}
							}
							snodes = p.findPieces("int getG()");
							for (Node n : snodes) {
								if (n instanceof SuperExpr || n instanceof ExplicitConstructorInvocationStmt) {
									sup[1] = true;
								}
							}
							snodes = p.findPieces("int getB()");
							for (Node n : snodes) {
								if (n instanceof SuperExpr || n instanceof ExplicitConstructorInvocationStmt) {
									sup[2] = true;
								}
							}
							tests[2].setResult(true);
							for (int i = 0; i < sup.length; i++) {
								if (!sup[i]) {
									tests[2].setResult(false);
								}
							}
						}

			 			TestCase.pushAll(tests);

					} catch (Exception e) {
						e.printStackTrace();
					}

			}
			}
