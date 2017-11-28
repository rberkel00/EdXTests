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
				new TestCase("Test 1", "Playlist.java exists", "Completion", 1),
				new TestCase("Test 2", "Playlist.java compiles", "Completion", 1),
				new TestCase("Test 3", "makeRequest method signature is correct and is public", "Completion", 1),
				new TestCase("Test 4", "addSong method signature is correct and is private", "Completion", 1),
				new TestCase("Test 5", "getNextSong method signature is correct and is public", "Completion", 1),
				new TestCase("Test 6", "searchList method signature is correct and is private", "Completion", 1),
				new TestCase("Test 7", "getNextSong works correctly", "Completion", 1),
				new TestCase("Test 8", "Constructor signature is correct", "Completion", 1),
				new TestCase("Test 9", "makeRequest works correctly with full list", "Completion", 1),
				new TestCase("Test 10", "makeRequest works correctly with repeat song", "Completion", 1),
				new TestCase("Test 11", "makeRequest works correctly with empty list and new song", "Completion", 1),
			};
			File lot = new File("Playlist.java");
			if (!lot.exists()) {
				System.out.println("Playlist.java was not found. Did you name your file correctly?");
				return;
			}
			tests[0].setResult(true);

			if (!TestCase.compile(new File("Playlist.java"))) {
				System.out.println("Playlist.java does not compile.");
			} else {
				tests[1].setResult(true);
			}

			Parser lparser = new Parser();
			if (lparser.parse("Playlist.java")) {
				String[] methods = {"Playlist()","boolean makeRequest(String)","int searchList(String)", "void addSong(String)", "String getNextSong()"};
				File[] files = lparser.replace(methods, "asnlib/Playlist.java");
				if (files[0] != null) {
					tests[7].setResult(true);
				}
				if (files[1] != null) {
					tests[2].setResult(true);
					tests[8].setResult(TestCase.runMain(files[1].getParent(), "Playlist", null, "(?s).*6true.*7false.*"));
					tests[9].setResult(TestCase.runMain(files[1].getParent(), "Playlist", null, "(?s).*2false.*"));
					tests[10].setResult(TestCase.runMain(files[1].getParent(), "Playlist", null, "(?s).*1true.*"));
				}
				if (files[2] != null) {
					tests[5].setResult(true);
				}
				if (files[3] != null) {
					tests[3].setResult(true);
				}
				if (files[4] != null) {
					tests[4].setResult(true);
					tests[6].setResult(TestCase.runMain(files[4].getParent(), "Playlist", null, "(?s).*8Song1.*"));
				}
			}

 			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}

}
}
