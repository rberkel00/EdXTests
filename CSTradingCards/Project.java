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
				new TestCase("Test 1", "Card.java exists", "Completion", 1), //done
				new TestCase("Test 2", "CardCollection.java exists", "Completion", 1), //done
				new TestCase("Test 3", "Card.java compiles", "Completion", 1), //done
				new TestCase("Test 4", "CardCollection.java compiles", "Completion", 1), //done
				new TestCase("Test 5", "Card instance variables are correct (and private)", "Completion", 1), //done
				new TestCase("Test 6", "CardCollection instance variables are correct (and private)", "Completion", 1), //done
				new TestCase("Test 7", "Card method signatures are correct", "Completion", 1), //done
				new TestCase("Test 8", "CardCollection method signatures are correct", "Completion", 1), //done
				new TestCase("Test 9", "Card implements the Comparable interface", "Completion", 1),
				new TestCase("Test 10", "Card compareTo method works in all 3 cases", "Completion", 1), //done
				new TestCase("Test 11", "CardCollection addCard works in all 3 cases: Card is already in collection, Card is not in collection, Card is at the end of the collection", "Completion", 1), //done
				new TestCase("Test 12", "CardCollection removeCard works (Hint: an ArrayList method works great for this!)", "Completion", 1), //done
				new TestCase("Test 13", "CardCollection getSize works (Hint: an ArrayList method works great for this!)", "Completion", 1), //done
				new TestCase("Test 14", "CardCollection mergeCollections returns duplicated", "Completion", 1), //done
				new TestCase("Test 15", "CardCollection mergeCollections empties the collection passed in", "Completion", 1), //done
				new TestCase("Test 16", "CardCollection mergeCollections adds non-duplicates to collection", "Completion", 1) //done

			};
			File lot = new File("Card.java");
			if (!lot.exists()) {
				System.out.println("Card.java was not found. Did you name your file correctly?");
			}
			else tests[0].setResult(true);
			File space = new File("CardCollection.java");
			if (!space.exists()) {
				System.out.println("CardCollection.java was not found. Did you name your file correctly?");
			}
			else tests[1].setResult(true);

			if (!TestCase.compile(new File("Card.java"))) {
				System.out.println("Card.java does not compile.");
			} else {
				tests[2].setResult(true);
			}

			if (!TestCase.compile(new File("CardCollection.java"))) {
				System.out.println("CardCollection.java does not compile.");
			} else {
				tests[3].setResult(true);
			}


			Parser lparser = new Parser();
			Parser hparser = new Parser();
			if (hparser.parse("CardCollection.java")) {
				String[] methods = {"CardCollection(String)","boolean addCard(Card)","void removeCard(int)", "List<Card> mergeCollections(CardCollection)", "String toString()"};
				File[] files = hparser.replace(methods, "asnlib/CardCollection.java");
				File tempf = new File("asnlib/Card.java");
				File temps;
				boolean[] methsig = new boolean[5];
				boolean[] acc2 = new boolean[3];
				boolean[] acc3 = new boolean[3];


				if (files[0] != null) {
					methsig[0] = true;
				}
				if (files[1] != null) {
					methsig[1] = true;
					temps = new File(files[1].getParent() + "/Card.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[10].setResult(!TestCase.runMain(files[1].getParent(), "CardCollection", null, "(?s).*addCard failed.*"));
				}
				if (files[2] != null) {
					methsig[2] = true;
					temps = new File(files[2].getParent() + "/Card.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[11].setResult(!TestCase.runMain(files[2].getParent(), "CardCollection", null, "(?s).*removeCard failed.*"));
 				}
				if (files[3] != null) {
					methsig[3] = true;
					temps = new File(files[3].getParent() + "/Card.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[12].setResult(!TestCase.runMain(files[3].getParent(), "CardCollection", null, "(?s).*getSize failed.*"));
				}
				if (files[4] != null) {
					methsig[4] = true;
					temps = new File(files[4].getParent() + "/Card.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[13].setResult(!TestCase.runMain(files[4].getParent(), "CardCollection", null, "(?s).*mergeCollections 1 failed.*"));
					tests[14].setResult(!TestCase.runMain(files[4].getParent(), "CardCollection", null, "(?s).*mergeCollections 2 failed.*"));
					tests[15].setResult(!TestCase.runMain(files[4].getParent(), "CardCollection", null, "(?s).*mergeCollections 3 failed.*"));
				}

				tests[7].setResult(true);

				for (int i = 0; i < 5; i++) {
					if (!methsig[i]) {
						tests[7].setResult(false);
					}
				}

				boolean[] vars = new boolean[2];
				boolean check;
				for (FieldDeclaration f : hparser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("private")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (vd.getName().asString().equals("owner") && vd.getType().asString().equals("String") && check) {
							vars[0] = true;
						} else if (vd.getName().asString().equals("myCollection") && vd.getType().asString().equals("List<Card>") && check) {
							vars[1] = true;
						}
					}
				}

				tests[5].setResult(true);
				for (int i = 0; i < vars.length; i++) {
					if (!vars[i]) {
						tests[5].setResult(false);
					}
				}

			}

			if (lparser.parse("Card.java")) {
				String[] methods = {"Card(String, String, int, int)","int compareTo(Card)","String toString()"};
				File[] files = lparser.replace(methods, "asnlib/Card.java");
				boolean[] methsig = new boolean[3];
				boolean[] acc2 = new boolean[4];


				if (files[0] != null) {
					methsig[0] = true;
				}
				if (files[1] != null) {
					methsig[1] = true;
					tests[9].setResult(TestCase.runMain(files[1].getParent(), "Card", null, "(?s).*-1 0 1.*"));
				}
				if (files[2] != null) {
					methsig[2] = true;
				}

				tests[6].setResult(true);
				for (int i = 0; i < 3; i++) {
					if (!methsig[i]) {
						tests[6].setResult(false);
					}
				}

				boolean[] vars = new boolean[4];
				boolean check;
				for (FieldDeclaration f : lparser.fields) {
					check = false;
					for (Modifier m : f.getModifiers()) {
						if (m.asString().equals("private")) {
							check = true;
						}
					}
					for (VariableDeclarator vd : f.getVariables()) {
						if (vd.getName().asString().equals("name") && vd.getType().asString().equals("String") && check) {
							vars[0] = true;
						} else if (vd.getName().asString().equals("nationality") && vd.getType().asString().equals("String") && check) {
							vars[1] = true;
						} else if (vd.getName().asString().equals("yearBorn") && vd.getType().asString().equals("int") && check) {
							vars[2] = true;
						} else if (vd.getName().asString().equals("yearDied") && vd.getType().asString().equals("int") && check) {
							vars[3] = true;
						}
					}
				}

				for(ClassOrInterfaceType t : lparser.implementedTypes) {
					if (t.asString().equals("Comparable<Card>")) tests[8].setResult(true);
				}

				tests[4].setResult(true);
				for (int i = 0; i < vars.length; i++) {
					if (!vars[i]) {
						tests[4].setResult(false);
					}
				}
			}
			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
