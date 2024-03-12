package payment_methods;

public class Crypto implements PaybleByInternet {

	private int money_amount;
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
	
}
