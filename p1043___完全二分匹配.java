import java.awt.Stroke;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.*;

public class p1043___完全二分匹配 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static Scanner scan = new Scanner(System.in);

	static int n, m, k;
	static int match[][], mark[], cx[], cy[], cnt;

	public static void main(String[] args) throws IOException {
		int index, key;
		String str, record[];
		TreeMap<String, Integer> id = new TreeMap<String, Integer>(), name = new TreeMap<String, Integer>();
		char ch;
		int cnt = 0;

		//
		// while (test-- > 0) {
		if (cnt == 0)
			cnt = 1;
		else
			out.println();

		n = nextInt();
		index = 0;
		id.clear();
		name.clear();
		match = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++)
			Arrays.fill(match[i], 1);
		int visit[] = new int[n + 1];
		record = new String[n + 1];

		for (int i = 1; i <= n; i++) {
			str = next();
			record[i] = str;
			id.put(str, i);
		}

		while (true) {
			ch = nextChar();
			if (ch == 'Q')
				break;
			str = next();

			if (ch == 'M') {
				if (id.containsKey(str)) {
					key = id.get(str);
				} else {
					id.put(str, ++index);
					key = index;
				}
				for (int i = 1; i <= n; i++) {
					if (visit[i] == 0)
						match[key][i] = 0;
				}
			} else {
				if (name.containsKey(str)) {
					key = name.get(str);
				} else {
					name.put(str, ++index);
					key = index;
				}
				if (ch == 'E') {
					visit[key] = 1;
				} else {
					visit[key] = 0;
				}
			}
		}

		cx = new int[n + 1];
		cy = new int[n + 1];
		mark = new int[n + 1];
		Arrays.fill(cx, -1);
		Arrays.fill(cy, -1);
		int ans = 0;
		for (int i = 1; i <= n; i++)
			if (cx[i] == -1) {
				Arrays.fill(mark, 0);
				ans += path(i);
			}

		int temp, order[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (match[i][j] > 0) {
					temp = 0;
					match[i][j] = 0;
					Arrays.fill(cx, -1);
					Arrays.fill(cy, -1);
					for (int ii = 1; ii <= n; ii++)
						if (cx[ii] == -1) {
							Arrays.fill(mark, 0);
							temp += path(ii);
						}
					if (ans > temp)
						order[j] = i;
					match[i][j] = 1;
				}
			}
		}

		int num;
		Set<String> set = name.keySet();
		for (String s : set) {
			num = name.get(s);
			num = order[num];
			if (num > 0)
				out.println(s + ":" + record[num]);
			else
				out.println(s + ":???");

		}
		// }

		out.flush();
		out.close();
	}

	static int path(int u) {
		for (int i = 1; i <= n; i++) {
			if (match[u][i] > 0 && mark[i] == 0) {
				mark[i] = 1;
				if (cy[i] == -1 || path(cy[i]) == 1) {
					cx[u] = i;
					cy[i] = u;
					return 1;
				}
			}
		}

		return 0;
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