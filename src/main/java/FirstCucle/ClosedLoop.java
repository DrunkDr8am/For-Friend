package FirstCucle;

public class ClosedLoop {
    private Double balloonVolume;
    private Double initialPressure;
    private int difficulty;

    public ClosedLoop(Double balloonVolume, Double initialPressure, int difficulty) {
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
