import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

public class p1080___lcs±‰–Œ___±‡º≠æ‡¿Î {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int table[][];

	public static void main(String[] args) throws IOException {
		table = new int[85][85];
		table['A']['A'] = table['G']['G'] = table['C']['C'] = table['T']['T'] = 5;
		table['A']['C'] = table['C']['A'] = table['A']['T'] = table['T']['A'] = table['T']['-'] = table['-']['T'] = -1;
		table['A']['G'] = table['G']['A'] = table['T']['C'] = table['C']['T'] = table['G']['-'] = table['-']['G'] = table['G']['T'] = table['T']['G'] = -2;
		table['A']['-'] = table['-']['A'] = table['C']['G'] = table['G']['C'] = -3;
		table['C']['-'] = table['-']['C'] = -4;
		int t = nextInt(), n, m, c[][];
		char chx, chy;
		String a, b;
		for (int cc = 1; cc <= t; cc++) {
			n = nextInt();
			a = next();
			m = nextInt();
			b = next();
			c = new int[n + 1][m + 1];

			for (int j = 1; j <= m; j++) {
				c[0][j] = c[0][j - 1] + table[b.charAt(j - 1)]['-'];
			}

			for (int i = 1; i <= n; i++) {
				c[i][0] = c[i - 1][0] + table[a.charAt(i - 1)]['-'];
			}

			for (int i = 1; i <= n; i++) {
				chx = a.charAt(i - 1);
				for (int j = 1; j <= m; j++) {
					chy = b.charAt(j - 1);
					c[i][j] = Math.max(c[i - 1][j - 1] + table[chx][chy], Math
							.max(c[i - 1][j] + table[chx]['-'], c[i][j - 1]
									+ table[chy]['-']));
				}
			}

			out.println(c[n][m]);
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