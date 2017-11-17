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


public class Check {
  public static void main(String[] args) {
    try {
      Parser p = new Parser();
      p.parse("Pirate.java");
      List<Node> expressions = new ArrayList<Node>();
  		for (MethodDeclaration m : p.methods) {
  			if (m.getDeclarationAsString(false, false, false).equals("void main(String[])")) {
  				BlockStmt block = m.getBody().get();
          p.findHelperMethods(block);
        }
      }
    } catch (Exception e ) {
      e.printStackTrace();
    }
  }
}
