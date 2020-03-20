import com.johnli.keypress.*;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.johnli.alerts.*;

public class Main {

	private static Scanner input = new Scanner(System.in);
	
	private static String name;
	private static BigDecimal price;
	private static BigDecimal alertVal;
	private static boolean alertIfBelow;
	
	public static void main(String[] args) throws IOException {
		System.out.println("ENTER STOCK NAME : ");
		Stock stock = YahooFinance.get(input.nextLine());
		
		intro();
		
		initialize(stock);
		getTargetValue();
		
		System.out.println("ENTER ESC TO EXIT");
		boolean isPressed = false;
		do {
			updatePrice(stock);
			if (alertIfBelow) {
				if (price.compareTo(alertVal) < 0) {
					System.out.println(" << SEND PHONE A TEXT MESSAGE SAYING THAT THE PRICE IS BELOW THE ALERT VALUE >> ");
				}
			} else {
				if (price.compareTo(alertVal) > 0) {
					System.out.println(" << SEND PHONE A TEXT MESSAGE SAYING THAT THE PRICE IS ABOVE THE ALERT VALUE >> ");
				}
			}
			isPressed = IsKeyPressed.isWPressed();
		} while (!isPressed);
		
	}
	
	public static void initialize(Stock stock) {
		name = stock.getName();
		price = stock.getQuote().getPrice();
	}
	
	public static void getTargetValue() {
		System.out.println("WHAT VALUE TO SET AN ALERT AT?  ");
		alertVal = input.nextBigDecimal();
		
		System.out.println("ALERT IF ABOVE OR BELOW VALUE? (A/B)");
		if (input.next().equalsIgnoreCase("A")) alertIfBelow = false;
		else alertIfBelow = true;
	}
	
	public static void updatePrice(Stock stock) {
		price = stock.getQuote().getPrice();
	}
	
	public static void intro() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
		System.out.println("                 BEGINNING TO START THE APPLICATION:        ");
		System.out.println("                         STOCK TRACKER                      ");
		System.out.println();
		System.out.println("--------------------------------------------------------------------");
		System.out.println("                            RUNNING                         ");
		System.out.println();
		
	}
}
