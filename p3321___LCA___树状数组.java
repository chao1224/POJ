import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Vector;


public class p3321___LCA___Ê÷×´Êý×é {
	static int n, index = 1, root = 1, c[];
	static Vector<Integer>[] tree;
	static p3321__node[] list;
	static StreamTokenizer in;

	public static void main(String[] args) throws Exception {
		PrintWriter pw = new PrintWriter(System.out);
		in = new StreamTokenizer(new BufferedReader(new InputStreamReader(
				System.in)));
		int x, y, m;
		char ch;
		n = nextInt();
		tree = new Vector[n + 1];
		list = new p3321__node[n + 1];
		for (int i = 1; i <= n; i++) {
			tree[i] = new Vector<Integer>();
			list[i] = new p3321__node(0, 0);
		}

		for (int j = 1; j < n; j++) {
			x = nextInt();
			y = nextInt();
			tree[x].add(y);
		}

		visit(root, 0);

		c = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			insert(i, 1);
		}

		m = nextInt();
		for (int i = 1; i <= m; i++) {
			ch = next().charAt(0);
			if (ch == 'Q') {
				x = nextInt();
				pw.println(sum(list[x].r) - sum(list[x].l - 1));
			} else {
				x = nextInt();
				if ((sum(list[x].l) - sum(list[x].l - 1)) == 1)
					insert(list[x].l, -1);
				else
					insert(list[x].l, 1);
			}
		}
		pw.close();
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static void visit(int temp, int depth) {
		if (list[temp].l == 0) {
			list[temp].l = index;
		}
		for (int i = 0; i < tree[temp].size(); i++) {
			index++;
			visit(tree[temp].get(i), depth + 1);
		}
		list[temp].r = index;
	}

	static int lowbit(int x) {
		return x & -x;
	}

	static void insert(int x, int dif) {
		while (x <= n) {
			c[x] += dif;
			x += lowbit(x);
		}
	}

	static int sum(int x) {
		int ans = 0;
		while (x > 0) {
			ans += c[x];
			x -= lowbit(x);
		}
		return ans;
	}

}

class p3321__node implements Comparable<p3321__node> {
	int l, r;

	p3321__node(int l, int r) {
		this.l = l;
		this.r = r;
	}

	public int compareTo(p3321__node o) {
		if (this.r < o.r)
			return -1;
		else if (this.r > o.r)
			return 1;
		else if (this.l > o.l)
			return -1;
		else
			return 1;
	}
}

