package estu.ceng;

import java.util.Random;

public class GaltonBoard implements Runnable {

    private int[] balls;
    private int numBins;

    public GaltonBoard(int[] balls, int numBins) {
        this.numBins = numBins;
        this.balls = balls;
    }

    @Override
    public void run() {
        Random random= new Random();
        numBins = 0;
        synchronized (balls){
            for (int i = 0; i< balls.length-1; i++){
                if(random.nextInt(2)==1){
                    numBins++;
                }
            }
            balls[numBins]++;
        }
    }

}
