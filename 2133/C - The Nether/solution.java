import java.io.*;
import java.util.*;
 
public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter writer = new PrintWriter(System.out);
 
    static long query(int x, List<Integer> subset) throws IOException {
        writer.print("? " + x + " " + subset.size() + " ");
        for (int v : subset) writer.print(v + " ");
        writer.println();
        writer.flush();
 
        String line = reader.readLine();
        if (line == null) System.exit(0);
        long res = Long.parseLong(line.trim());
        if (res == -1) System.exit(0);
        return res;
    }
 
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(reader.readLine().trim());
 
        while (T-- > 0) {
            int n = Integer.parseInt(reader.readLine().trim());
 
            List<Integer> allGears = new ArrayList<>();
            for (int i = 1; i <= n; i++) allGears.add(i);
 
            int[] lengths = new int[n + 1];
            Arrays.fill(lengths, 1);
 
            for (int gear = 1; gear <= n; gear++) {
                lengths[gear] = (int) query(gear, allGears);
            }
 
            int startGear = 1, maxLen = lengths[1];
            for (int i = 2; i <= n; i++) {
                if (lengths[i] > maxLen) {
                    maxLen = lengths[i];
                    startGear = i;
                }
            }
 
            List<List<Integer>> gearsByLength = new ArrayList<>();
            for (int i = 0; i <= maxLen; i++) gearsByLength.add(new ArrayList<>());
            for (int v = 1; v <= n; v++) {
                if (lengths[v] >= 1 && lengths[v] <= maxLen) {
                    gearsByLength.get(lengths[v]).add(v);
                }
            }
 
            List<Integer> path = new ArrayList<>();
            boolean[] visited = new boolean[n + 1];
            int current = startGear;
            path.add(current);
            visited[current] = true;
 
            for (int need = maxLen - 1; need >= 1; need--) {
                int nextGear = -1;
 
                for (int candidate : gearsByLength.get(need)) {
                    if (visited[candidate]) continue;
                    List<Integer> pair = Arrays.asList(current, candidate);
                    long response = query(current, pair);
                    if (response == 2) {
                        nextGear = candidate;
                        break;
                    }
                }
 
                if (nextGear == -1) {
                    writer.print("! 1 " + startGear + " ");
                    writer.println();
                    writer.flush();
                    continue;
                }
 
                path.add(nextGear);
                visited[nextGear] = true;
                current = nextGear;
            }
 
            writer.print("! " + path.size() + " ");
            for (int g : path) writer.print(g + " ");
            writer.println();
            writer.flush();
        }
    }
}