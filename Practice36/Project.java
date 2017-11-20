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
				new TestCase("Test 1", "File compiles", "Completion", 1),
				new TestCase("Test 2", "Declared static variable \"orders\"", "Completion", 1),
				new TestCase("Test 3", "Increment orders", "Completion", 1),
				new TestCase("Test 4", "Decrement orders", "Completion", 1)
			};

			if (!TestCase.compile(new File("Order.java"))) {
				System.out.println("Order.java does not compile.");
			} else {
				tests[0].setResult(true);
			}

			Parser parser = new Parser();

			boolean first = false;
			boolean last = false;

			if (parser.parse("Order.java")) {
				List<FieldDeclaration> fields = parser.fields;
				for(FieldDeclaration f : fields) {
					boolean t = false;
					for(Modifier m : f.getModifiers()){
						if(m.asString().equals("static")){
							t = true;
						}
					}
					if(t){
						NodeList<VariableDeclarator> nvd = f.getVariables();
						for (VariableDeclarator vd : nvd) {
							if (vd.getName().asString().equals("orders") && vd.getType().asString().equals("int")) {
								tests[1].setResult(true);
							}
						}
					}

				}
			}




 			TestCase.pushAll(tests);

		} catch (Exception e) {
		}

}
}
