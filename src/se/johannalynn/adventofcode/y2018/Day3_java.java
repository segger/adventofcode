package se.johannalynn.adventofcode.y2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day3_java {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day3_java");

        // #1 @ 1,3: 4x4
        String inFileName = "input/2018/day3.txt";
        Scanner scanner = new Scanner(new File(inFileName));

        //star1(scanner);
        star2(scanner);
    }

    static class Elf {
        int id;
        int left;
        int top;
        int width;
        int height;

        Elf(int id, int left, int top, int width, int height) {
            this.id = id;
            this.left = left;
            this.top = top;
            this.width = width;
            this.height = height;
        }
    }

    private static List<Elf> getElves(Scanner scanner) {
        List<Elf> elves = new ArrayList<>();
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

            Elf newElf = new Elf(id, left, top, width, height);
            elves.add(newElf);

        }
        return elves;
    }

    private static void star1(Scanner scanner) {
        int[][] fabric = new int[1000][1000];

        int count = 0;
        List<Elf> elves = getElves(scanner);
        for(Elf elf : elves) {
            for(int i = elf.left; i < elf.left + elf.width; i++) {
                for(int j = elf.top; j < elf.top + elf.height; j++) {
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

    private static void star2(Scanner scanner) {
        int[][] fabric = new int[1000][1000];
        List<Elf> elves = getElves(scanner);

        for(Elf elf : elves) {
            for(int i = elf.left; i < elf.left + elf.width; i++) {
                for(int j = elf.top; j < elf.top + elf.height; j++) {
                    fabric[i][j]++;
                }
            }
        }
        for(Elf elf : elves) {
            boolean claimed = false;
            for(int i = elf.left; i < elf.left + elf.width; i++) {
                for(int j = elf.top; j < elf.top + elf.height; j++) {
                    int square = fabric[i][j];
                    if(square != 1) {
                        claimed = true;
                    }
                }
            }
            if(!claimed) {
                System.out.println(elf.id);
            }
        }
    }
}
