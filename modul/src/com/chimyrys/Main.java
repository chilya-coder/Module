package com.chimyrys;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "text.txt";
        int[] ints = readIntegersFromFile(filename);
        int enter = getValueFromMenu();
        processEnter(enter, ints);
    }
    public static int countNumberOfIntegers(String fileName) {
        int count = -1;
        try (FileInputStream fis = new FileInputStream(fileName);
             DataInputStream dis = new DataInputStream(fis)) {
            String x;
            do {
                x = dis.readLine();
                count += 1;
            } while (x != null);
        } catch (IOException ignored) {

        }
        return count;
    }

    public static int[] readIntegersFromFile(String filename) {
        int numberOfIntegers = countNumberOfIntegers(filename);
        int[] ints = new int[numberOfIntegers];
        try (FileInputStream fis = new FileInputStream(filename);
             DataInputStream dis = new DataInputStream(fis)) {
            for (int i = 0; i < numberOfIntegers; i++) {
                ints[i] = Integer.valueOf(dis.readLine());
            }
        } catch (IOException ignored) {

        }
        return ints;
    }

    public static int getValueFromMenu() {
        System.out.println("1. Only unique");
        System.out.println("2. Only paired");
        System.out.println("3. Only couples key - element");
        System.out.println("4. Quit");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static void processEnter(int enter, int[] ints) {
        LocalDateTime startTime = LocalDateTime.now();
        switch (enter) {
            case 1:
                Set<Integer> set = new HashSet<>();
                Arrays.stream(ints).forEach(set::add);
                System.out.println(set);
                break;
            case 2:
                List<Integer> list = new LinkedList<>();
                Arrays.stream(ints).filter(integer -> integer % 2 == 0).forEach(list::add);
                System.out.println(list);
                break;
            case 3:
                Map<String, Integer> map = new TreeMap<>();
                for (int i = 0; i < ints.length; i++) {
                    map.put((i + 1) + "'s element", ints[i]);
                }
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
                break;
            case 4:
                System.out.println("Quit");
                LocalDateTime quitTime = LocalDateTime.now();
                long seconds = ChronoUnit.SECONDS.between(startTime, quitTime);
                System.out.println("Session time " + seconds);
                break;
        }
    }
}
