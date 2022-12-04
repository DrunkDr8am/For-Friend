package Start;

import FirstCucle.ClosedLoop;
import SeconCucle.SemiClosedLoop;

import java.util.Scanner;

public class Main {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = true;
        do {
            System.out.println("Выберите тип поогружения");
            System.out.println("1 - замкнутый цикл");
            System.out.println("2 - полузамкнутый цикл");
            System.out.println("3 - выход");
            int type = check123();
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("введите объем балона(1 или 2 литра)");//1 или 2 литра
                    int balloonVolume = checkVolumeInClosedLoop();
                    System.out.println("укажите давление в балоне(от 200 до 300 паскалей)");//200;250;300
                    int initialPressure = checkPressure();
                    ClosedLoop closedLoop = new ClosedLoop();
                    closedLoop.printTime(balloonVolume, initialPressure, difficulty);
                    System.out.println("тут могла быть ваша реклама");
                }
                case 2 -> {
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("выберите объем баолна");//от 5 до 10 литров
                    int value = checkVolumeInSemiClosedLoop();
                    System.out.println("укажите содержание кислорода");//от 40% до 60%
                    int percent = checkPercent();
                    System.out.println("укажите давление в балоне");//200;250;300
                    int pressure = checkPressure();
                    semiClosedLoop.printDepthAndTime(value, percent, pressure);
                    System.out.println("тут могла быть ваша реклама");
                    double time = 93.333333;
                    double depth = 30;
                    semiClosedLoop.printValuePercentPressure(depth, time);
                }
                default -> {
                    System.out.println("Плыви Рыбка");
                    exit = false;
                }
            }
        } while (exit);
    }

    public static int selectDifficulty() {
        System.out.println("Выберите сложность поогружения");
        System.out.println("1 - легко");
        System.out.println("2 - нормально");
        System.out.println("3 - сложно");
        return check123();
    }

    public static int check123() {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= 1 && number <= 3)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
        }
    }

    public static int checkVolumeInClosedLoop() {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= 1 && number <= 2)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
        }
    }

    public static int checkPercent() {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= 40 && number <= 60)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
        }
    }

    public static int checkVolumeInSemiClosedLoop() {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= 5 && number <= 10)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
        }
    }

    public static int checkPressure() {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= 200 && number <= 300)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
        }
    }

    public static boolean checkInt(String string) {
        boolean flag = true;
        if (string.length() > 0) {
            for (int i = 0; i < string.length(); i++) {
                if (string.charAt(i) >= 48 && string.charAt(i) <= 57) {
                } else flag = false;
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

}