public class Customer {
    private Autoshow autoshow;
    private final int LOWER_THRESHOLD_BALANCE = 500;
    private final int UPPER_THRESHOLD_BALANCE = 5_000;
    private int balance = setThreshold(LOWER_THRESHOLD_BALANCE, UPPER_THRESHOLD_BALANCE);
    private final int LOWER_THRESHOLD_CAR = 1;
    private final int UPPER_THRESHOLD_CAR = 3;
    private Car waitingCar = Car.getCar(setThreshold(LOWER_THRESHOLD_CAR, UPPER_THRESHOLD_CAR));

    public Customer(Autoshow autoshow) {
        this.autoshow = autoshow;
    }

    public Car getWaitingCar() {
        return this.waitingCar;
    }

    public int getBalance() {
        return this.balance;
    }

    private int setThreshold(int lowerThreshold, int upperThreshold) {
        upperThreshold -= lowerThreshold;
        int count = (int) (Math.random() * ++upperThreshold) + lowerThreshold;
        return count;
    }

    public boolean waitingCarAvailabilityCheck() {
        if (autoshow.getCarInStock().equals(waitingCar)) {
            System.out.println(Thread.currentThread().getName() + ": ура, в автосалоне есть машина '"
                    + autoshow.getCarInStock().getTitle()
                    + "', которую я хочу купить!");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + ": увы, в автосалоне есть машина '"
                    + autoshow.getCarInStock().getTitle()
                    + "', а я хочу купить '"
                    + waitingCar.getTitle() + "'!");
            return false;
        }
    }

    public boolean moneyAvailabilityCheck() {
        if (autoshow.getCarInStock().getPrice() <= balance) {
            System.out.println(Thread.currentThread().getName() + ": ура, машина стоит "
                    + autoshow.getCarInStock().getPrice() + " руб."
                    + ", а у меня как раз есть "
                    + balance + " руб.!");
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() + ": увы, машина стоит "
                    + autoshow.getCarInStock().getPrice() + " руб."
                    + ", а у меня есть только "
                    + balance + " руб.!");
            return false;
        }
    }

    public void buyCar() {
        balance -= autoshow.getCarInStock().getPrice();
        autoshow.setCarInStock(null);
        System.out.println(Thread.currentThread().getName() + ": ура, я купил машину!");
    }

}
