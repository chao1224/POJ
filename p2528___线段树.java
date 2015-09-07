import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Set;
import java.util.TreeMap;

public class p2528___Ïß¶ÎÊ÷ {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static int n;
	static node arr[];

	public static void main(String[] args) throws IOException {
		int t = nextInt(), cnt, l, r, temp, ans;
		node edge[];
		TreeMap<Integer, Integer> tree;
		Set<Integer> set;
		for (int c = 1; c <= t; c++) {
			n = nextInt();
			edge = new node[n + 1];
			tree = new TreeMap<Integer, Integer>();
			for (int i = 1; i <= n; i++) {
				edge[i] = new node(nextInt(), nextInt());
				tree.put(edge[i].l, 0);
				tree.put(edge[i].r, 0);
			}

			cnt = 0;
			set = tree.keySet();
			for (int i : set)
				tree.put(i, ++cnt);

			arr = new node[cnt * 4];
			build(1, cnt, 1);

			ans = 0;
			for (int i = n; i > 0; i--) {
				l = tree.get(edge[i].l);
				r = tree.get(edge[i].r);
				temp = query(l, r, 1);
				if (temp == (r - l + 1)) {
					ans++;
				} else {
					update(l, r, 1);
				}
			}
			out.println(n - ans);
		}
		out.flush();
		out.close();
	}

	static void build(int l, int r, int idx) {
		arr[idx] = new node(l, r);
		if (l == r)
			return;
		int mid = arr[idx].calmid();
		build(l, mid, idx << 1);
		build(mid + 1, r, idx << 1 | 1);
	}

	static void update(int l, int r, int idx) {
		if (arr[idx].l >= l && arr[idx].r <= r) {
			arr[idx].flag = true;
			arr[idx].sum = arr[idx].len();
			return;
		}
		if (arr[idx].flag) {
			arr[idx << 1].flag = arr[idx << 1 | 1].flag = true;
			arr[idx].flag = false;
			arr[idx << 1].sum = arr[idx << 1].len();
			arr[idx << 1 | 1].sum = arr[idx << 1 | 1].len();
		}
		int mid = arr[idx].calmid();
		if (l <= mid)
			update(l, r, idx << 1);
		if (r > mid)
			update(l, r, idx << 1 | 1);
		arr[idx].sum = arr[idx << 1].sum + arr[idx << 1 | 1].sum;
	}

	static int query(int l, int r, int idx) {
		if (l == arr[idx].l && r == arr[idx].r) {
			return arr[idx].sum;
		}
		if (arr[idx].flag) {
			arr[idx << 1].flag = arr[idx << 1 | 1].flag = true;
			arr[idx].flag = false;
			arr[idx << 1].sum = arr[idx << 1].len();
			arr[idx << 1 | 1].sum = arr[idx << 1 | 1].len();
		}
		int mid = arr[idx].calmid();
		if (r <= mid)
			return query(l, r, idx << 1);
		else if (l > mid)
			return query(l, r, idx << 1 | 1);
		else
			return query(l, mid, idx << 1) + query(mid + 1, r, idx << 1 | 1);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static class node {
		int l, r, sum;
		boolean flag;

		node(int l, int r) {
			this.l = l;
			this.r = r;
			this.sum = 0;
			this.flag = false;
		}

		int calmid() {
			return (l + r) >> 1;
		}

		int len() {
			return r - l + 1;
		}

	}

}
