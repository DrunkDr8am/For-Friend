package Start;

import FirstCucle.ClosedLoop;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean exit=true;
        do {
            System.out.println("Выберите тип поогружения");
            System.out.println("1 - замкнутый цикл");
            System.out.println("2 - полузамкнутый цикл");
            System.out.println("3 - выход");
            int type = in.nextInt();
            switch (type) {
                case 1 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("введите объем балона");
                    double balloonVolume = in.nextDouble();
                    System.out.println("введите начальное давление");
                    double initialPressure = in.nextDouble();
                    ClosedLoop closedLoop = new ClosedLoop(balloonVolume, initialPressure, difficulty);
                    System.out.println("тут могла быть ваша реклама");
                }
                case 2 -> {
                    int difficulty = selectDifficulty();
                    System.out.println("Выберите тип поогружения");
                    System.out.println("тут могла быть ваша реклама");
                }
                default -> {exit=false;}
            }
        }while (exit);
    }
    public static int selectDifficulty(){
        Scanner in = new Scanner(System.in);
        System.out.println("Выберите сложность поогружения");
        System.out.println("1 - легко");
        System.out.println("2 - нормально");
        System.out.println("3 - сложно");
        return in.nextInt();
    }
}
