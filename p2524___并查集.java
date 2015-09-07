import java.util.Scanner;

public class p2524___²¢²é¼¯ {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, m, case_num = 0, a, b, ra, rb, count;
		DisjointSet ds;
		while ((n = scan.nextInt()) != 0 && (m = scan.nextInt()) != 0) {
			count = 0;
			case_num++;
			ds = new DisjointSet(50001);
			for (int y = 1; y <= m; y++) {
				a = scan.nextInt();
				b = scan.nextInt();
				ra = ds.find(a);
				rb = ds.find(b);
				if (ra != rb) {
					// System.out.println(a + "   " + b);
					ds.union(ra, rb);
					// System.out.println(ds.find(a) + "   " + ds.find(b));
					count++;
				}
			}
			// System.out.println(ds.find(4) + "  " + ds.find(5) + "  "
			// + ds.find(8));
			System.out.println("Case " + case_num + ": " + (n - count));
		}
	}
}

class DisjointSet {
	int[] rank;
	int[] f;

	public DisjointSet(int n) {
		f = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			f[i] = i;
			rank[i] = 1;
		}

	}

	void union(int ra, int rb) {
		if (rank[ra] > rank[rb]) {
			f[rb] = ra;
		} else if (rank[rb] > rank[ra]) {
			f[ra] = rb;
		} else {
			rank[rb]++;
			f[ra] = rb;
		}
	}

	int find(int x) {
		if (f[x] != x)
			f[x] = find(f[x]);
		return f[x];
	}

}
