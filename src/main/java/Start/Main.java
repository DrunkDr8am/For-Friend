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
            System.out.println("3 - обратная функция для полузамкнутого цикла");
            System.out.println("4 - выход");
            int type = checkCorrectNumber(1,4);
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("введите объем балона(1 или 2 литра)");//1 или 2 литра
                    int balloonVolume = checkCorrectNumber(1,2);
                    System.out.println("укажите давление в балоне(от 200 до 300 паскалей)");//200;250;300
                    int initialPressure = checkCorrectNumber(200,300);
                    ClosedLoop closedLoop = new ClosedLoop();
                    closedLoop.printTime(balloonVolume, initialPressure, difficulty);
                    System.out.println("тут могла быть ваша реклама");
                }
                case 2 -> {
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("выберите объем баолна (от 5 до 10 литров)");//от 5 до 10 литров
                    int value = checkCorrectNumber(5,10);
                    System.out.println("укажите содержание кислорода(от 40 до 60 процентов)");//от 40% до 60%
                    int percent = checkCorrectNumber(40,60);
                    System.out.println("укажите давление в балоне (от 200 до 300 паскалей)");//200;250;300
                    int pressure = checkCorrectNumber(200,300);
                    semiClosedLoop.printDepthAndTime(value, percent, pressure);
                    System.out.println("тут могла быть ваша реклама");
                }
                case 3 ->{
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("укажите длительность погружения в минутах (от 30 до 420) ");
                    int time = checkCorrectNumber(30,420);
                    System.out.println("укажите глубину погружения в метрах (от 2 до 50)");
                    int depth = checkCorrectNumber(2,50);
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
        return checkCorrectNumber(1,3);
    }

    public static int checkCorrectNumber(int min, int max) {
        while (true) {
            String volume = in.nextLine();
            if (checkInt(volume)) {
                int number = Integer.parseInt(volume);
                if (number >= min && number <= max)
                    return number;
            }
            System.out.println("Рыбка, ты совсем дурак?");
            System.out.println("Введите число в деапазоне от "+min+" до "+max);
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