import java.util.LinkedList;
import java.util.Scanner;

public class p3750___Լɪ�򻷼�ģ�� {
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
		// �ҳ���w���ˣ��ӵ�w���˿�ʼ����
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
		// �ӵ�w����ʼ����������s�ĳ���
		count = 0;
		while (!queue.isEmpty()) {
			// ��1��ʼ����
			count++;
			// ����һ���˱���
			String person = (String) queue.removeFirst();
			if (count == s) {
				System.out.println(person);
				count = 0;// ����Ӻ����±���
			} else {
				queue.add(person);
			}
		}
	}
}