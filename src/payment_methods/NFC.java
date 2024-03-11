package payment_methods;

public class NFC implements PaybleByInternet  {
	private int money_amount;
	private String name;
	
	public NFC(String name) {
		this.money_amount = 0;
		this.name = name;
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
		return this.money_amount;
	}
	
	public String getName() {
		return this.name;
	}
}
