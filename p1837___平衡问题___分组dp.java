import java.util.Scanner;

public class p1837___平衡问题___分组dp {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int c_num = scan.nextInt();
		int g_num = scan.nextInt();

		int[] c = new int[c_num + 1];
		int[] g = new int[g_num + 1];
		for (int j = 1; j <= c_num; j++) {
			c[j] = scan.nextInt();
		}
		for (int i = 1; i <= g_num; i++) {
			g[i] = scan.nextInt();
		}
		int max = 15000;
		
		int[][] f = new int[g_num + 1][max + 1];
		f[0][7500] = 1;
		
		for (int i = 1; i <= g_num; i++) {
			for (int j = 1; j <= c_num; j++) {
				int v = g[i] * c[j];
				for (int k = 0; k <= max; k++) {
					if (f[i - 1][k] != 0) {
						f[i][k + v] += f[i - 1][k];
					}
				}
			}
		}
		System.out.println(f[g_num ][7500]);
	}
}