package payment_methods;

public class Wallet implements Payble {
	private double money_amount;
	
	public Wallet() {
		this.money_amount = 0;
	}
	
	@Override
	public boolean withdraw_money(double money_to_sub) {
		if(withdraw_check(money_amount, money_to_sub)) {
			money_amount -= money_to_sub;
			return true;
		}
		
		return false;
	}
	@Override
	public void add_money(double money_to_add) {
		this.money_amount += money_to_add;
	}
	
	@Override
	public double get_money() {
		return this.money_amount;
	}

}
