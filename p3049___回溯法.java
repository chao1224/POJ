import java.io.*;
import java.util.*;

public class p3049___»ØËÝ·¨ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n, m;
	static char arr[];
	static boolean mark[];

	public static void main(String[] args) throws IOException {
		m = nextInt();
		n = nextInt();
		arr = new char[n];
		mark = new boolean[n];
		Arrays.fill(mark, false);
		for (int i = 0; i < n; i++)
			arr[i] = nextChar();
		Arrays.sort(arr);
		dfs(0, 0);

		out.flush();
		out.close();
	}

	static boolean check() {
		int v = 0, non = 0;
		for (int i = 0; i < n; i++)
			if (mark[i])
				if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i'
						|| arr[i] == 'o' || arr[i] == 'u')
					v++;
				else
					non++;
		if (v < 1 || non < 2)
			return false;
		return true;
	}

	static void dfs(int k, int count) {
		if (count == m) {
			if (check()) {
				for (int i = 0; i < n; i++)
					if (mark[i])
						out.print(arr[i]);
				out.println();
			}
		} else {
			if (n - (k + 1) + (count + 1) >= m) {
				mark[k] = true;
				dfs(k + 1, count + 1);
				mark[k] = false;
			}
			if (n - k + count >= m)
				dfs(k + 1, count);
		}
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static char nextChar() throws IOException {
		in.nextToken();
		return in.sval.charAt(0);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return (long) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}