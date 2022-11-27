package SeconCucle;

public class SemiClosedLoop {
    public void printGlybinaAndTime(int value, int fullPersent, int preasure){
        double persent=(double)fullPersent/100;
        double timeInMinute = preasure*value*0.7;
        timeInMinute=timeInMinute/(3+(3*persent));
        System.out.println("Минуты: "+timeInMinute+" Часы: "+ timeInMinute/60);
        double depth=1.6/(0.2*persent);
        System.out.println("Глубина погружения: "+depth);
    }
}