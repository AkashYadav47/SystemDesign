package ProducerConsumer;

public class Producer extends Thread {
    ProductList productList;
    int id;

    public Producer (ProductList productList) {
        this.productList = productList;
        id = 1;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
            Product p = new Product(id);
            id++;
            productList.insertProduct(p);
        }
    }
}
