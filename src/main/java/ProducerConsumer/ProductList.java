package ProducerConsumer;

import java.util.ArrayDeque;
import java.util.Queue;

public class ProductList {
    int capacity;
    Queue<Product> list;
    //boolean lock;

    public ProductList(int capacity) {
        this.capacity = capacity;
        list = new ArrayDeque<>();
    }

    synchronized public void insertProduct (Product p) {
        while(capacity <= list.size()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        list.add(p);
        System.out.println("Produced :" +p.getProductID());
        notify();
    }

    synchronized public Product consumeProduct (int id) {
        while(list.size() ==0 ) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        Product p = list.poll();
        System.out.println("Consumer " + id + " :"  +p.getProductID());
        notify();
        return p;
    }

    public int getCapacity() {
        return capacity;
    }

    public Queue<Product> getList() {
        return list;
    }
}
