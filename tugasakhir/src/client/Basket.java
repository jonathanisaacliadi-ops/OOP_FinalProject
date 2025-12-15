package client;
import java.util.*;
import main.Items;

public class Basket {
    private List<Items> items = new ArrayList<>();

    public void addItem(Items item) {
        items.add(item);
    }

    public List<Items> getItems() {
        return items;
    }
}