package com.sleepyocean.exercise.simple;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode {
    public static void main(String[] args) {
        question100_minimumTotal(new ArrayList<>());
    }

    public static int question100_minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1) return triangle.get(0).get(0);
        int result = 0;
        return result;
    }

    public static int question509_fib(int n) {
        return 1;
    }

    public static int[][] question542_updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && mat[newX][newY] == -1) {
                    mat[newX][newY] = mat[x][y] + 1;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
        return mat;
    }

    public static int[][] question733_floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        internal_question733_deepLook(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    public static int question733_maxAreaOfIsland(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    ans = Math.max(ans, internal_question733_dfs(grid, i, j));
                }
            }
        }
        return ans;
    }

    public static int[][] question733_updateMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != 0) {
                    mat[i][j] = internal_question733_dfsMatrix(mat, i, j);
                }
            }
        }
        return mat;
    }

    private static void internal_question733_deepLook(int[][] image, int sr, int sc, int currentColor, int newColor) {
        if (image[sr][sc] != currentColor) return;
        image[sr][sc] = newColor;

        if (sr > 0) {
            internal_question733_deepLook(image, sr - 1, sc, currentColor, newColor);
        }
        if (sr + 1 < image.length) {
            internal_question733_deepLook(image, sr + 1, sc, currentColor, newColor);
        }
        if (sc > 0) {
            internal_question733_deepLook(image, sr, sc - 1, currentColor, newColor);
        }
        if (sc + 1 < image[0].length) {
            internal_question733_deepLook(image, sr, sc + 1, currentColor, newColor);
        }
    }

    private static int internal_question733_dfs(int[][] grid, int i, int j) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length && grid[i][j] != 0) {
            grid[i][j] = 0;
            return 1 + internal_question733_dfs(grid, i - 1, j) + internal_question733_dfs(grid, i + 1, j) + internal_question733_dfs(grid, i, j - 1) + internal_question733_dfs(grid, i, j + 1);
        } else {
            return 0;
        }
    }

    private static int internal_question733_dfsMatrix(int[][] mat, int i, int j) {
        if (i < 0 || i + 1 > mat.length || j < 0 || j + 1 > mat[0].length || mat[i][j] == 0) return 0;
        int ans = 1;
        ans = Math.min(ans, internal_question733_dfsMatrix(mat, i - 1, j) + 1);
        ans = Math.min(ans, internal_question733_dfsMatrix(mat, i + 1, j) + 1);
        ans = Math.min(ans, internal_question733_dfsMatrix(mat, i, j - 1) + 1);
        ans = Math.min(ans, internal_question733_dfsMatrix(mat, i, j + 1) + 1);
        return ans;
    }
}
