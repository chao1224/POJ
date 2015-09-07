import java.util.Scanner;

public class p1611___²¢²é¼¯ {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n, m = 0, k, a, b, ra, rb, ans, bonus;
		DisjointSet ds;
		while ((n = scan.nextInt()) != 0) {
			m = scan.nextInt();
			ds = new DisjointSet(n);
			for (int i = 1; i <= m; i++) {
				k = scan.nextInt();
				a = scan.nextInt();
				for (int j = 2; j <= k; j++) {
					b = scan.nextInt();
					ra = ds.find(a);
					rb = ds.find(b);
					if (ra != rb) {
						ds.union(ra, rb);
					}
				}
			}
			ans = 0;
			bonus = ds.find(0);
			for (int i = 0; i < n; i++)
				if (ds.find(i) == bonus)
					ans++;
			System.out.println(ans);
		}
	}
}

class DDisjointSet {
	int[] rank;
	int[] f;

	public DDisjointSet(int n) {
		f = new int[n];
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			f[i] = i;
			rank[i] = 1;
		}
	}

	void union(int a, int b) {
		if (rank[a] > rank[b]) {
			f[b] = a;
		} else if (rank[b] > rank[a]) {
			f[a] = b;
		} else {
			rank[b]++;
			f[a] = b;
		}
	}

	int find(int x) {
		if (f[x] != x)
			f[x] = find(f[x]);
		return f[x];
	}
}
