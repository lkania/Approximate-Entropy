import entropy.ApproximateEntropy;
import entropy.parameters.Parameters;
import utils.CommandLineUtils;

public class CommandLine {

    public static void main(String[] args) {

        double[] ts = CommandLineUtils.StringToArray(args[0]);
        int m = Integer.parseInt(args[1]);
        int n = Integer.parseInt(args[2]);
        double r = Double.parseDouble(args[3]);

        Parameters p = new Parameters(m, n, r);

        double[] apen = ApproximateEntropy.apEn(ts, p);

        System.out.print(CommandLineUtils.ArrayToString(apen));
    }

}
