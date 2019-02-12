package se.johannalynn.adventofcode.y2018;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Day9_java {

    public static void main(String[] args) {
        long res = game(419, 72164*100);
        System.out.println("res=" + res);
    }


    static class CircleDeque<T> extends ArrayDeque<T> {
        void rotate(int num) {
            if (num == 0) return;
            if (num > 0) {
                for (int i = 0; i < num; i++) {
                    T t = this.removeLast();
                    this.addFirst(t);
                }
            } else {
                for (int i = 0; i < Math.abs(num) - 1; i++) {
                    T t = this.remove();
                    this.addLast(t);
                }
            }

        }
    }

    static long game(int players, int end) {
        CircleDeque<Integer> circle = new CircleDeque<>();
        circle.addFirst(0);
        long[] scores = new long[players];
        for (int i = 1; i <= end; i++) {
            if (i % 23 == 0) {
                circle.rotate(-7);
                scores[i % players] += i + circle.pop();

            } else {
                circle.rotate(2);
                circle.addLast(i);
            }
        }
        return Arrays.stream(scores).max().getAsLong();
    }
}
