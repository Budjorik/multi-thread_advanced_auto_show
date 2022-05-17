public class CustomerOfAutoshow extends Thread {
    private Autoshow autoshow;

    public CustomerOfAutoshow(Autoshow autoshow) {
        super();
        this.autoshow = autoshow;
    }

    @Override
    public void run() {
        autoshow.sellCar();
    }

}
