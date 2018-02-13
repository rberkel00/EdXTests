public class AuctionItem extends Item {
	private int daysLeft;
	private double currentBid;
	private User highBidder;

	public AuctionItem(String name, double listCost, User owner, int days) {
		super(name, listCost, owner, true);
		currentBid = listCost;
		daysLeft = days;
		highBidder = null;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public double getCurrentBid() {
		return currentBid;
	}

	public void advanceDay() {
		if (getForSale()) {
			if (daysLeft == 0) {
				purchaseItem(highBidder, currentBid);
			} else {
				daysLeft--;
			}
		}
	}

	public String makeBid(User user, double bid) {
		if (getForSale()) {
			if (bid > currentBid) {
				currentBid = bid;
				highBidder = user;
			}
		}
		return highBidder.getName();
	}

	public void resetAuction(double startingBid, int days) {
		if (highBidder == null) {
			currentBid = startingBid;
			daysLeft = days;
		}
	}

	public String toString() {
		String user = "None";
		if (getOwner() != null) user = getOwner().getName();
		return String.format("%s ($%.2f, %s, %d days left)", getName(), currentBid, user, daysLeft);
	}

}


