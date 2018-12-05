package CodingPart;
import java.util.*;
/**
 * Created by yuqishi on 11/8/18.
 */
public class SlidingGame {
    int[][] matrix;
    int m, n;
    int originX, originY;
    String source;
    String target;
    int[] dx, dy;
    String[] dir;

    public SlidingGame(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.source = matrixToString(matrix.clone());
        int[][] targetMatrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    this.originX = i;
                    this.originY = j;
                }
                targetMatrix[i][j] = i * n + j;
            }
        }
        this.target = matrixToString(targetMatrix);
        this.dx = new int[]{0,0,-1,1};
        this.dy = new int[]{1,-1,0,0};
        this.dir = new String[]{"Right", "Left", "Up","Down"};
    }

    public boolean canSolve() {
        Queue<int[]> q = new LinkedList<>();
        Queue<String> q_matrix = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(new int[]{originX, originY});
        q_matrix.offer(source);
        visited.add(source);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            String curMatrix = q_matrix.poll();
            for (int k = 0; k < 4; k++) {
                int newX = cur[0] + dx[k];
                int newY = cur[1] + dy[k];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }

                int[][] newMatrix = stringToMatrix(curMatrix);
                int temp = newMatrix[cur[0]][cur[1]];
                newMatrix[cur[0]][cur[1]] = newMatrix[newX][newY];
                newMatrix[newX][newY] = temp;
                String newMatrixString = matrixToString(newMatrix);
                if (newMatrixString.equals(target)) {
                    return true;
                }
                if (!visited.contains(newMatrixString)) {
                    q.offer(new int[]{newX, newY});
                    q_matrix.offer(newMatrixString);
                    visited.add(newMatrixString);
                }
            }
        }
        return false;
    }

    public List<String> getPath() {

        Queue<int[]> q = new LinkedList<>();
        Queue<String> q_matrix = new LinkedList<>();
        Queue<List<String>> q_path = new LinkedList<>();
        Set<String> visited = new HashSet<>();


        q.offer(new int[]{originX, originY});
        q_matrix.offer(source);
        q_path.offer(new ArrayList<>());
        visited.add(source);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                String curMatrix = q_matrix.poll();
                List<String> curPath = q_path.poll();

                if (curMatrix.equals(target)) {
                    return curPath;
                }
                for (int k = 0; k < 4; k++) {
                    int newX = cur[0] + dx[k];
                    int newY = cur[1] + dy[k];
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                        continue;
                    }

                    int[][] newMatrix = stringToMatrix(curMatrix);
                    int temp = newMatrix[cur[0]][cur[1]];
                    newMatrix[cur[0]][cur[1]] = newMatrix[newX][newY];
                    newMatrix[newX][newY] = temp;
                    String newMatrixString = matrixToString(newMatrix);

                    if (!visited.contains(newMatrixString)) {
                        List<String> newPath = new ArrayList<>(curPath);
                        newPath.add(dir[k]);

                        q.offer(new int[]{newX, newY});
                        q_matrix.offer(newMatrixString);
                        visited.add(newMatrixString);
                        q_path.offer(newPath);
                    }
                }
            }

        }
        return new ArrayList<>();
    }

    public String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j]).append(",");
            }
        }
        return sb.toString();
    }

    public int[][] stringToMatrix(String s) {
        String[] arr = s.split(",");
        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = Integer.parseInt(arr[i * n + j]);
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[][] matrix = {{3, 1, 4},
                {6, 2, 0},
                {7, 5, 8}};
        SlidingGame ss = new SlidingGame(matrix);
        System.out.println(ss.canSolve());
        System.out.println(ss.getPath());
    }

}
