package SeconCucle;

public class SemiClosedLoop {
    public void printDepthAndTime(int value, int fullPercent, int pressure){
        double percent=(double)fullPercent/100;
        double timeInMinute = pressure*value*0.7;
        timeInMinute=timeInMinute/(3+(3*percent));
        System.out.println("Минуты: "+timeInMinute+" Часы: "+ timeInMinute/60);
        double depth=1.6/(0.2*percent);
        System.out.println("Глубина погружения: "+depth);
    }
}