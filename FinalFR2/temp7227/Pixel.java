public class Pixel {
    private int r, g, b;

    public Pixel() {r = g = b = 0;}

    public Pixel(int r, int g, int b) {
        setR(r);
        setG(g);
        setB(b);
    }

    public int getR() {return this.r;}

    public void setR(int r) {
        if (r >= 0) {
            if (r <= 255)
                this.r = r;
            else
                this.r = 255;
        } else {
            this.r = 0;
        }
    }

    public int getG() {return this.g;}

    public void setG(int g) {
        if (g >= 0) {
            if (r <= 255)
                this.g = g;
            else
                this.g = 255;
        } else {
            this.g = 0;
        }
    }

    public int getB() {return this.b;}

    public void setB(int b) {
        if (b >= 0) {
            if (b <= 255)
                this.b = b;
            else
                this.b = 255;
        } else {
            this.b = 0;
        }
    }
    // similar methods getG, getB, setG, and setB are not shown

    public String toString() {
        return "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
    }
}
