import java.util.Scanner;


public class p1988___并查集应用 {
	static int[] father = new int[30001];
	static int[] size = new int[30001];
	static int[] relation = new int[30001];

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int k = scan.nextInt();
		int a, b;
		char ch;
		for (int i = 1; i <= 30000; i++) {
			father[i] = i;
			size[i] = 1;
		}
		for (int i = 0; i < k; i++) {
			ch = scan.next().charAt(0);
			if (ch == 'M') {
				a = scan.nextInt();
				b = scan.nextInt();
				union(a, b);
			} else {
				a = scan.nextInt();
				find(a);
				System.out.println(relation[a]);
			}
		}
	}

	static int find(int x) {
		if (x == father[x])
			return father[x];
		int temp = father[x];
		father[x] = find(father[x]);
		relation[x] += relation[temp];
		return father[x];
	}

	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		father[ra] = rb;
		relation[ra] += size[rb];
		size[rb] += size[ra];
	}
}
