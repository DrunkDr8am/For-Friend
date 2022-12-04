package SeconCucle;

public class SemiClosedLoop {
    public void printDepthAndTime(int value, int fullPercent, int pressure) {
        double percent = (double) fullPercent / 100;
        double timeInMinute = pressure * value * 0.7;
        timeInMinute = timeInMinute / (3 / percent);
        System.out.println("Минуты: " + timeInMinute + " Часы: " + timeInMinute / 60);
        double depthSport = 1.6 / percent * 10 - 10;
        double depthNATO = 2.4 / percent * 10 - 10;
        double depthRF = 3.2 / percent * 10 - 10;
        System.out.println("Глубина погружения для рыбок: " + depthSport);
        System.out.println("Глубина погружения для морских котиков: " + depthNATO);
        System.out.println("Глубина погружения для моржей: " + depthRF);

    }

    public void printValuePercentPressure(double depth,double timeInMinute){
        double percent = (1.6*10)/(depth+10);
        System.out.println("Процент содержания кислорода: " + percent*100);
        double valuePressure = (timeInMinute*3)/(percent*0.7);
        double value;
        double pressure;
        for (int i=5;i<10;i++){
            value=i;
            pressure=valuePressure/value;
            if(pressure>190)
            System.out.println("Для балона в: "+value+" литров будет давление в: "+pressure+" паскаль");
        }

    }
}