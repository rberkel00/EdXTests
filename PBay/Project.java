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
				new TestCase("Test 1", "Item.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 2", "SaleItem.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 3", "User.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 4", "AuctionItem.java contains all necessary method signatures", "Completion", 1),
				new TestCase("Test 5", "Item.java compiles", "Completion", 1),
				new TestCase("Test 6", "SaleItem.java compiles", "Completion", 1),
				new TestCase("Test 7", "User.java compiles", "Completion", 1),
				new TestCase("Test 8", "AuctionItem.java compiles", "Completion", 1),
				new TestCase("Test 9", "listCost is rounded in Item constructor", "Completion", 1),
				new TestCase("Test 10", "Item toggleForSale switches the value of forSale", "Completion", 1),
				new TestCase("Test 11", "Item purchaseItem updates the balance and owner and switches forSale", "Completion", 1),
				new TestCase("Test 12", "User updateBalance works correctly", "Completion", 1),
				new TestCase("Test 13", "AuctionItem extends Item", "Completion", 1),

				new TestCase("Test 14", "AuctionItem makeBid works correctly", "Completion", 1),
				new TestCase("Test 15", "SaleItem extends Item", "Completion", 1),
				new TestCase("Test 16", "SaleItem purchaseItem works correctly", "Completion", 1)
			};
			File it = new File("Item.java");
			if (!it.exists()) {
				System.out.println("Item.java was not found. Did you name your file correctly?");
			}
			File si = new File("SaleItem.java");
			if (!si.exists()) {
				System.out.println("SaleItem.java was not found. Did you name your file correctly?");
			}
			File us = new File("User.java");
			if (!us.exists()) {
				System.out.println("User.java was not found. Did you name your file correctly?");
			}
			File ai = new File("AuctionItem.java");
			if (!ai.exists()) {
				System.out.println("AuctionItem.java was not found. Did you name your file correctly?");
			}
			if (!TestCase.compile(new File("Item.java"))) {
				System.out.println("Item.java does not compile.");
			} else {
				tests[4].setResult(true);
			}
			if (!TestCase.compile(new File("SaleItem.java"))) {
				System.out.println("SaleItem.java does not compile.");
			} else {
				tests[5].setResult(true);
			}
			if (!TestCase.compile(new File("User.java"))) {
				System.out.println("User.java does not compile.");
			} else {
				tests[6].setResult(true);
			}
			if (!TestCase.compile(new File("AuctionItem.java"))) {
				System.out.println("AuctionItem.java does not compile.");
			} else {
				tests[7].setResult(true);
			}

			Parser itp = new Parser();
			if (itp.parse("Item.java")) {
				String[] methods = {"Item(String, double, User, boolean)","String getName()","double getListCost()", "User getOwner()", "boolean getForSale()", "void setOwner(User)", "void setListCost(double)", "void toggleForSale()", "void purchaseItem(User, double)", "String toString()"};
				File[] files = itp.replace(methods, "asnlib/Item.java");
				boolean[] ms = new boolean[10];
				if (files[0] != null) {
					ms[0] = true;
					File a = new File(files[0].getParent() + "/Store.java");
					File b = new File(files[0].getParent() + "/AuctionItem.java");
					File c = new File(files[0].getParent() + "/SaleItem.java");
					File d = new File(files[0].getParent() + "/User.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/AuctionItem.java");
					File c1 = new File("asnlib/SaleItem.java");
					File d1 = new File("asnlib/User.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[8].setResult(!TestCase.runMain(files[0].getParent(), "Store", null, "(?s).*Rounded: 2.78.*"));
				}
				if (files[1] != null) {
					ms[1] = true;
				}
				if (files[2] != null) {
					ms[2] = true;
 				}
				if (files[3] != null) {
					ms[3] = true;
				}
				if (files[4] != null) {
					ms[4] = true;
				}
				if (files[5] != null) {
					ms[5] = true;
				}
				if (files[6] != null) {
					ms[6] = true;
				}
				if (files[7] != null) {
					ms[7] = true;
					File a = new File(files[7].getParent() + "/Store.java");
					File b = new File(files[7].getParent() + "/AuctionItem.java");
					File c = new File(files[7].getParent() + "/SaleItem.java");
					File d = new File(files[7].getParent() + "/User.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/AuctionItem.java");
					File c1 = new File("asnlib/SaleItem.java");
					File d1 = new File("asnlib/User.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[9].setResult(!TestCase.runMain(files[7].getParent(), "Store", null, "(?s).*ForSale: false.*"));
				}
				if (files[8] != null) {
					ms[8] = true;
					File a = new File(files[8].getParent() + "/Store.java");
					File b = new File(files[8].getParent() + "/AuctionItem.java");
					File c = new File(files[8].getParent() + "/SaleItem.java");
					File d = new File(files[8].getParent() + "/User.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/AuctionItem.java");
					File c1 = new File("asnlib/SaleItem.java");
					File d1 = new File("asnlib/User.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[10].setResult(!TestCase.runMain(files[8].getParent(), "Store", null, "(?s).*Purchase: Dad true.*"));
				}
				if (files[9] != null) {
					ms[9] = true;
				}

				tests[0].setResult(true);
				for (int i = 0; i < 10; i++) {
					if (!ms[i]) {
						tests[0].setResult(false);
					}
				}
			}

			Parser sip = new Parser();
			if (sip.parse("SaleItem.java")) {
				String[] methods = {"SaleItem(String, double, User, double)","String getDiscount()","void purchaseItem(User)"};
				File[] files = sip.replace(methods, "asnlib/SaleItem.java");
				boolean[] ms = new boolean[3];
				if (files[0] != null) {
					ms[0] = true;
				}
				if (files[1] != null) {
					ms[1] = true;
				}
				if (files[2] != null) {
					ms[2] = true;
					File a = new File(files[2].getParent() + "/Store.java");
					File b = new File(files[2].getParent() + "/AuctionItem.java");
					File c = new File(files[2].getParent() + "/Item.java");
					File d = new File(files[2].getParent() + "/User.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/AuctionItem.java");
					File c1 = new File("asnlib/Item.java");
					File d1 = new File("asnlib/User.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[15].setResult(!TestCase.runMain(files[2].getParent(), "Store", null, "(?s).*Purchase: 7.75 Blake.*"));
	 			}

				tests[1].setResult(true);
				for (int i = 0; i < 3; i++) {
					if (!ms[i]) {
						tests[1].setResult(false);
					}
				}

				for(ClassOrInterfaceType t : sip.extendedTypes) {
					if (t.asString().equals("Item")) tests[14].setResult(true);
				}
			}

			Parser usp = new Parser();
			if (usp.parse("User.java")) {
				String[] methods = {"User(String)","String getName()","double getAccountBalance()", "double updateBalance(double)", "String toString()"};
				File[] files = usp.replace(methods, "asnlib/User.java");
				boolean[] ms = new boolean[5];
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
					File a = new File(files[3].getParent() + "/Store.java");
					File b = new File(files[3].getParent() + "/AuctionItem.java");
					File c = new File(files[3].getParent() + "/SaleItem.java");
					File d = new File(files[3].getParent() + "/Item.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/AuctionItem.java");
					File c1 = new File("asnlib/SaleItem.java");
					File d1 = new File("asnlib/Item.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[11].setResult(!TestCase.runMain(files[3].getParent(), "Store", null, "(?s).*Balance: 5.0.*"));
				}
				if (files[4] != null) {
					ms[4] = true;
				}

				tests[2].setResult(true);
				for (int i = 0; i < 5; i++) {
					if (!ms[i]) {
						tests[2].setResult(false);
					}
				}
			}

			Parser aip = new Parser();
			if (aip.parse("AuctionItem.java")) {
				String[] methods = {"AuctionItem(String, double, User, int)","int getDaysLeft()","double getCurrentBid()", "void advanceDay()", "String makeBid(User, double)", "void resetAuction(double, int)", "String toString()"};
				File[] files = aip.replace(methods, "asnlib/AuctionItem.java");
				boolean[] ms = new boolean[7];
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
				}
				if (files[4] != null) {
					ms[4] = true;
					File a = new File(files[4].getParent() + "/Store.java");
					File b = new File(files[4].getParent() + "/Item.java");
					File c = new File(files[4].getParent() + "/SaleItem.java");
					File d = new File(files[4].getParent() + "/User.java");
					File a1 = new File("asnlib/Store.java");
					File b1 = new File("asnlib/Item.java");
					File c1 = new File("asnlib/SaleItem.java");
					File d1 = new File("asnlib/User.java");
					Files.copy(a1.toPath(), a.toPath());
					Files.copy(b1.toPath(), b.toPath());
					Files.copy(c1.toPath(), c.toPath());
					Files.copy(d1.toPath(), d.toPath());
					TestCase.compile(b);
					TestCase.compile(c);
					TestCase.compile(d);
					TestCase.compile(a);
					tests[13].setResult(!TestCase.runMain(files[4].getParent(), "Store", null, "(?s).*Bid Owner: 11.0.*"));
				}
				if (files[5] != null) {
					ms[5] = true;
				}
				if (files[6] != null) {
					ms[6] = true;
				}

				tests[3].setResult(true);
				for (int i = 0; i < 7; i++) {
					if (!ms[i]) {
						tests[3].setResult(false);
					}
				}

				for(ClassOrInterfaceType t : aip.extendedTypes) {
					if (t.asString().equals("Item")) tests[12].setResult(true);
				}

			}


			TestCase.pushAll(tests);
		} catch (Exception e) {
			e.printStackTrace();
		}
}
}
