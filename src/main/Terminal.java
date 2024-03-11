package main;

import payment_methods.Payble;
import payment_methods.PaybleByInternet;

public class Terminal {
	private String name;
	
	private boolean cashService;
	private boolean internetPayService;
	
	public Terminal(String name, boolean internetPayService, boolean cashService) {
		this.name = name;
		this.internetPayService = internetPayService;
		this.cashService = cashService;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void customerServiceOnline(PaybleByInternet pay_thing) {
		if(!this.internetPayService) {
			System.out.println("Цей термінал не підтримує інтернет сервіс");
			return;
		}
		
		int input = Main.getUsersInput("Що хочете зробити?\n"
				+ "1. Зняти гроші\n"
				+ "2. Покласти гроші", 2);
		
		double money_amount_input = getMoneyAmount();
		
		switch(input) {
		case 1:
			pay_thing.send_request_withdraw_money(money_amount_input);
			break;
		case 2:
			if(!wallet_withdraw(money_amount_input))
				return;
			
			pay_thing.send_request_add_money(money_amount_input);
			break;
		}
	}
	
	public void customerServiceCash(Payble wallet) {
		if(!this.cashService) {
			System.out.println("Цей термінал не підтримує цей сервіс");
			return;
		}
		
		int input = Main.getUsersInput("Що хочете зробити?\n"
				+ "1. Зняти гроші\n"
				+ "2. Покласти гроші", 2);
		
		double money_amount_input = getMoneyAmount();
		
		switch(input) {
		case 1:
			wallet.withdraw_money(money_amount_input);
			break;
		case 2:
			wallet.add_money(money_amount_input);
			break;
		}
	}
	
	private boolean wallet_withdraw(double money_amount_input) {
		return Main.wallet.withdraw_money(money_amount_input);
	}
	
	private double getMoneyAmount(){
		double money_amount_input;
		while(true) {
			System.out.println("Введіть кількість коштів");
			try {
				money_amount_input = Main.scanner.nextDouble();
			}
			catch(Exception ex) {
				System.out.println("Введіть значення нормально");
				continue;
			}
			finally {
				Main.scanner.nextLine();
			}
			
			break;
		}
		
		return money_amount_input;
	}
}
