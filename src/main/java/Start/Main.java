package Start;

import FirstCucle.ClosedLoop;
import SeconCucle.SemiClosedLoop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean exit = true;
        do {
            System.out.println("Выберите тип поогружения");
            System.out.println("1 - замкнутый цикл");
            System.out.println("2 - полузамкнутый цикл");
            System.out.println("3 - выход");
            int type = in.nextInt();
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("введите объем балона");//1 или 2 литра
                    int balloonVolume = 2;
                    System.out.println("укажите давление в балоне");//200;250;300
                    int initialPressure = 200;
                    ClosedLoop closedLoop = new ClosedLoop();
                    closedLoop.printTime(balloonVolume, initialPressure, difficulty);
                    System.out.println("тут могла быть ваша реклама");
                }
                case 2 -> {
                    SemiClosedLoop semiClosedLoop = new SemiClosedLoop();
                    System.out.println("выберите объем баолна");//от 5 до 10 литров
                    int value = in.nextInt();
                    System.out.println("укажите содержание кислорода");//от 40% до 60%
                    int percent = in.nextInt();
                    System.out.println("укажите давление в балоне");//200;250;300
                    int pressure = in.nextInt();
                    semiClosedLoop.printDepthAndTime(value, percent, pressure);
                    System.out.println("тут могла быть ваша реклама");
                }
                default -> {
                    System.out.println("Пока Рыбка");
                    exit = false;
                }
            }
        } while (exit);
    }

    public static int selectDifficulty() {
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите сложность поогружения");
        System.out.println("1 - легко");
        System.out.println("2 - нормально");
        System.out.println("3 - сложно");
        return in.nextInt();
    }
}