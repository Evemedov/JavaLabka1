package main;
import java.util.Scanner;

import payment_methods.*;


public class Main {
	public static Scanner scanner;
	private static Terminal[] terminals = {new Terminal("Приватбанк", true, true), 
			new Terminal("Монобанк", true, false), 
			new Terminal("Ощадбанк", false, true)};
	
	//pay methods
	public static Wallet wallet;
	private static MasterCard mastercard;
	private static Visa_Card visa;
	private static Crypto crypto;
	private static NFC nfc;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		pay_methods_init();
		
		//main loop
		while(true) {
			int input = getUsersInput("\nЩо хочете зробити?\n"
					+ "1. Заробити кошти\n"
					+ "2. Підійти до терміналу\n"
					+ "3. Подивитися стан коштів\n"
					+ "4. Вийти з програми", 4);
				
			//switch
			switch(input) {
			case 1:
				System.out.println("Ви заробили 1000 гривень");
				wallet.add_money(1000);
				
				break;
			case 2:
				//To witch terminal ask
				String str = "До якого терміналу підійдете?\n";
				for(int i = 0; i < terminals.length; i++){
					str += i + 1 + ". " + terminals[i].getName() + "\n";
				}
				
				int input2 = getUsersInput(str, terminals.length) - 1;
				
				//work with terminal
				int input3 = Main.getUsersInput("Вітаємо в терміналі \"" + terminals[input2].getName() + "\". Яким способом оплати хочете скористатися?\n"
						+ "1. Карточка Mastercard\n"
						+ "2. Карточка Visa\n"
						+ "3. Криптовалюта\n"
						+ "4. NFC\n"
						+ "5. Готівка", 5);
				
				switch(input3) {
				case 1:
					terminals[input2].customerServiceOnline(mastercard);
					break;
				case 2:
					terminals[input2].customerServiceOnline(visa);
					break;
				case 3:
					terminals[input2].customerServiceOnline(crypto);
					break;
				case 4:
					terminals[input2].customerServiceOnline(nfc);
					break;
				case 5:
					terminals[input2].customerServiceCash(wallet);
					break;
				}
				
				break;
			case 3:
				System.out.println("Гаманець: " + wallet.get_money());
				System.out.println("Visa \"" + visa.getName() + "\": " + visa.send_request_get_money());
				System.out.println("Mastercard \"" + mastercard.getName() + "\": " + mastercard.send_request_get_money());
				System.out.println("Кріптогаманець: " + crypto.send_request_get_money());
				System.out.println("NFC модуль \"" + nfc.getName() + "\": " + nfc.send_request_get_money());
				break;
			case 4:
				System.exit(0);
				break;
			}
		}
		
	}
	
	private static void pay_methods_init() {
		wallet = new Wallet();
		mastercard = new MasterCard();
		mastercard.generateCardNumber();
		visa = new Visa_Card();
		visa.generateCardNumber();
		crypto = new Crypto();
		nfc = new NFC("3F7A739A2F1E");
	}
	
	public static int getUsersInput(String titleStr, int chooseTo) {
		int input;
		while(true) {
			System.out.println(titleStr);
			try {
				input = scanner.nextInt();
			}
			catch(Exception ex) {
				System.out.println("Введіть значення нормально");
				continue;
			}
			finally {
				scanner.nextLine();
			}
			
			if(input > chooseTo || input < 0) {
				System.out.println("Введіть значення нормально");
				continue;
			}
			
			System.out.print("\n");
			return input;
		}
	}
}
