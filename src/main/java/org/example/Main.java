package org.example;

import java.io.IOException;
import java.util.*;

public class Main {

    static class Solution {

        static int[] dx = {0, 0, -1, 1};
        static int[] dy = {-1, 1, 0, 0};
        static boolean flag = true;
        static int lx;
        static int ly;
        static int cnt = 0;
        public static void toLeverDfs(ArrayList<ArrayList<String>> maps, boolean[][] visit, int x, int y) {

            if (Objects.equals(maps.get(x).get(y), "L")) {
                cnt++;
                if (flag){
                    lx = x;
                    ly = y;
                    flag = false;
                }
                return;
            }

            fourWay(maps, visit, x, y);
        }

        public static void toExitDfs(ArrayList<ArrayList<String>> maps, boolean[][] visit, int x, int y) {

            if (Objects.equals(maps.get(x).get(y), "E")) {
                return;
            }

            fourWay(maps, visit, x, y);
        }

        private static void fourWay(ArrayList<ArrayList<String>> maps, boolean[][] visit, int x, int y) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!(nx >= 0 && ny >= 0 && nx < maps.size() && ny < maps.size())) continue;
                if (!visit[x][y] && !Objects.equals(maps.get(x).get(y), "X")){
                    visit[nx][ny] = true;
                    toLeverDfs(maps, visit, nx, ny);
                    visit[nx][ny] = false;
                }
            }
        }

        public static int solution(String[] maps) {

            int x = 0;
            int y = 0;

            ArrayList<ArrayList<String>> arr = new ArrayList<>();
            for (int i = 0; i < maps.length; i++) {
                ArrayList<String> lst = new ArrayList<>(List.of(maps[i].split("")));
                for (int j = 0; j < maps[i].length(); j++) {
                    if (Objects.equals(lst.get(j), "S")) {
                        System.out.println(1);
                        x = i;
                        y = j;
                    }
                }
                arr.add(lst);
            }


            boolean[][] visit = new boolean[x][y];

            toLeverDfs(arr, visit, x, y);
            toExitDfs(arr, visit, lx, ly);

            return cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        String[] maps = {"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"};
        System.out.println(Solution.solution(maps));
    }
}