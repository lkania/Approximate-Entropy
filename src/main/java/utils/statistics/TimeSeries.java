package utils.statistics;

public class TimeSeries {

    public static double sd(double[] ts, int start, int end) {
        double ans = 0;
        double mean = mean(ts, start, end);
        for (int i = start; i < end; i++) {
            ans += (ts[i] - mean) * (ts[i] - mean);
        }
        ans /= end - start + 1;
        ans = Math.sqrt(ans);
        return ans;
    }

    public static double mean(double[] ts, int start, int end) {
        double ans = sum(ts, start, end);
        ans /= end - start + 1;
        return ans;
    }

    public static double sum(double[] ts, int start, int end) {
        double ans = 0;
        for (int i = start; i < end; i++) {
            ans += ts[i];
        }
        return ans;
    }

}
