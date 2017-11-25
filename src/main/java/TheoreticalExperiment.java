import entropy.ApproximateEntropy;
import entropy.parameters.Parameters;
import utils.exporters.csv.CSVExporter;
import utils.importers.csv.OneColumnCSVReader;
import utils.time.Time;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class TheoreticalExperiment {

    private static final String CWD = System.getProperty("user.dir");
    private static final String SRC = CWD + "/../data/theoretical_experiment/apen_src/";
    private static final String OUT = CWD + "/../data/theoretical_experiment/apen_out/";

    private static final String[] EXPERIMENTS = {
            "sine+noise+3", "noise+sine+3", "sine+chaos+3", "chaos+sine+3"};
    private static final double[] NOISE_LEVELS = {0, 0.1, 0.2, 0.3, 0.4};
    private static final int ITERATIONS = 100;

    public static void main(String[] args) {

        Time.log(() -> run(), "TheoreticalExperiment");

    }

    private static void run() {

        int i = 0;
        final Parameters[] pList = new Parameters[12];
        pList[i++] = new Parameters(1, 1000, 0.1);
        pList[i++] = new Parameters(1, 1000, 0.15);
        pList[i++] = new Parameters(1, 1000, 0.2);
        pList[i++] = new Parameters(1, 1000, 0.25);

        pList[i++] = new Parameters(2, 1000, 0.1);
        pList[i++] = new Parameters(2, 1000, 0.15);
        pList[i++] = new Parameters(2, 1000, 0.2);
        pList[i++] = new Parameters(2, 1000, 0.25);

        pList[i++] = new Parameters(3, 1000, 0.1);
        pList[i++] = new Parameters(3, 1000, 0.15);
        pList[i++] = new Parameters(3, 1000, 0.2);
        pList[i] = new Parameters(3, 1000, 0.25);

        Arrays.stream(EXPERIMENTS)
                .forEach(
                        experiment -> {
                            String src = SRC + experiment + "/";
                            String out = OUT + experiment + "/";

                            IntStream.range(0, ITERATIONS)
                                    .forEach(
                                            iteration -> Arrays.stream(NOISE_LEVELS)
                                                    //.parallel()
                                                    .forEach(
                                                            noiseLevel -> {

                                                                String filename = filename(
                                                                        experiment,
                                                                        iteration,
                                                                        noiseLevel
                                                                );

                                                                Time.log(
                                                                        () -> run(
                                                                                filename,
                                                                                src,
                                                                                out,
                                                                                pList),
                                                                        filename
                                                                );
                                                            }

                                                    )
                                    );


                        }
                );

    }

    private static String filename(
            String experiment,
            int iteration,
            double noiseLevel) {

        return experiment + "_" + iteration + "_" + noiseLevel;
    }

    private static void run(
            final String fileName,
            final String src,
            final String out,
            final Parameters[] pList) {

        double[] price = OneColumnCSVReader.read(src, fileName);

        List<double[]> apEns = ApproximateEntropy.apEn(price, pList);

        CSVExporter.export(apEns, pList, out, fileName + "_apen");
    }

}
