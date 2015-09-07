import java.io.*;
import java.util.*;

public class p2309___水数位统计 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(System.out);

	static int n;

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		while (test-- > 0) {
			n = nextInt();
			int i = 0;
			while (((1 << i) & n) == 0)
				i++;
			out.print(n - (1 << i) + 1 + " ");
			out.println(n - (1 << i) + (1 << (i + 1)) - 1);
		}
		out.close();
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

	static float nextFloat() throws IOException {
		in.nextToken();
		return (float) in.nval;
	}

	static double nextDouble() throws IOException {
		in.nextToken();
		return in.nval;
	}
}