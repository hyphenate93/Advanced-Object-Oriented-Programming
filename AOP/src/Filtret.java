public class Filtret implements Filter {
	public static String[] filter(String[] a, Filter f) {
		String[] b = new String[a.length];
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (f.accept(a[i])) {
				b[count] = a[i];
				count++;
			}

		}
		return b;

	}

	@Override
	public boolean accept(String x) {

		if (x.length() <= 3) {

			return true;

		}

		else {

			return false;

		}
	}
}