import java.io.*;
import java.util.*;

public class p1008___日历小模拟 {
	static int n;
	static HashMap<String, Integer> haab = new HashMap<String, Integer>();
	static HashMap<Integer, String> tzolkin = new HashMap<Integer, String>();

	static void init() {
		haab.put("pop", 0);
		haab.put("no", 1);
		haab.put("zip", 2);
		haab.put("zotz", 3);
		haab.put("tzec", 4);
		haab.put("xul", 5);
		haab.put("yoxkin", 6);
		haab.put("mol", 7);
		haab.put("chen", 8);
		haab.put("yax", 9);
		haab.put("zac", 10);
		haab.put("ceh", 11);
		haab.put("mac", 12);
		haab.put("kankin", 13);
		haab.put("muan", 14);
		haab.put("pax", 15);
		haab.put("koyab", 16);
		haab.put("cumhu", 17);
		haab.put("uayet", 18);

		String temp[] = { "imix", "ik", "akbal", "kan", "chicchan", "cimi",
				"manik", "lamat", "muluk", "ok", "chuen", "eb", "ben", "ix",
				"mem", "cib", "caban", "eznab", "canac", "ahau" };

		int i = 1, j = 0;
		for (int t = 0; t < 260; t++) {
			tzolkin.put(t, i + " " + temp[j]);
			i++;
			if (i == 14)
				i = 1;
			j++;
			if (j == 20)
				j = 0;
		}
	}

	static int fromHaabToInt(String a, String b, int year) {
		int ret = 0;
		int day = Integer.parseInt(a.substring(0, a.length() - 1));
		int month = haab.get(b);
		ret = year * 365 + day + month * 20;
		return ret;
	}

	static String fromIntToTzolkin(int num) {
		String ret;
		int year = num / 260;
		int day = num % 260;
		ret = tzolkin.get(day) + " " + year;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		init();
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		System.out.println(n);
		while (n-- > 0) {
			int temp = fromHaabToInt(scan.next(), scan.next(), scan.nextInt());
			System.out.println(fromIntToTzolkin(temp));
		}
	}
}
