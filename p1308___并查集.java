import java.util.Scanner;


public class p1308___²¢²é¼¯ {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a, b, ra, rb;

		maxsize = 10000;
		int[] begin;
		int[] end;
		int index, root;

		boolean mark = true;
		int case_num = 0;
		while (scan.hasNext()) {
			a = scan.nextInt();
			b = scan.nextInt();
			if (a == -1 && b == -1)
				break;
			case_num++;
			root = 0;
			mark = true;
			begin = new int[maxsize];
			end = new int[maxsize];
			for (index = 0;; index++) {
				if (a == 0 && b == 0)
					break;
				begin[index] = a;
				end[index] = b;
				a = scan.nextInt();
				b = scan.nextInt();
			}

			if (index == 0)
				System.out.println("Case " + case_num + " is a tree.");
			else {
				mark = true;
				init();
				for (int i = 0; i < index; i++) {
					a = begin[i];
					b = end[i];
					if (father[a] == 0)
						init(a);
					if (father[b] == 0)
						init(b);

					root = a;
					ra = find(a);
					rb = find(b);
					if (ra == rb) {
						mark = false;
						break;
					} else {
						union(ra, rb);
					}
				}
				if (mark) {
					int k;
					mark = true;
					for (k = 0; k < maxsize; k++) {
						if (father[k] != 0) {
							if (find(k) != find(root)) {
								mark = false;
								break;
							}
						}
					}
					if (mark)
						System.out.println("Case " + case_num + " is a tree.");
					else
						System.out.println("Case " + case_num
								+ " is not a tree.");
				} else
					System.out.println("Case " + case_num + " is not a tree.");
			}
		}
	}

	static int maxsize;
	static int[] father, rank;

	static void init() {
		father = new int[maxsize + 1];
		rank = new int[maxsize + 1];
	}

	static void init(int x) {
		father[x] = x;
		rank[x] = 1;
	}

	static int find(int x) {
		if (father[x] == x)
			return x;
		father[x] = find(father[x]);
		return father[x];
	}

	static void union(int ra, int rb) {
		if (rank[ra] > rank[rb]) {
			father[rb] = ra;
		} else {
			if (rank[ra] == rank[rb])
				rank[rb]++;
			father[ra] = rb;
		}
	}

}
