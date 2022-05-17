public class ProducerOfAutoshow extends Thread {
    private Autoshow autoshow;

    public ProducerOfAutoshow(Autoshow autoshow) {
        super();
        this.autoshow = autoshow;
    }

    @Override
    public void run() {
        autoshow.receiveCar();
    }

}
