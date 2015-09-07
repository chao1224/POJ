import java.io.*;
import java.util.*;

public class p1781___约瑟夫环数学解法___具体数学 {
	static int n, k;

	static int turn(String str) {
		int ret = 0;
		ret = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
		for (int i = '1'; i <= str.charAt(3); i++)
			ret = ret * 10;
		return ret;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			int n = turn(scan.next());
			if (n == 0)
				break;
			int temp = 1;
			while (temp <= n)
				temp <<= 1;
			temp >>= 1;
			System.out.println(2 * (n - temp) + 1);
		}
	}

}
