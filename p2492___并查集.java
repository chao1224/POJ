import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class p2492___²¢²é¼¯ {
	static int[] father, relation;
	static int n, m, index;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int t = Integer.parseInt(br.readLine());
		int n, m, a, b, ra, rb;
		StringTokenizer str;
		for (int i = 1; i <= t; i++) {
			str = new StringTokenizer(br.readLine());
			n = Integer.parseInt(str.nextToken());
			m = Integer.parseInt(str.nextToken());
			init(n);
			for (int j = 1; j <= m; j++) {
				str = new StringTokenizer(br.readLine());
				a = Integer.parseInt(str.nextToken());
				b = Integer.parseInt(str.nextToken());
				ra = find(a);
				rb = find(b);
				if (ra == rb) {
					if (relation[a] == relation[b]) {
						flag = false;
					}
				} else {
					union(a, b, ra, rb);
				}
			}
			pw.println("Scenario #" + i + ":");
			if (flag)
				pw.println("No suspicious bugs found!");
			else
				pw.println("Suspicious bugs found!");
			pw.println();
		}
		br.close();
		pw.close();
	}

	static void init(int n) {
		flag = true;
		father = new int[n + 1];
		relation = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			father[i] = i;
			relation[i] = 0;
		}
	}

	static int find(int x) {
		if (father[x] == x)
			return x;
		int temp = father[x];
		father[x] = find(father[x]);
		relation[x] = (relation[x] + relation[temp]) % 2;
		return father[x];
	}

	static void union(int a, int b, int ra, int rb) {
		father[ra] = rb;
		relation[ra] = (1 + relation[b] - relation[a]) % 2;
	}
}
