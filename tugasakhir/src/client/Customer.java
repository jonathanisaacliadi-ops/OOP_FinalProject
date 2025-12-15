package client;
import main.Items;


public class Customer {
    private String name;
    private Basket basket;

    public Customer(String name) {
        this.name = name;
        this.basket = new Basket();
    }

    public Basket getBasket() {
        return basket;
    }
}
