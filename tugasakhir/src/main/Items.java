package main;

public interface Items {
	String getName();
	double getPrice();
	String getColor();
	void setPrice(double price);
}

interface hasCategory {
	String category();
}