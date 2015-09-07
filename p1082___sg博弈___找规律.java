import java.io.*;
import java.security.KeyStore.Entry;
import java.util.*;

public class p1082___sg²©ÞÄ___ÕÒ¹æÂÉ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	static int n;
	static int year, month, day;

	public static void main(String[] args) throws IOException {
		int test = nextInt();
		while (test-- > 0) {
			year = nextInt();
			month = nextInt();
			day = nextInt();
			if ((month + day) % 2 == 0
					|| (day == 30 && (month == 9 || month == 11)))
				out.println("YES");
			else
				out.println("NO");

		}

		out.flush();
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