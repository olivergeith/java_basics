package demo.kochsnowflake;

public class PaperFoldSequence {

	public static void main(final String[] args) {

		String theString = "L";

		for (int level = 0; level < 5; level++) {
			theString = foldLeft(theString);
			System.out.println(level + " -> " + theString);
		}

	}

	private static String foldLeft(final String theString) {

		return theString + "L" + replace(reverse(theString));
	}

	private static String reverse(final String theSting) {
		return new StringBuilder(theSting).reverse().toString();
	}

	private static String replace(String theString) {
		theString = theString.replaceAll("L", "X");
		theString = theString.replaceAll("R", "L");
		theString = theString.replaceAll("X", "R");

		return theString;
	}

}
