import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class p2761___树状数组___离散化___二分 {
	static int n, m;
	static int[] c, answer, arr;
	static problem[] problemList;
	static lisan[] hash;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str = new StringTokenizer(br.readLine());
		n = Integer.parseInt(str.nextToken());
		m = Integer.parseInt(str.nextToken());
		hash = new lisan[n + 1];
		arr = new int[n + 1];
		int[] rehash = new int[n + 1];

		str = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			hash[i] = new lisan(i, Integer.parseInt(str.nextToken()));
		}
		Arrays.sort(hash, 1, n + 1, null);
		for (int i = 1; i <= n; i++) {
			rehash[i] = hash[i].val;
			hash[i].val = i;
		}

		for (int i = 1; i <= n; i++)
			arr[hash[i].key] = hash[i].val;

		// for (int i = 1; i <= n; i++) {
		// System.out.print(arr[i] + " ");
		// }
		// System.out.println();

		problemList = new problem[m + 1];
		answer = new int[m + 1];
		c = new int[n + 1];
		for (int i = 1; i <= m; i++) {
			str = new StringTokenizer(br.readLine());
			problemList[i] = new problem(Integer.parseInt(str.nextToken()),
					Integer.parseInt(str.nextToken()), Integer.parseInt(str
							.nextToken()), i);
		}
		Arrays.sort(problemList, 1, m + 1, null);
		br.close();

		int a, b, k, l = problemList[1].l, r = problemList[1].l - 1, begin, end, mid;
		for (int i = 1; i <= m; i++) {
			a = problemList[i].l;
			b = problemList[i].r;
			k = problemList[i].k;

			for (int j = l; j < a; j++)
				insert(arr[j], -1);
			for (int j = r + 1; j <= b; j++)
				insert(arr[j], 1);

			begin = 1;
			end = n;
			while (begin <= end) {
				mid = (begin + end) >> 1;
				if (sum(mid) >= k)
					end = mid - 1;
				else
					begin = mid + 1;
			}
			answer[problemList[i].id] = rehash[begin];
			l = a;
			r = b;
		}
		PrintWriter pw = new PrintWriter(System.out);
		for (int i = 1; i <= m; i++)
			pw.println(answer[i]);
		pw.close();
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

class problem implements Comparable<problem> {
	int l, r, k, id;

	problem(int l, int r, int k, int id) {
		this.l = l;
		this.r = r;
		this.k = k;
		this.id = id;
	}

	public int compareTo(problem o) {
		if (this.l < o.l)
			return -1;
		else if (this.l > o.l)
			return 1;
		else if (this.r < o.r)
			return -1;
		else
			return 1;
	}
}

class lisan implements Comparable<lisan> {
	int key, val, trueValue;

	lisan(int key, int val) {
		this.key = key;
		this.val = val;
		this.trueValue = val;
	}

	public int compareTo(lisan o) {
		if (this.val < o.val)
			return -1;
		else
			return 1;
	}


}
