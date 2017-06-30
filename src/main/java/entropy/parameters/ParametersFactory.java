package entropy.parameters;

import java.util.List;

public class ParametersFactory {

    public static void addParameters(
            int m,
            int[] ns,
            double[] sdPercentages,
            List<Parameters> pList) {

        for (int n : ns) {
            for (double sdP : sdPercentages) {
                pList.add(new Parameters(m, n, sdP));
            }
        }

    }
}
