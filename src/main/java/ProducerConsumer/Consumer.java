package ProducerConsumer;

import java.util.List;

class Consumer extends Thread {
    int consumerId;
    ProductList productList;

    public Consumer (ProductList productList, int consumerId) {
        this.consumerId = consumerId;
        this.productList = productList;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            Product p = productList.consumeProduct(this.consumerId);
        }
    }

}
