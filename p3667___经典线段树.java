import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class p3667___¾­µäÏß¶ÎÊ÷ {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
	static int n, ch;
	static p3667___node[] arr;

	public static void main(String[] args) throws Exception {
		n = nextInt();
		arr = new p3667___node[n * 5];
		build(1, n, 1);

		int m = nextInt(), a, b;
		while (m-- > 0) {
			ch = nextInt();
			if (ch == 1) {
				a = nextInt();
				b = query(a, 1);
				pw.println(b);
				if (b > 0) {
					update(b, a + b - 1, 1, 1);
				}
			} else {
				a = nextInt();
				b = nextInt();
				update(a, a + b - 1, 0, 1);
			}
		}
		pw.flush();
		pw.close();
	}

	static void build(int left, int right, int idx) {
		arr[idx] = new p3667___node(left, right);
		arr[idx].max = arr[idx].lmax = arr[idx].rmax = arr[idx].len();
		if (left == right) {
			return;
		}
		int mid = arr[idx].calmid();
		build(left, mid, idx << 1);
		build(mid + 1, right, idx << 1 | 1);
	}

	static void update(int left, int right, int lazy, int idx) {
		if (left <= arr[idx].left && right >= arr[idx].right) {
			arr[idx].lazy = lazy;
			arr[idx].clear();
			return;
		}

		// pushDown(idx);
		if (arr[idx].lazy != -1) {
			arr[idx << 1].lazy = arr[idx << 1 | 1].lazy = arr[idx].lazy;
			arr[idx << 1].clear();
			arr[idx << 1 | 1].clear();
			arr[idx].lazy = -1;
		}
		int mid = arr[idx].calmid();
		if (left <= mid)
			update(left, right, lazy, idx << 1);
		if (mid < right)
			update(left, right, lazy, idx << 1 | 1);

		// pushUp(idx);
		arr[idx].max = Math.max(arr[idx << 1].rmax + arr[idx << 1 | 1].lmax,
				Math.max(arr[idx << 1].max, arr[idx << 1 | 1].max));
		arr[idx].lmax = arr[idx << 1].lmax;
		arr[idx].rmax = arr[idx << 1 | 1].rmax;
		if (arr[idx << 1].max == arr[idx << 1].len())
			arr[idx].lmax += arr[idx << 1 | 1].lmax;
		if (arr[idx << 1 | 1].max == arr[idx << 1 | 1].len())
			arr[idx].rmax += arr[idx << 1].rmax;

	}

	static int query(int val, int idx) {
		if (arr[idx].left == arr[idx].right && val == 1) {
			return arr[idx].left;
		}
		// pushDown(idx);
		if (arr[idx].lazy != -1) {
			arr[idx << 1].lazy = arr[idx << 1 | 1].lazy = arr[idx].lazy;
			arr[idx << 1].clear();
			arr[idx << 1 | 1].clear();
			arr[idx].lazy = -1;
		}

		if (arr[idx << 1].max >= val) {
			return query(val, idx << 1);
		} else if (arr[idx << 1].rmax + arr[idx << 1 | 1].lmax >= val) {
			return arr[idx << 1].right - arr[idx << 1].rmax + 1;
		} else if (arr[idx << 1 | 1].max >= val) {
			return query(val, idx << 1 | 1);
		} else
			return 0;
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws IOException {
		in.nextToken();
		return (String) in.sval;
	}

}

class p3667___node {
	int left, right, max, lmax, rmax;
	int lazy = -1;

	p3667___node(int left, int right) {
		this.left = left;
		this.right = right;
	}

	int calmid() {
		return (left + right) >> 1;
	}

	int len() {
		return right - left + 1;
	}

	void clear() {
		if (lazy != 0)
			max = rmax = lmax = 0;
		else
			max = rmax = lmax = len();
	}
}