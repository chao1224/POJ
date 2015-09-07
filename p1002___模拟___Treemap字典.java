import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class p1002___Ä£Äâ___Treemap×Öµä {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int m[] = new int[256];

	public static void main(String[] args) throws IOException {
		m['0'] = 0;
		m['1'] = 1;
		m['2'] = m['A'] = m['B'] = m['C'] = 2;
		m['3'] = m['D'] = m['E'] = m['F'] = 3;
		m['4'] = m['G'] = m['H'] = m['I'] = 4;
		m['5'] = m['J'] = m['K'] = m['L'] = 5;
		m['6'] = m['M'] = m['N'] = m['O'] = 6;
		m['7'] = m['P'] = m['R'] = m['S'] = 7;
		m['8'] = m['T'] = m['U'] = m['V'] = 8;
		m['9'] = m['W'] = m['X'] = m['Y'] = 9;

		int turn = Integer.parseInt(br.readLine());
		TreeMap<String, Integer> maap = new TreeMap<String, Integer>();
		int val;
		String tt;
		for (int i = 0; i < turn; i++) {
			tt = StringToInt(br.readLine().replace("-", ""));
			if (maap.containsKey(tt)) {
				val = maap.get(tt) + 1;
				maap.put(tt, val);
			} else {
				maap.put(tt, 1);
			}
		}

		Set<String> set = maap.keySet();
		Iterator<String> it = set.iterator();
		boolean flag = true;
		while (it.hasNext()) {
			tt = it.next();
			if (maap.get(tt) > 1) {
				flag = false;
				val = maap.get(tt);
				out.println(tt.substring(0, 3) + "-" + tt.substring(3) + " "
						+ val);
			}
		}
		if (flag)
			out.println("No duplicates. ");

		out.flush();
		out.close();
	}

	public static String StringToInt(String str) {
		StringBuilder result = new StringBuilder();
		char ch;
		for (int j = 0; j < str.length(); j++) {
			ch = str.charAt(j);
			if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z'))
				result.append(m[ch]);
		}
		return result.toString();
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}
}
