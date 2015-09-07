import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Set;
import java.util.TreeMap;

public class p2418___×ÖµäTreeMap {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int cur, total = 0;
		String name;

		while ((name = reader.readLine()) != null) {
			if (map.containsKey(name)) {
				cur = map.get(name);
				map.put(name, cur + 1);
			} else {
				map.put(name, 1);
			}
			total++;
		}

		Set<String> set = map.keySet();
		for (String s : set) {
			out.println(s + " "
					+ String.format("%.4f", (100 * map.get(s) + 0.0) / total));
		}
		out.flush();
		out.close();
	}
}
