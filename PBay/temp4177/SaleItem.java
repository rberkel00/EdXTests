public class SaleItem extends Item {
	private double discount;

	public SaleItem(String name, double listCost, User owner, double discount) {
		super(name, listCost, owner, true);
		this.discount = discount;
	}

	public String getDiscount() {
		return String.format("%d%%", Math.round(100*discount));
	}

	public void purchaseItem(User user) {
		super.purchaseItem(user, getListCost()*(1-discount));
	}
}



