package org.example;

import java.util.Collections;
import java.util.PriorityQueue;

public class defenseGame {
    static class Solution {
        public static int solution(int n, int k, int[] enemy) {
            int answer = enemy.length;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < enemy.length; i++) {
                n -= enemy[i];
                maxHeap.add(enemy[i]);

                if (n < 0) {
                    if (k > 0) {
                        n += maxHeap.poll();
                        k--;
                    } else {
                        answer = i; break;
                    }
                }
            }
            return answer;
        }
    }
}
