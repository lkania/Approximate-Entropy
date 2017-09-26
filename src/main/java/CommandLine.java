import entropy.ApproximateEntropy;
import entropy.parameters.Parameters;
import utils.CommandLineUtils;

public class CommandLine {

    private static Parameters p = new Parameters(2, 1000, 0.1);

    public static void main(String[] args) {

        String s = args[0];

        double[] ts = CommandLineUtils.StringToArray(s);

        double[] apen = ApproximateEntropy.apEn(ts, p);

        String ans = CommandLineUtils.ArrayToString(apen);

        System.out.print(ans);
    }

}
