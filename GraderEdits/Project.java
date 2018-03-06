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
			TestCase[] tests = {new TestCase("Test1", "Regex Check", "Completion", 1) //done
			};
			File lot = new File("Card.java");
			if (!lot.exists()) {
				System.out.println("Card.java was not found. Did you name your file correctly?");
			}
			File space = new File("CardCollection.java");
			if (!space.exists()) {
				System.out.println("CardCollection.java was not found. Did you name your file correctly?");
			}

			if (!TestCase.compile(new File("Card.java"))) {
				System.out.println("Card.java does not compile.");
			}

			if (!TestCase.compile(new File("CardCollection.java"))) {
				System.out.println("CardCollection.java does not compile.");
			}


			Parser hparser = new Parser();
			if (hparser.parse("CardCollection.java")) {
				String[] methods = {"CardCollection(String)","boolean addCard(Card)"};
				File[] files = hparser.replace(methods, "asnlib/CardCollection.java");
				File tempf = new File("asnlib/Card.java");
				File temps;


				if (files[0] != null) {
				}
				if (files[1] != null) {
					temps = new File(files[1].getParent() + "/Card.java");
					Files.copy(tempf.toPath(), temps.toPath());
					TestCase.compile(temps);
					tests[0].setResult(!TestCase.runMain(files[1].getParent(), "CardCollection", null, "(?s).*addCard failed.*"));
				}
			}
			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
