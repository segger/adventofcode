package se.johannalynn.adventofcode.y2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10_java {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day10_java");

        // #1 @ 1,3: 4x4
        String inFileName = "2018/input/day10.txt";
        Scanner scanner = new Scanner(new File(inFileName));

        star1(scanner);
    }

    static class PointInTheSky {
        char letter;
        int x;
        int y;
        int v_x;
        int v_y;

        PointInTheSky(char letter, int x, int y, int v_x, int v_y) {
            this.letter = letter;
            this.x = x;
            this.y = y;
            this.v_x = v_x;
            this.v_y = v_y;
        }

        private int[] getPosition(int sec) {
            int[] position = new int[2];
            position[0] = x + sec * v_x;
            position[1] = y + sec * v_y;
            return position;
        }
    }

    private static void star1(Scanner scanner) {
        List<PointInTheSky> pointInTheSkyList = new ArrayList<>();
        int max_x = Integer.MIN_VALUE;
        int min_x = Integer.MAX_VALUE;
        int max_y = Integer.MIN_VALUE;
        int min_y = Integer.MAX_VALUE;
        char letter = 'A';

        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] posVel = input.split("=");

            String tmp = posVel[1].split(">")[0].substring(1).trim();
            String[] pos = tmp.split(",");
            int x = Integer.valueOf(pos[0].trim());
            int y = Integer.valueOf(pos[1].trim());

            String[] tmp2 = posVel[2].split(",");
            int v_x = Integer.valueOf(tmp2[0].substring(1).trim());
            int v_y = Integer.valueOf(tmp2[1].substring(0, tmp2[1].length()-1).trim());

            if(x > max_x) {
                max_x = x;
            }
            if(x < min_x) {
                min_x = x;
            }
            if(y > max_y) {
                max_y = y;
            }
            if(y < min_y) {
                min_y = y;
            }
            pointInTheSkyList.add(new PointInTheSky(letter++, x, y, v_x, v_y));
        }

        //printSky(pointInTheSkyList, diff_x, diff_y, 50);

        int min_diff = Integer.MAX_VALUE;
        int min_sec_min_x = 0;
        int min_sec_min_y = 0;
        int min_sec_max_x = 0;
        int min_sec_max_y = 0;
        int sec = 0;
        int min_sec = 0;
        while(sec < 200000) {
            max_x = Integer.MIN_VALUE;
            min_x = Integer.MAX_VALUE;
            max_y = Integer.MIN_VALUE;
            min_y = Integer.MAX_VALUE;

            for (PointInTheSky p : pointInTheSkyList) {
                int[] point = p.getPosition(sec);

                if(point[0] > max_x) {
                    max_x = point[0];
                }
                if(point[0] < min_x) {
                    min_x = point[0];
                }
                if(point[1] > max_y) {
                    max_y = point[1];
                }
                if(point[1] < min_y) {
                    min_y = point[1];
                }
            }
            /*
            System.out.println("max_x: " + max_x + ", min_x: " + min_x);
            System.out.println("max_y: " + max_y + ", min_y: " + min_y);
            */

            int diff_min_x_tmp = min_x;
            int diff_max_x_tmp = max_x;
            int diff_min_y_tmp = min_y;
            int diff_max_y_tmp = max_y;

            int diff = max_x - min_x + max_y - min_y;

            //System.out.println("sec=" + sec + ", diff: " + diff);

            if(diff < min_diff) {
                min_diff = diff;
                min_sec_min_x = diff_min_x_tmp;
                min_sec_min_y = diff_min_y_tmp;
                min_sec_max_x = diff_max_x_tmp;
                min_sec_max_y = diff_max_y_tmp;
                min_sec = sec;
            }

            sec++;
        }

        System.out.println("min_sec: " + min_sec + ", min_diff: " + min_diff);
        //System.out.println("minX: " + min_sec_min_x + ", maxX: " + min_sec_max_x + ", minY: " + min_sec_min_y + ", maxY: " + min_sec_max_y);
        printSky(pointInTheSkyList, min_sec_min_x,min_sec_min_y,min_sec_max_x, min_sec_max_y, min_sec);
    }

    private static void printSky(List<PointInTheSky> pointInTheSkyList, int min_x, int min_y, int max_x, int max_y, int sec) {
        int x_diff = max_x - min_x;
        int y_diff = max_y - min_y;
        String[][] sky = new String[y_diff+1][x_diff+1];
        for (PointInTheSky point : pointInTheSkyList) {
            int[] pos = point.getPosition(sec);
            int x_pos = pos[0] - Math.abs(min_x);
            int y_pos = pos[1] - Math.abs(min_y);

            //sky[y_pos][x_pos] = String.valueOf(pos.letter);
            sky[y_pos][x_pos] = "#";
        }

        System.out.println("Print sky");
        for (int i = 0; i < sky.length; i++) {
            for (int j = 0; j < sky[i].length; j++) {
                String p = sky[i][j];
                if (p == null) {
                    System.out.print(".");
                } else {
                    System.out.print(p);
                }
            }
            System.out.println();
        }

    }
}
