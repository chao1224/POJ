import java.awt.*;
import java.io.*;
import java.util.*;

public class p1129___染色___贪心解决 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m;
	static int map[][] = new int[26][26];

	public static void main(String[] args) throws IOException {
		String str;
		int ch;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			for (int i = 0; i < n; i++)
				Arrays.fill(map[i], 0);
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 2; j < str.length(); j++) {
					ch = str.charAt(j) - 'A';
					map[i][ch] = map[ch][i] = 1;
				}
			}
			int ans = greed();
			if (ans == 1)
				out.println("1 channel needed.");
			else
				out.println(ans + " channels needed.");
		}
		out.flush();
		out.close();
	}

	static int greed() {
		int[] color = new int[n + 1];
		Arrays.fill(color, -1);
		int[] b = new int[n + 1];
		int k;

		for (int i = 0; i < n; i++) {
			Arrays.fill(b, 0);

			for (k = 0; k < n; k++)
				if (map[i][k] == 1 && color[k] != -1)
					b[color[k]] = 1;

			for (k = 0; k < n; k++)
				if (b[k] == 0)
					break;

			color[i] = k;
		}

		int max = 0;
		for (int i = 0; i < n; i++)
			max = Math.max(max, color[i]);
		max++;

		return max;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

}