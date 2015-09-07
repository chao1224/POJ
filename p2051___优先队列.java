import java.util.PriorityQueue;
import java.util.Scanner;

public class p2051___优先队列 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PriorityQueue<p2051__node> queue = new PriorityQueue();
		while (scan.next().equals("Register")) {
			queue.add(new p2051__node(scan.nextInt(), scan.nextInt()));
		}
		int k = scan.nextInt();
		p2051__node cur;
		for (int i = 1; i <= k; i++) {
			cur = queue.poll();
			System.out.println(cur.id);
			cur.frequent += cur.charg;
			queue.add(cur);
		}
	}
}

class p2051__node implements Comparable<p2051__node> {
	int id, charg, frequent;

	p2051__node(int id, int charg) {
		this.id = id;
		this.charg = charg;
		this.frequent = charg;
	}

	public int compareTo(p2051__node n) {
		if (this.frequent < n.frequent)
			return -1;
		else if (this.frequent > n.frequent)
			return 1;
		else if (this.id < n.id)
			return -1;
		return 1;
	}

}
