package main;
import java.util.*;

public class Store<T extends Items & hasCategory> {
    protected ArrayList<T> items = new ArrayList<>() {
	};
    
    public void addItems(T item) {
        items.add(item);
    }

    public List<T> getItems() {
        return items;
    }
    
    public void removeItem(T item) {
        items.remove(item);
    }
    
    protected List<T> filterByCategory(String key) {
        List<T> out = new ArrayList<>();
        for (T x : items) {
            if (x.category().equals(key)) out.add(x);
        }
        return out;
    }
    
    protected List<T> filterByColor(String key) {
        List<T> out = new ArrayList<>();
        for (T x : items) {
            if (x.getColor().equals(key.toLowerCase())) out.add(x);
        }
        return out;
    }
    
    protected List<T> sortByName() { 
    	List<T> out = new ArrayList<>(items);
        out.sort((a, b) -> a.getName().compareTo(b.getName()));
        return out;
    }
    
    protected List<T> sortByPriceAsc() { 
    	List<T> out = new ArrayList<>(items);
        out.sort((a, b) -> Double.compare(a.getPrice(), b.getPrice()));
        return out;
    }
    
    protected List<T> sortByPriceDesc() {
    	List<T> out = new ArrayList<>(items);
        out.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
        return out;
    }
}
