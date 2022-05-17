import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Autoshow {
    private final int SLEEP_TO_PRODUCE = 1_000;
    private final int SLEEP_TO_SALE = 1_000;
    private int maxNumberOfSales;
    private int maxNumberOfProduces;
    private final int NUMBER_OF_CUSTOMERS = 50;
    private Producer producer = new Producer(this);
    private List<Customer> customers = setCustomers(NUMBER_OF_CUSTOMERS);
    private int currentNumberOfCustomer = 0;
    private Car carInStock;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Autoshow(int maxNumberOfSales, int maxNumberOfProduces) {
        this.maxNumberOfSales = maxNumberOfSales;
        this.maxNumberOfProduces = maxNumberOfProduces;
    }

    public void setCarInStock(Car newCar) {
        this.carInStock = newCar;
    }

    public Car getCarInStock() {
        return this.carInStock;
    }

    public int getMaxNumberOfSales() {
        return this.maxNumberOfSales;
    }

    public int getMaxNumberOfProduces() {
        return this.maxNumberOfProduces;
    }

    public int getSLEEP_TO_SALE() {
        return this.SLEEP_TO_SALE;
    }

    public int getSLEEP_TO_PRODUCE() {
        return this.SLEEP_TO_PRODUCE;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

    public int getCurrentNumberOfCustomer() {
        return this.currentNumberOfCustomer;
    }

    public void addCurrentNumberOfCustomer() {
        this.currentNumberOfCustomer++;
    }

    public void minusMaxNumberOfSales() {
        this.maxNumberOfSales--;
    }

    private List<Customer> setCustomers(int size) {
        List<Customer> newCustomers = new ArrayList<>();
        for (int i = 0 ; i < size ; i++) {
            Customer customer = new Customer(this);
            newCustomers.add(customer);
        }
        return newCustomers;
    }

    private int setThreshold(int lowerThreshold, int upperThreshold) {
        upperThreshold -= lowerThreshold;
        int count = (int) (Math.random() * ++upperThreshold) + lowerThreshold;
        return count;
    }

    public void receiveCar() {
        try {
            lock.lock();
            while(carInStock != null) {
                condition.await();
            }
            Thread.sleep(SLEEP_TO_PRODUCE);
            producer.produceCar();
            maxNumberOfProduces --;
            System.out.println("Автосалон: осталось произвести и поставить еще = "
                    + maxNumberOfProduces + " а/м.");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": хочу купить а/м.");
            while (carInStock == null) {
                System.out.println(Thread.currentThread().getName() + ": не могу купить, а/м нет.");
                condition.await();
            }
            Thread.sleep(SLEEP_TO_SALE);
            if (customers.get(currentNumberOfCustomer).waitingCarAvailabilityCheck()) {
                if (customers.get(currentNumberOfCustomer).moneyAvailabilityCheck()) {
                    customers.get(currentNumberOfCustomer).buyCar();
                    maxNumberOfSales--;
                    System.out.println("Автосалон: в продаже появится еще = "
                            + maxNumberOfSales + " а/м.");
                }
            }
            currentNumberOfCustomer++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

