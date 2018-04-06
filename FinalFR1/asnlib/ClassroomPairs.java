public class ClassroomPairs {
	public static String studentPair(String a, String b) {
		return a + ", " + b;
	}
	public static boolean canPair(String[][] classroom) {
		int count = getCount(classroom);
		return (count%2 == 0);
	}
	public static String[] classPairs(String[][] classroom) {
		int count = getCount(classroom);
		if (!canPair(classroom)) {
			return new String[count];
		}
		String[] pairs = new String[count/2];
		int total = 0;
		boolean paired = true;
		String first = "";
		for (int i = 0; i < classroom.length; i++) {
			if (i%2 == 0) {
				for (int j = 0; j < classroom[i].length; j++) {
					if (paired) {
						first = classroom[i][j];
						paired = false;
					} else {
						pairs[total] = studentPair(first, classroom[i][j]);
						total++;
						paired = true;
					}
				}
			} else {
				for (int j = classroom[i].length - 1; j >= 0; j--) {
					if (paired) {
						first = classroom[i][j];
						paired = false;
					} else {
						pairs[total] = studentPair(first, classroom[i][j]);
						total++;
						paired = true;
					}
				}
			}
		}
		return pairs;
	}
	public static int getCount(String[][] classroom) {
		int count = 0;
		for (String[] row : classroom) {
			for (String col : row) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		String[][] classroom = {
			{"Robyn", "Chris", "Brenda", "Taylor", "Ruger"},
			{"Hank", "Jess", "Vicki", "Ken", "Bandit"},
			{"Robin", "Marshall"}
		};
		System.out.println("test01: " + studentPair(classroom[0][1], classroom[0][3]));
		System.out.println("test02: " + canPair(classroom));
		String[][] class2 = {
			{"HI", "Bye", "oh"},
			{"blah", "k"}
		};
		System.out.println("test03: " + canPair(class2));
		System.out.println("test04: ");
		String[] pairs = classPairs(classroom);
		for (String pair : pairs) {
			System.out.println(pair);
		}
	}
}
