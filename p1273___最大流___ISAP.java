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

public class p1273___�����___ISAP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StreamTokenizer in = new StreamTokenizer(br);
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, m, cap[][], flow[][], h[], vh[];

	public static void main(String[] args) throws IOException {
		int x, y, val, ans, tmp;
		while (in.nextToken() != in.TT_EOF) {
			m = (int) in.nval;
			n = nextInt();
			cap = new int[n + 1][n + 1];
			flow = new int[n + 1][n + 1];
			for (int i = 1; i <= m; i++) {
				x = nextInt();
				y = nextInt();
				val = nextInt();
				cap[x][y] += val;
			}
			h = new int[n + 1];
			vh = new int[n + 1];
			ans = 0;
			while (h[1] != n)
				ans += sap(1, n*10000000);
			out.println(ans);
		}

		out.flush();
		out.close();
	}

	static int sap(int idx, int maxCap) {
		if (idx == n)
			return maxCap;// /���һ����㡣����
		int l = maxCap, d, minH = n;
		// /�˴εĲ�������, ĳ��ʹ�õ�����, �ھӵ���С����
		for (int i = 1; i <= n; i++) {
			if (cap[idx][i] - flow[idx][i] > 0) {
				if (h[idx] == h[i] + 1) {
					d = sap(i, Math.min(l, cap[idx][i] - flow[idx][i]));
					// /�´��ҵ�������
					flow[idx][i] += d; // /���±ߵĲ�������
					flow[i][idx] -= d;
					l -= d; // /���±��β�������
					if (h[1] == n || l == 0)
						return maxCap - l;// /GAP
				}
				minH = Math.min(minH, h[i] + 1); // /����h[idx]
			}
		}
		if (l == maxCap) { // /not found!
			vh[h[idx]]--; // /GAP
			vh[minH]++;
			if (vh[h[idx]] == 0)
				h[1] = n;
			h[idx] = minH;
		}
		return maxCap - l;
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
