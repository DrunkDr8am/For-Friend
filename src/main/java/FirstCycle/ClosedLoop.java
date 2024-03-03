package FirstCycle;

import static Difficulty.DifficultyConstant.*;

public class ClosedLoop {

    public void printTime(int balloonVolume, int pressure, int difficulty, double zapas) {
        double timeInMinute = pressure * balloonVolume * zapas;
        if (difficulty == EASY) {
            int a = (int) timeInMinute / 30;
            a = a * 8;
            timeInMinute -= a;
            System.out.printf("Расчетное время экспозиции на грунте в минутах: %.2f\n", timeInMinute);
            System.out.printf("В часах: %.2f ", timeInMinute / 60);
        }
        if (difficulty == NORMAL) {
            timeInMinute = timeInMinute / 1.5;
            int a = (int) timeInMinute / 20;
            a = a * 8;
            timeInMinute -= a;
            System.out.printf("Расчетное время экспозиции на грунте в минутах: %.2f\n", timeInMinute);
            System.out.printf("В часах: %.2f ", timeInMinute / 60);
        }
        if (difficulty == HARD) {
            timeInMinute = timeInMinute / 2;
            int a = (int) timeInMinute / 10;
            a = a * 8;
            timeInMinute -= a;
            System.out.printf("Расчетное время экспозиции на грунте в минутах: %.2f\n", timeInMinute);
            System.out.printf("В часах: %.2f ", timeInMinute / 60);
        }
    }

    public void printVolumePlusPressure(int time, int difficulty, double zapas) {
        double doubleTime = 0;
        boolean flag;
        boolean hasBalon = false;
        if (difficulty == EASY) {
            int a = time / 20;
            a = a * 8;
            time += a;
            doubleTime = time * 1.5;
        }
        if (difficulty == NORMAL) {
            int a = time / 20;
            a = a * 8;
            time += a;
            doubleTime = time * 2;
        }
        //doubleTime = pressure*balloonVolume*zapas;
        //blon(1/2)
        //pressure(200-300)

        for (int balloonVolume = 0; balloonVolume < 2; balloonVolume++) {
            flag = false;
            for (int pressure = 200; pressure < 300; pressure++) {
                if (doubleTime <= pressure * balloonVolume * zapas && !flag) {
                    flag = true;
                    hasBalon = true;
                    System.out.println("Нужен баллон объемом " + balloonVolume + " литров и с давлением не меньше " + pressure + " паскаль.");
                }
            }
        }
        if (!hasBalon)
            System.out.println("Нет подходящего снаряжения.");
    }
}