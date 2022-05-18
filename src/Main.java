import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numberOfDeals = 10;
        Autoshow autoshow = new Autoshow(numberOfDeals, numberOfDeals);

        for (int i = 0 ; i < numberOfDeals ; i++) {
            startSales(autoshow);
            startProduce(autoshow);
        }

    }

    public static void startProduce(Autoshow autoshow) {
        String nameOfProducer = "Производитель";
        new Thread(null, new ProducerOfAutoshow(autoshow), nameOfProducer).start();
    }

    public static void startSales(Autoshow autoshow) {
        for (int i = 1 ; i <= autoshow.getCustomers().size() ; i++) {
            String nameOfCustomer = "Покупатель " + i;
            new Thread(null, new CustomerOfAutoshow(autoshow), nameOfCustomer).start();
        }
    }

}
