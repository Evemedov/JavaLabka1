package payment_methods;

public abstract class Cards implements PaybleByInternet {
	protected double money_amount;
	protected String card_number;
	
	public Cards(String card_number) {
		this.money_amount = 0;
		this.card_number = card_number;
	}
	public Cards() {
		this.money_amount = 0;
		this.card_number = "";
	}
	
	@Override
	public boolean send_request_withdraw_money(double money_to_sub) {
		if(withdraw_check(money_amount, money_to_sub)) {
			money_amount -= money_to_sub;
			return true;
		}
		
		return false;
	}

	@Override
	public void send_request_add_money(double money_to_add) {
		this.money_amount += money_to_add;
	}

	@Override
	public double send_request_get_money() {
		return Math.round((this.money_amount) * 100d) / 100d;
	}
	
	public void generateCardNumber() {
		int num1 = (int)(Math.random() * 8999 + 1000);
		int num2 = (int)(Math.random() * 8999 + 1000);
		int num3 = (int)(Math.random() * 8999 + 1000);
		int num4 = (int)(Math.random() * 8999 + 1000);
		
		this.card_number = num1 + " " + num2 + " " + num3 + " " + num4;
	}
	
	public String getName() {
		return this.card_number;
	}
}
