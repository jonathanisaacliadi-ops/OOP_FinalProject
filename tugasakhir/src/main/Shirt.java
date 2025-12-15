package main;

public abstract class Shirt implements Items, hasCategory {
    protected String name;
    protected double price;
    protected String color;
   
    
    public Shirt(String name, double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
    }
    
    
    @Override
	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() { 
    	return name;
    }
    
    public double getPrice() { 
    	return price;
    }
    
    public String getColor() { 
    	return color;
    }

    public abstract String category();
    
}

class LongSleeve extends Shirt {
    public LongSleeve(String name, double price, String color) {
        super(name, price, color);
    }
    
    @Override
    public String category() {
    	return "long"; 
    }
}

class ShortSleeve extends Shirt {
    public ShortSleeve(String name, double price, String color) {
        super(name, price, color);
    }
    
    @Override
    public String category() { 
    	return "short";
    }
}
