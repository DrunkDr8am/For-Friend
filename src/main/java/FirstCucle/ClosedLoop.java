package FirstCucle;

import static Difficulty.DifficultyConstant.*;

public class ClosedLoop {

    public void printTime(int balloonVolume, int pressure, int difficulty){
        double timeInMinute = pressure*balloonVolume*0.7;
        if (difficulty==EASY){
            int a = (int) timeInMinute/30;
            a=a*8;
            timeInMinute-=a;
            System.out.println("Минуты: "+timeInMinute+" Часы: "+ timeInMinute/60);
        }
        if (difficulty==NORMAL){
            timeInMinute=timeInMinute/1.5;
            int a = (int) timeInMinute/20;
            a=a*8;
            timeInMinute-=a;
            System.out.println("Минуты: "+timeInMinute+" Часы: "+ timeInMinute/60);
        }
        if (difficulty==HARD){
            timeInMinute=timeInMinute/2;
            int a = (int) timeInMinute/10;
            a=a*8;
            timeInMinute-=a;
            System.out.println("Минуты: "+timeInMinute+" Часы: "+ timeInMinute/60);
        }
    }

}