package algorithm.util.graph;


import java.util.ArrayList;
import java.util.List;

public class ToplogicalSort {

    public int[] find(int numOfCourse, ArrayList<ArrayList<Integer>> graph) {
        int[] visited = new int[numOfCourse];
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < numOfCourse; ++i)
            if (dfs(i, graph, visited, answer)) return new int[0];

        return answer.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int current, ArrayList<ArrayList<Integer>> graph, int[] visited, List<Integer> answer) {
        if (visited[current] == 1) return true;
        if (visited[current] == 2) return false;

        visited[current] = 1;
        for (int next : graph.get(current))
            if (dfs(next, graph, visited, answer)) return true;

        visited[current] = 2;
        answer.add(current);

        return false;
    }

}
