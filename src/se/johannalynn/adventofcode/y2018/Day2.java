package se.johannalynn.adventofcode.y2018;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Day2");

        String inFileName = "2018/input/day2.txt";
        Scanner in = new Scanner(new File(inFileName));

        star1(in);
        //star2(in);
    }

    private static boolean[] count(String id) {
        boolean[] retValue = new boolean[2];
        Map<Character, Integer> counter = new HashMap<>();
        for(char letter : id.toCharArray()) {
            Integer current = counter.get(letter);
            if (current != null) {
                counter.put(letter, ++current);
            } else {
                counter.put(letter, 1);
            }
        }
        Iterator<Character> itr = counter.keySet().iterator();
        while(itr.hasNext()) {
            Character letter = itr.next();
            int nbr = counter.get(letter);
            if (nbr == 2) {
                retValue[0] = true;
            }
            if (nbr == 3) {
                retValue[1] = true;
            }
        }
        return retValue;
    }

    private static void star1(Scanner in) {
        int twos = 0;
        int threes = 0;
        while(in.hasNextLine()) {
            String id = in.nextLine();
            boolean[] counts = count(id);
            twos += counts[0] ? 1 : 0;
            threes += counts[1] ? 1 : 0;
        }

        System.out.println(twos * threes);
    }
}
