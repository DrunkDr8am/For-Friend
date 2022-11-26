package FirstCucle;

public class ClosedLoop {
    private double balloonVolume;
    private double initialPressure;
    private int difficulty;

    public ClosedLoop(double balloonVolume, double initialPressure, int difficulty) {
        this.balloonVolume=balloonVolume;
        this.initialPressure=initialPressure;
        this.difficulty=difficulty;
    }

    public Double getBalloonVolume() {
        return balloonVolume;
    }

    public Double getInitialPressure() {
        return initialPressure;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setBalloonVolume(Double balloonVolume) {
        this.balloonVolume = balloonVolume;
    }

    public void setInitialPressure(Double initialPressure) {
        this.initialPressure = initialPressure;
    }












}
