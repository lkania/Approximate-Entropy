package utils;

public class CommandLineUtils {

    private static final String SEPARATOR = ",";

    public static double[] StringToArray(String s) {
        String[] split = s.split(SEPARATOR);
        double[] ans = new double[split.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Double.parseDouble(split[i]);
        }
        return ans;
    }

    public static String ArrayToString(double[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i != a.length - 1)
                sb.append(SEPARATOR);
        }
        return sb.toString();
    }

}
