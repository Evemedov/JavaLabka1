package payment_methods;

public interface Payble {
	boolean withdraw_money(double money_to_sub);
	
	void add_money(double money_to_add);
	
	double get_money();
	
	default boolean withdraw_check(double money_amount, double money_to_withdraw) {
		if(money_amount < money_to_withdraw) {
			System.out.println("Недостатньо коштів");
			return false;
		}
		
		return true;
	}
}
