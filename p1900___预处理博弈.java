import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1900___Ô¤´¦Àí²©ÞÄ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static StringTokenizer stoke;
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		m++;
		int[][] F = new int[n + 1][n + 1];
		int[] record = new int[1 + n * n];

		int deep = 0, a, b, tmp;
		while (true) {
			deep++;
			Arrays.fill(record, 0);
			tmp = 0;
			if ((deep & 1) == 1) {
				for (b = 1; b <= n; b++)
					for (a = 1; a < b; a++)
						if (F[a][b] == 0)
							record[a + b]++;
				for (b = 1; b <= n; b++)
					for (a = 1; a < b; a++)
						if (record[a + b] == 1 && F[a][b] == 0) {
							F[a][b] = deep;
							tmp++;
						}
			} else {
				for (b = 1; b <= n; b++)
					for (a = 1; a < b; a++)
						if (F[a][b] == 0)
							record[a * b]++;
				for (b = 1; b <= n; b++)
					for (a = 1; a < b; a++)
						if (record[a * b] == 1 && F[a][b] == 0) {
							F[a][b] = deep;
							tmp++;
						}
			}
			if (deep == m)
				break;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				out.print(F[i][j] + "  ");
			out.println();
		}

		out.println(tmp);
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				if (F[i][j] == m)
					out.println(i + " " + j);
		}
		out.flush();
		out.close();
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
