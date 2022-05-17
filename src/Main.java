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
        String nameOfCustomer = "Производитель";
        Thread producerOfAutoshow = new ProducerOfAutoshow(autoshow);
        producerOfAutoshow.setName(nameOfCustomer);
        producerOfAutoshow.start();
    }

    public static void startSales(Autoshow autoshow) {
        for (int i = 1 ; i <= autoshow.getCustomers().size() ; i++) {
            String nameOfCustomer = "Покупатель " + i;
            Thread customerOfAutoshow = new CustomerOfAutoshow(autoshow);
            customerOfAutoshow.setName(nameOfCustomer);
            customerOfAutoshow.start();
        }
    }

}
