package entropy.parameters;

import java.io.Serializable;

public class Parameters implements Serializable {

    private int m;
    private int n;
    private double sdPercentage;

    public Parameters(int m, int n, double sdPercentage) {
        this.m = m;
        this.n = n;
        this.sdPercentage = sdPercentage;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public double getSdPercentage() {
        return sdPercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameters that = (Parameters) o;

        if (m != that.m) return false;
        if (n != that.n) return false;
        return Double.compare(that.sdPercentage, sdPercentage) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = m;
        result = 31 * result + n;
        temp = Double.doubleToLongBits(sdPercentage);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return m + " " + n + " " + sdPercentage;
    }
}