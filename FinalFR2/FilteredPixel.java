public class FilteredPixel extends Pixel {
	private double rp, bp, gp;
	private boolean isFiltered;
	public FilteredPixel(int r, int b, int g, double rp, double bp, double gp) {
		super(r, b, g);
		this.rp = rp;
		this.bp = bp;
		this.gp = gp;
		isFiltered = false;
	}

	public void setFiltered(boolean filtered) {
		this.isFiltered = filtered;
	}

	public int getR() {
		if (isFiltered) {
			return (int)(super.getR() * rp);
		}
		return super.getR();
	}

	public int getG() {
		if (isFiltered) {
			return (int)(super.getG() * gp);
		}
		return super.getG();
	}

	public int getB() {
		if (isFiltered) {
			return (int)(super.getB() * bp);
		}
		return super.getB();
	}

	public String toString() {
		if (isFiltered) {
			return "#" + Integer.toHexString((int)(getR()*rp)) + Integer.toHexString((int)(getG()*gp)) + Integer.toHexString((int)(getB()*bp));
		}
		else return super.toString();
	}

	public static void main(String[] args) {
		FilteredPixel fp = new FilteredPixel(2, 4, 6, 0.5, 0.5, 0.5);
		fp.setFiltered(true);
		System.out.println("test05: " + fp.getR());
		System.out.println("test06: " + fp.getG());
		System.out.println("test07: " + fp.getB());
		System.out.println("test08: " + fp.toString());
		fp.setFiltered(false);
		System.out.println("test09: " + fp.toString());
		System.out.println("test10: " + (fp instanceof Pixel));
	}

}
