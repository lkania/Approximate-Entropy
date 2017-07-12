package entropy;

import entropy.parameters.Parameters;
import utils.statistics.TimeSeries;
import utils.time.Time;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the algorithm proposed by Pincus to calculate the Approximate Entropy (ApEn).
 * The original paper (Pincus 1991) can be accessed by the following link:
 * https://drive.google.com/open?id=0B4Za2CLWDa-iaUpSd2lxZ2pQOVE
 * A more in deep explanation of the algorithm can be accessed by the following link:
 * https://drive.google.com/open?id=0B4Za2CLWDa-iVjdwSENXbW81cjQ
 */

public class ApproximateEntropy {

    public static List<double[]> apEn(
            final double[] ts,
            final Parameters[] pList) {

        ArrayList<double[]> ans = Arrays.stream(pList)
                .parallel()
                .map(p -> Time.log(() -> apEn(ts, p), ts.length + " " + p.toString()))
                .collect(Collectors.toCollection(ArrayList::new));

        return ans;
    }


    public static double[] apEn(
            final double[] ts,
            final Parameters p) {

        int startIndex = 2000;
        int limit = ts.length;

        double[] ans = new double[limit - startIndex];

        for (int i = startIndex; i < limit; i++) {
            int m = p.getM();
            int n = p.getN();

            /**
             * We start at i-n+1 to use the data
             * at ts[end], i.e. to use the data from
             * the last available data-point.
             */

            int start = i - n + 1;
            int end = i;
            double r = TimeSeries.sd(ts, start, end) * p.getSdPercentage();

            ans[i - startIndex] = apEn(ts, start, end, m, r, n);

        }

        return ans;

    }

    private static double apEn(
            double[] ts,
            int start,
            int end,
            int m,
            double r,
            int n) {

        return phi(ts, start, end, m, r, n) - phi(ts, start, end, m + 1, r, n);

    }

    private static double phi(
            double[] ts,
            int start,
            int end,
            int m,
            double r,
            int n) {

        double ans = 0;

        /**
         * The limit is set to end - m + 1, because the last distance
         * is going to be done with i=end, therefore a vector
         * with (ts[end],ts[end+1],...,ts[end+m-1]) will be built.
         */

        for (int i = start; i <= end - m + 1; i++) {
            ans += Math.log(c(ts, start, end, m, r, n, i));
        }

        ans /= n - m + 1;

        return ans;
    }

    private static double c(
            double[] ts,
            int start,
            int end,
            int m,
            double r,
            int n,
            int index) {

        double ans = 0;
        for (int i = start; i <= end - m + 1; i++) {
            /**
             * Important! The ApEn algorithm counts each sequence as matching itself,
             * a practice carried over to avoid the occurrence of ln (0) in the calculations.
             * This step has led to discussion of a bias of ApEn towards regularity.
             *
             * Original discussion:
             *  Richman and Moorman (2000) - https://drive.google.com/open?id=0B4Za2CLWDa-iekRDVGZZc3lWZTA
             * Further discussion:
             *  https://drive.google.com/file/d/0B4Za2CLWDa-ib0plSzJZZklhb1E/view?usp=sharing
             */
            if (distance(ts, m, i, index) <= r) {
                ans++;
            }
        }

        ans /= n - m + 1;

        return ans;
    }

    private static double distance(
            double[] ts,
            int m,
            int i1,
            int i2) {

        double ans = 0;
        for (int i = 0; i < m; i++) {
            double val1 = ts[i1 + i];
            double val2 = ts[i2 + i];
            ans = Math.max(ans, Math.abs(val1 - val2));
        }
        return ans;
    }


}
