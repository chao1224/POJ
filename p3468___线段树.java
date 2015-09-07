import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class p3468___Ïß¶ÎÊ÷ {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
	static int n;
	static long lazy[];
	static node[] arr;
	static char ch;

	static class node {
		int left, right, add;
		long sum;
		boolean mark;

		node() {
		}

		int calmid() {
			return (left + right) >> 1;
		}

		int len() {
			return right - left + 1;
		}
	}

	public static void main(String[] args) throws Exception {
		n = nextInt();
		int m = nextInt();
		lazy = new long[n + 1];
		arr = new node[n * 5];
		for (int i = 1; i <= n; i++)
			lazy[i] = nextInt();
		build(1, n, 1);

		int a, b, c;
		while (m-- > 0) {
			ch = next().charAt(0);
			if (ch == 'C') {
				a = nextInt();
				b = nextInt();
				c = nextInt();
				update(a, b, 1, c);
			} else {
				a = nextInt();
				b = nextInt();
				pw.println(query(a, b, 1));
			}
		}
		pw.flush();
		pw.close();
	}

	static long build(int left, int right, int idx) {
		arr[idx] = new node();
		arr[idx].left = left;
		arr[idx].right = right;
		arr[idx].add = 0;
		arr[idx].mark = false;
		if (left == right) {
			arr[idx].sum = lazy[left];
			return arr[idx].sum;
		}
		int mid = arr[idx].calmid();
		arr[idx].sum = build(left, mid, idx << 1)
				+ build(mid + 1, right, idx << 1 | 1);
		return arr[idx].sum;
	}

	static void update(int left, int right, int idx, int add) {
		pushDown(idx);
		if (arr[idx].left >= left && arr[idx].right <= right) {
			arr[idx].add += add;
			arr[idx].mark = true;
			return;
		}
		int mid = arr[idx].calmid();
		if (left <= mid)
			update(left, right, idx << 1, add);
		if (right > mid)
			update(left, right, idx << 1 | 1, add);
		pushUp(idx);
	}

	static long query(int left, int right, int idx) {
		pushDown(idx);
		if (left == arr[idx].left && right == arr[idx].right)
			return arr[idx].sum;
		int mid = arr[idx].calmid();
		if (right <= mid) {
			return query(left, right, idx << 1);
		} else if (mid < left) {
			return query(left, right, idx << 1 | 1);
		} else {
			return query(left, mid, idx << 1)
					+ query(mid + 1, right, idx << 1 | 1);
		}
	}

	static void pushUp(int idx) {
		if (arr[idx].left == arr[idx].right)
			return;
		pushDown(idx << 1);
		pushDown(idx << 1 | 1);
		arr[idx].sum = arr[idx << 1].sum + arr[idx << 1 | 1].sum;
	}

	static void pushDown(int idx) {
		if (arr[idx].mark) {
			arr[idx].mark = false;
			long k = arr[idx].add;
			arr[idx].sum += k * arr[idx].len();
			arr[idx].add = 0;
			if (arr[idx].left != arr[idx].right) {
				int t = idx << 1;
				arr[t].mark = true;
				arr[t].add += k;
				t++;
				arr[t].mark = true;
				arr[t].add += k;
			}
		}
	}

	// wa
	// static void pushDown(int idx) {
	// if (arr[idx].mark) {
	// if (arr[idx].left != arr[idx].right) {
	// arr[idx << 1].add += arr[idx].add;
	// arr[idx << 1 | 1].add += arr[idx].add;
	// arr[idx << 1].mark = true;
	// arr[idx << 1 | 1].mark = true;
	// }
	// arr[idx].sum += arr[idx].len() * arr[idx].add;
	// arr[idx].add = 0;
	// arr[idx].mark = false;
	// }
	// }

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return (String) in.sval;
	}

}
