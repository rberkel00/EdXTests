public class Item {
	private String name;
	private double listCost;
	private User owner;
	private boolean forSale;
 
	public Item(String name, double listCost, User owner, boolean forSale) {
		this.name = name;
		this.listCost = Math.round(listCost*100) / 100.0;
		this.owner = owner;
		this.forSale = forSale;
	}

	public String getName() {
		return name;
	}

	public double getListCost() {
		return listCost;
	}

	public User getOwner() {
		return owner;
	}

	public boolean getForSale() {
		return forSale;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setListCost(double listCost) {
		this.listCost = Math.round(listCost*100) / 100.0;
	}

	public void toggleForSale() {
		if (forSale) forSale = false;
		else forSale = true;
	}

	public void purchaseItem(User user, double cost) {
		user.updateBalance(0 - cost);
        owner.updateBalance(cost);
		owner = user;
        toggleForSale();
	}

	public String toString() {
		return String.format("%s ($%.2f - %s)", name, listCost, owner.getName());
	}
}