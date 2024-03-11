package payment_methods;

public interface PaybleByInternet {
	
	boolean send_request_withdraw_money(double money_to_sub);
	
	void send_request_add_money(double money_to_add);
	
	double send_request_get_money();
	
	default boolean withdraw_check(double money_amount, double money_to_withdraw) {
		if(money_amount < money_to_withdraw) {
			System.out.println("Недостатньо коштів");
			return false;
		}
		
		return true;
	}
}
