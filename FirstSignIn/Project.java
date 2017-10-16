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

		TestCase[] tests = {
				new TestCase("Test 1", "FirstSignIn compiles", "Correctness", 1)
			};

		try {
			
			tests[0].setResult(TestCase.compile(new File("FirstSignIn.java")));
			TestCase.pushAll(tests);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}