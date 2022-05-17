import java.util.ArrayList;
import java.util.List;

public class Producer {
    private Autoshow autoshow;
    private final int LOWER_THRESHOLD_CAR = 1;
    private final int UPPER_THRESHOLD_CAR = 3;

    public Producer(Autoshow autoshow) {
        this.autoshow = autoshow;
    }

    public void produceCar() {
        this.autoshow.setCarInStock(Car.getCar(setThreshold(LOWER_THRESHOLD_CAR, UPPER_THRESHOLD_CAR)));
        System.out.println(Thread.currentThread().getName() + ": я произвел машину '"
                + autoshow.getCarInStock().getTitle()
                + "' и поставил ее в автосалон!");
    }

    private int setThreshold(int lowerThreshold, int upperThreshold) {
        upperThreshold -= lowerThreshold;
        int count = (int) (Math.random() * ++upperThreshold) + lowerThreshold;
        return count;
    }

}

