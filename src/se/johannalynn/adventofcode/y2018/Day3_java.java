package se.johannalynn.adventofcode.y2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3_java {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day3_java");

        // #1 @ 1,3: 4x4
        String inFileName = "2018/input/day3_pre1.txt";
        Scanner scanner = new Scanner(new File(inFileName));

        star1(scanner);
    }

    private static void star1(Scanner scanner) {
        int[][] fabric = new int[1000][1000];

        int count = 0;
        while(scanner.hasNextLine()) {
            String[] elf = scanner.nextLine().split(" ");
            int id = Integer.valueOf(elf[0].substring(1));
            String pos = elf[2].substring(0, elf[2].length() - 1);
            String[] position = pos.split(",");
            int left = Integer.valueOf(position[0]);
            int top = Integer.valueOf(position[1]);
            String[] dimension = elf[3].split("x");
            int width = Integer.valueOf(dimension[0]);
            int height = Integer.valueOf(dimension[1]);

            for(int i = left; i < left + width; i++) {
                for(int j = top; j < top + height; j++) {
                    int square = fabric[i][j];
                    if(square == 0) {
                        fabric[i][j]++;
                    } else if(square == 1) {
                        count++;
                        fabric[i][j]++;
                    }
                }
            }
        }

        System.out.println(count);

    }
}
