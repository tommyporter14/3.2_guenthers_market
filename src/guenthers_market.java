import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class guenthers_market {
	
	private static Scanner scnr;
	private static Map<String, Double> items = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	private static List<String> orderNames = new ArrayList<>();
	private static List<Double> orderPrices = new ArrayList<>();
	
	public static void main(String[] args) {
		
		scnr =new Scanner(System.in);
		fillItemsMap();
		System.out.println("Welcome to Guenther's Market. Here is the menu:\n");
		printMenu();
		addOrder();
		
		do
		{
			System.out.print("\nWould you like to order anything else (y/n)? ");
			String yOrN = scnr.nextLine();
			try
			{
				if(yOrN.equalsIgnoreCase("y"))
				{
					addOrder();
					continue;
				}
				else if (!yOrN.equalsIgnoreCase("y") && !yOrN.equalsIgnoreCase("n"))
			    {
					throw new InputMismatchException();
				}
				else
				{
					break;
				}
			}
			catch (InputMismatchException e)
			{
				System.out.print("\nInvalid input. Please enter 'y' or 'n': ");
				continue;
			}
		}while (true);

		String[] names = new String[orderNames.size()];
		for(int i = 0; i < orderNames.size(); i++) 
			{
				names[i] = orderNames.get(i);
			}
		double[] prices = new double[orderPrices.size()];
		for(int i = 0; i < orderPrices.size(); i++) 
			{
				prices[i] = orderPrices.get(i);
			}
		
		System.out.println("\nThanks for your order!");
		System.out.println("Here is what you got:\n ");
		printOrder(names, prices);
		
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		double average = averagePrice(prices);
		System.out.println("\nAverage price per item in your order was " +currencyFormatter.format(average));
		double highest = highestIndex(prices);
		System.out.println("The most expensive item was " +currencyFormatter.format(highest));
		double lowest = lowestIndex(prices);
		System.out.println("The cheapest item was " +currencyFormatter.format(lowest));
		double total = 0.00;
        for (int i = 0; i < orderPrices.size(); i++)
        {
            total += orderPrices.get(i);
        }
        System.out.println("Your total is: " + currencyFormatter.format(total));
		
        scnr.close();
	}

	private static void fillItemsMap() 
	{
		items.put("Apple", 0.99);
		items.put("Banana", 0.59);
		items.put("Cauliflower", 1.59);
		items.put("Dragonfruit", 2.19);
		items.put("Elderberry", 1.79);
		items.put("Figs", 2.09);
		items.put("Grapefruit", 1.99);
		items.put("Honeydew", 3.49);
	}
	
	private static void printMenu()
	{
		System.out.printf("%-15s %-10s%n","Item", "Price");
		System.out.println("==============================");
		for (Map.Entry<String,Double> entry : items.entrySet())
		{
			System.out.printf("%-15s %-1s %.2f%n", entry.getKey(),"$", entry.getValue());
		}
	}
	
	private static void addOrder()
	{
		String itemName;
		do {
			System.out.print("\nWhat item would you like to order? ");
			itemName = scnr.nextLine();
			if (!items.containsKey(itemName))
			{
				System.out.println("\nSorry, we don't have those.  Please try again.\n");
				printMenu();
				continue;
			}
			else
			{
				break;
			}
		}while(true);
		Double itemPrice = items.get(itemName);
		System.out.println("Adding " + itemName + " to cart at $" + itemPrice);
		orderNames.add(itemName);
		orderPrices.add(itemPrice);
	}
	
	private static Double averagePrice(double[] p)
	{
		Double average = 0.00;
		double sum = 0;
		for(int i = 0; i < p.length; i++ )
		{
	        sum = sum + p[i];
		}
		average = sum/p.length;
		return average;
	}
	
	private static double highestIndex(double[] p)
	{
		int size = p.length;
		Arrays.sort(p);
		double highest = p[size-1];
		return highest;
	}
	
	private static double lowestIndex(double[] p)
	{
		Arrays.sort(p);
		double lowest = p[0];
		return lowest;
	}
	
	private static void printOrder(String[] n, double[] p)
	{
		for (int i = 0; i < n.length;i++) 
		{
			System.out.printf("%-15s %-1s %-10s%n", String.valueOf(n[i]), "$", String.valueOf(p[i]));
		}
	}
}