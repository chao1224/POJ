import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

class P1330___LCA {
	static int n, index, root, f[], b[], pos[];
	static Vector<Integer>[] tree;
	static StreamTokenizer in;

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(
				System.in)));
		PrintWriter pw = new PrintWriter(System.out);
		int x, y;
		boolean[] mark;
		int t = nextInt();
		while (t-- > 0) {
			n = nextInt();
			mark = new boolean[n + 1];
			tree = new Vector[n + 1];
			f = new int[2 * n];
			pos = new int[n + 1];
			b = new int[n + 1];
			for (int i = 1; i <= n; i++)
				tree[i] = new Vector<Integer>();

			for (int j = 1; j < n; j++) {
				x = nextInt();
				y = nextInt();
				mark[y] = true;
				tree[x].add(y);
			}
			for (int i = 1; i <= n; i++)
				if (!mark[i]) {
					root = i;
					break;
				}
			index = 1;
			visit(root, 0);

			// for (int i = 1; i <= 2 * n - 1; i++) {
			// pw.print(f[i] + "  ");
			// }
			// pw.println();
			// for (int i = 1; i <= 2 * n - 1; i++) {
			// pw.print(b[f[i]] + "  ");
			// }
			// pw.println();
			// for (int i = 1; i <= n; i++)
			// pw.print(pos[i] + "  ");
			// pw.println();

			x = nextInt();
			int ans = x, dep = b[x], temp;
			x = pos[x];
			y = pos[nextInt()];
			if (x > y) {
				x = x ^ y;
				y = x ^ y;
				x = x ^ y;
			}
			for (int i = x; i <= y; i++) {
				temp = f[i];
				if (b[temp] < dep) {
					dep = b[temp];
					ans = temp;
				}
			}
			pw.println(ans);
		}
		pw.close();
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void visit(int temp, int depth) {
		if (pos[temp] == 0) {
			pos[temp] = index;
			b[temp] = depth;
		}
		f[index++] = temp;
		if (tree[temp].size() == 0)
			return;
		for (int i = 0; i < tree[temp].size(); i++) {
			visit(tree[temp].get(i), depth + 1);
			f[index++] = temp;
		}
	}
}