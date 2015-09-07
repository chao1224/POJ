import java.util.Scanner;
import java.util.TreeMap;


public class p3481___×ÖµäTreeMap {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int type, k, p, index;
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

		while (true) {
			type = scan.nextInt();
			if (type == 0)
				break;
			else if (type == 1) {
				k = scan.nextInt();
				p = scan.nextInt();
				map.put(p, k);
			} else if (type == 2) {
				if (map.isEmpty()) {
					System.out.println(0);
				} else {
					index = map.lastKey();
					System.out.println(map.get(index));
					map.remove(index);
				}
			} else {
				if (map.isEmpty()) {
					System.out.println(0);
				} else {
					index = map.firstKey();
					System.out.println(map.get(index));
					map.remove(index);
				}
			}
		}
	}
}

