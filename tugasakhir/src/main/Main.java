package main;
import java.util.*;

import client.Customer;
import client.Basket;


public class Main {
	Customer customer1 = new Customer("Guest");
	Store<Shirt> shirtStore = new Store<>();
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("\n\n\nWelcome to Uniqqler");
			System.out.println("1. Browse Items");
			System.out.println("2. View basket");
			System.out.println("3. Logout");
			System.out.println("4. Exit");
			System.out.print(">> ");
			
			int choice = sc.nextInt(); sc.nextLine();
			
			switch (choice) {
				case 1:
					browseItems(shirtStore);
					break;
				case 2:
					viewBasket();
					break;
				case 3:
					login();
					break;
				case 4:
					System.out.println("Exiting...");
					System.exit(0);
				default:
					System.out.println("Invalid option");
					break;
			}
		}
	}
	
	
	public void viewBasket() {
		Basket basket = customer1.getBasket();
		
		if(basket.getItems().isEmpty()) {
			System.out.println("Basket is empty");
		}
		else {
			System.out.println("Items in basket:");
			for (Items item : basket.getItems()) {
				System.out.println("- " + item.getName() + " | $" + item.getPrice());
			}
		}
	}
	

	public <T extends Items & hasCategory> void browseItems(Store<T> store) {
		Scanner sc = new Scanner(System.in);
		List<T> currentList = store.getItems();
		
		while (true) {
			System.out.println("\n\n\nBrowse Items");
			System.out.println("1. Filter by category");
			System.out.println("2. Filter by color");
			System.out.println("3. Sort by name");
			System.out.println("4. Sort by price ascending");
			System.out.println("5. Sort by price descending");
			System.out.println("6. Reset filter");
			System.out.println("7. Show items / Add to basket");
			System.out.println("0. Back to menu");
			System.out.print(">> ");

			int choice = sc.nextInt(); sc.nextLine();

			switch (choice) {
				case 1:
	        		List<String> categories = new ArrayList<>();
        			for (T item : store.getItems()) {
        				if (!categories.contains(item.category())) {
        					categories.add(item.category());
        				}
        			}
        			System.out.println("Available categories: ");
        			for (String cat : categories) {
        				System.out.println("- " + cat);
        			}

        			System.out.print("Enter category: ");
        			String category = sc.nextLine();
        			currentList = store.filterByCategory(category);
        			break;
	        	case 2:
		            List<String> colors = new ArrayList<>();
		            for (T item : store.getItems()) {
		                if (!colors.contains(item.getColor())) {
		                    colors.add(item.getColor());
		                }
		            }
		            System.out.println("Available colors: ");
		            for (String col : colors) {
						System.out.println("- " + col);
					}
	
		            System.out.print("Enter color: ");
		            String color = sc.nextLine();
		            currentList = store.filterByColor(color);
		            break;
	            case 3:
	                currentList = store.sortByName();
	                break;
	            case 4:
	                currentList = store.sortByPriceAsc();
	                break;
	            case 5:
	                currentList = store.sortByPriceDesc();
	                break;
	            case 6:
	            	currentList = store.getItems();
	            	break;
	            case 7:
	            	if (currentList.isEmpty()) {
	            		System.out.println("No Item in Store");
	            		break;
	            	}
	            	
	            	System.out.printf("%-3s | %-30s | %-9s | %-10s |\n", "No",  "Item's Name", "Price", "Category");
	            	for (int i = 0; i < currentList.size(); i++) {
	            		T item = currentList.get(i);
	            		System.out.printf("%-3d | %-30s | $%-9.2f | %-10s |\n", i + 1,  item.getName(), item.getPrice(), item.category());
	            	}
	            	
	            	System.out.print("Choose an Item Number to Add to Basket (0 to cancel): ");
	            	int itemChoice = sc.nextInt(); sc.nextLine();
	            	
	            	if (itemChoice > 0 && itemChoice <= currentList.size()) {
	            		customer1.getBasket().addItem(currentList.get(itemChoice - 1));
	            		System.out.println(currentList.get(itemChoice - 1).getName() + " added to basket!");
	            	}
	            	else {
	            		System.out.println("Input a valid option");
	            	}
	            	break;
	            case 0:
	                return;
	            default:
	                System.out.println("Invalid option");
	                break;
	        }
	    }
	}

	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter credential: ");
		String credential = sc.nextLine();
		
		if(credential.toLowerCase().equals("admin")) {
			adminMenu();
		}
		if(credential.toLowerCase().equals("customer")) {
			menu();
		}
		
	}

	public void adminMenu() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("\n\n\nWelcome to Uniqqler Admin page");
			System.out.println("1. Browse Items");
			System.out.println("2. Add Item to Store");
			System.out.println("3. Update Item's price in Store");
			System.out.println("4. Delete Item from Store");
			System.out.println("5. Logout");
			System.out.println("6. Exit");
	        System.out.print(">> ");
	
	        int choice = sc.nextInt(); sc.nextLine();
	        
	        switch (choice) {
				case 1:
					browseItems(shirtStore);
					break;
				case 2:
					addItemToStore();
					break;
				case 3:
					UpdatePriceInStore(shirtStore);
					break;
				case 4:
					deleteItemFromStore(shirtStore);
					break;
				case 5:
					login();
					break;
				case 6:
					System.out.println("Exiting...");
					System.exit(0);
					break;
	
				default:
					break;
			}
		}
	}


	public void addItemToStore () {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter category (long, short): ");
		String itemCategory = sc.nextLine();
		System.out.print("Enter Item's Name (format: color category shirt): ");
		String itemName = sc.nextLine();
		System.out.print("Enter item's color: ");
		String itemColor = sc.nextLine();
		System.out.print("Enter item's price: ");
		double itemPrice = sc.nextDouble();
		
		if(itemCategory.equals("long")) {
			shirtStore.addItems(new LongSleeve(itemName, itemPrice, itemColor)); 
		}
		else if(itemCategory.equals("short")) {
			shirtStore.addItems(new ShortSleeve(itemName, itemPrice, itemColor));
		}
		
		System.out.println("Item added to Store!");
		System.out.println("Press enter to continue...");
		sc.nextLine();
	}
	
	public <T extends Items & hasCategory> void deleteItemFromStore(Store<T> store) {
		Scanner sc = new Scanner(System.in);
		List<T> currentList = store.getItems();
		
		System.out.printf("%-3s | %-30s | %-9s | %-10s |\n", "No",  "Item's Name", "Price", "Category");
    	for (int i = 0; i < currentList.size(); i++) {
    		T item = currentList.get(i);
    		System.out.printf("%-3d | %-30s | $%-9.2f | %-10s |\n", i + 1,  item.getName(), item.getPrice(), item.category());
    	}
    	
    	System.out.print("Choose an item to remove (index): ");
    	int choice = sc.nextInt(); sc.nextLine();
    	
    	if (choice > 0 && choice <= currentList.size()) {
    		store.removeItem(currentList.get(choice-1));    		
    	}
    	else {
    		System.out.println("Input a valid option");
    		return;
    	}
    	
    	System.out.println("Item is deleted");
    	System.out.println("Press enter to continue...");
    	sc.nextLine();
	}
	
	public <T extends Items & hasCategory> void UpdatePriceInStore(Store<T> store) {
		Scanner sc = new Scanner(System.in);
		List<T> currentList = store.getItems();
		
		System.out.printf("%-3s | %-30s | %-9s | %-10s |\n", "No",  "Item's Name", "Price", "Category");
    	for (int i = 0; i < currentList.size(); i++) {
    		T item = currentList.get(i);
    		System.out.printf("%-3d | %-30s | $%-9.2f | %-10s |\n", i + 1,  item.getName(), item.getPrice(), item.category());
    	}
    	
    	System.out.print("Choose an item to Update Price (index): ");
    	int choice = sc.nextInt(); sc.nextLine();
    	
    	if (choice > 0 && choice <= currentList.size()) {
    		System.out.println("Input item's new price:");
    		double newPrice = sc.nextDouble(); sc.nextLine();
    		
    		currentList.get(choice-1).setPrice(newPrice);
    		
    	}
    	else {
    		System.out.println("Input a valid option");
    		return;
    	}
    	
    	System.out.println("Price is updated");
    	System.out.println("Press enter to continue...");
    	sc.nextLine();    	
	}

	public Main() {
//		Default items
		shirtStore.addItems(new LongSleeve("Blue Long Sleeve Shirt", 25.0, "blue")); 
		shirtStore.addItems(new LongSleeve("Green Long Sleeve Shirt", 30.0, "green"));
		shirtStore.addItems(new LongSleeve("Black Long Sleeve Shirt", 28.0, "black"));
		shirtStore.addItems(new ShortSleeve("Red Short Sleeve Shirt", 20.0, "red"));
		shirtStore.addItems(new ShortSleeve("White Short Sleeve Shirt", 22.0, "white"));
		shirtStore.addItems(new ShortSleeve("Yellow Short Sleeve Shirt", 18.0, "yellow"));
		shirtStore.addItems(new LongSleeve("Gray Long Sleeve Shirt", 27.0, "gray"));
		shirtStore.addItems(new ShortSleeve("Blue Short Sleeve Shirt", 19.0, "blue"));
		
		login();
	}



	public static void main(String[] args) {
		new Main();

	}

}
