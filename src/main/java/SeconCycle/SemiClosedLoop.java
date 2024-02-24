package SeconCycle;

public class SemiClosedLoop {
    public double[] printDepthAndTime(int value, int fullPercent, int pressure) {
        double percent = (double) fullPercent / 100;
        double timeInMinute = pressure * value * 0.7;
        timeInMinute = timeInMinute / (3 / percent);
        System.out.printf("Расчетное время экспозиции на грунте в минутах: %.2f ,в часах: %.2f\n" ,timeInMinute, timeInMinute / 60);
        double depthSport = 1.6 / percent * 10 - 10;
        double depthNATO = 2.4 / percent * 10 - 10;
        double depthRF = 3.2 / percent * 10 - 10;
        System.out.printf("Глубина погружения для спортсменов: %.2f\n", depthSport);
        System.out.printf("Глубина погружения для военных НАТО: %.2f\n" , depthNATO);
        System.out.printf("Глубина погружения для военных РФ: %.2f\n" , depthRF);
        return new double[]{timeInMinute,depthSport};
    }


    public double[]printValuePercentPressure(int depth,int timeInMinute){
        boolean flag = false;
        double coefficient;
        if (depth<17) {
            coefficient = 1.6;
        }else if (depth<=30) {
            coefficient=2;
        } else {
            coefficient=3.2;
        }
        double percent = (coefficient*10)/(depth+10);
        if (percent<0.40){
            return new double[]{-1, -1, -1};
        }
        //System.out.printf("Процент содержания кислорода: %.2f\n" , percent*100);
        double valuePressure = (timeInMinute*3)/(percent*0.7);
        double value=0;
        double pressure=0;
        System.out.println("Для полу-замкнутого цикла");
        for (int i=5;i<=10;i++){
            value=i;
            pressure=valuePressure/value;
            if(pressure>170&&pressure<330) {
                System.out.printf("Для баллона в %.2f литров давление составит в %.2f паскаль\n", value, pressure);
                flag = true;
            }
        }
        if (!flag){
            pressure=-1;
            percent = -1;
        }
        return new double[]{ percent, pressure };
    }

}