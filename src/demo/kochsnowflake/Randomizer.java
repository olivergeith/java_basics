package demo.kochsnowflake;

public class Randomizer {

	public static float getRandomFloat(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		return mRandom;
	}

	public static float getRandomFloatAlternating(final float min, final float max) {
		final float mRandom = (float) (min + Math.random() * (max - min));
		if (getRandomBoolean()) {
			return mRandom;
		} else {
			return -mRandom;
		}
	}

	public static int getRandomInt(final int min, final int max) {

		final double d = Math.random() * (max - min);
		final int ceil = (int) Math.round(d);
		System.out.println("d=" + d);
		System.out.println("ceil=" + ceil);

		final int mRandom = min + ceil;
		return mRandom;
	}

	public static boolean getRandomBoolean() {
		final int mRandom = (int) Math.round(Math.random() * 1);
		if (mRandom == 1) {
			return true;
		}
		return false;
	}

	/**
	 * @param percent
	 * @return true in percent of cases!
	 */
	public static boolean getRandomBooleanInPercentOfCases(final int percent) {
		if (getRandomInt(1, 100) <= percent) {
			return true;
		}
		return false;
	}

	public static void main(final String[] args) {
		int counttrue = 0;
		int countfalse = 0;
		for (int i = 0; i < 1000; i++) {
			if (getRandomInt(5, 6) == 5) {
				counttrue++;
			} else {
				countfalse++;
			}
		}
		System.out.println("true  =" + counttrue);
		System.out.println("false =" + countfalse);
	}

	// public static void main(final String[] args) {
	// int counttrue = 0;
	// int countfalse = 0;
	// for (int i = 0; i < 1000; i++) {
	// if (getRandomBooleanInPercentOfCases(99)) {
	// counttrue++;
	// } else {
	// countfalse++;
	// }
	// }
	// System.out.println("true =" + counttrue);
	// System.out.println("false =" + countfalse);
	// }

}
