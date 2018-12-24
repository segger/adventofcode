package se.johannalynn.adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day1");

        String inFileName = "2018/input/day1.txt";
        Scanner in = new Scanner(new File(inFileName));

        //star1(in);
        star2(in);
    }

    private static void star1(Scanner in) {
        int start = 0;
        while(in.hasNextLine()) {
            int frequency = Integer.parseInt(in.nextLine());
            start += frequency;
        }
        System.out.println(start);
    }

    private static void star2(Scanner in) {
        List<Integer> shifts = new ArrayList<>();
        while(in.hasNextLine()) {
            int frequency = Integer.parseInt(in.nextLine());
            shifts.add(frequency);
        }

        int current = 0;
        Set<Integer> frequencies = new HashSet<>();
        frequencies.add(current);
        int size = frequencies.size();

        boolean found = false;
        int i = 0;
        while(!found) {
            int frequency = shifts.get(i);
            current += frequency;
            frequencies.add(current);

            int newSize = frequencies.size();
            if (size == newSize) {
                System.out.println(current);
                found = true;
            }
            size = newSize;
            i++;
            if (i >= shifts.size()) {
                i = 0;
            }
        }
    }
}
