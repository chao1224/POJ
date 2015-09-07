import java.util.LinkedList;
import java.util.Scanner;

public class p3750___约瑟夫环简单模拟 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = Integer.parseInt(scan.nextLine());
		LinkedList queue = new LinkedList();
		for (int i = 0; i < n; i++) {
			String name = scan.nextLine();
			queue.add(name.trim());
		}
		String ws = scan.nextLine();
		String[] wss = ws.split(",");
		int w = Integer.parseInt(wss[0]);
		int s = Integer.parseInt(wss[1]);

		int count = 0;
		// 找出第w个人，从第w个人开始报数
		while (true) {
			count++;
			if (count == n + 1) {
				count = 1;
			}
			if (count == w) {
				break;
			}
			String person = (String) queue.removeFirst();
			queue.add(person);

		}
		// 从第w个开始报数，报到s的出队
		count = 0;
		while (!queue.isEmpty()) {
			// 从1开始报数
			count++;
			// 出来一个人报数
			String person = (String) queue.removeFirst();
			if (count == s) {
				System.out.println(person);
				count = 0;// 出完队后，重新报数
			} else {
				queue.add(person);
			}
		}
	}
}