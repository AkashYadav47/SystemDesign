package ProducerConsumer;

public class Main {
    public static void main(String [] args) {
        int capacity=10;

        ProductList productList = new ProductList(capacity);
        Producer producer = new Producer(productList);
        Consumer consumer1 = new Consumer(productList,1);
        Consumer consumer2 = new Consumer(productList,2);

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
