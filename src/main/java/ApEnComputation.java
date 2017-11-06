import entropy.ApproximateEntropy;
import entropy.parameters.Parameters;
import utils.exporters.csv.CSVExporter;
import utils.importers.csv.OneColumnCSVReader;
import utils.time.Time;

import java.util.Arrays;
import java.util.List;

public class ApEnComputation {


    private static final String CWD = System.getProperty("user.dir");
    private static final String SRC = CWD + "/../data/apEn_src/incremental/15min/shared_period/";
    private static final String OUT = CWD + "/../data/apEn/incremental/15min/shared_period/";
    private static final String[] stocks = {"spy", "qqqq", "dia"};


    public static void main(String[] args) {

        Time.log(() -> run(), "ApEnComputation");

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
        pList[i++] = new Parameters(3, 1000, 0.25);

        Arrays.stream(stocks)
                .forEach(
                        stock -> {

                            String srcFile = SRC + stock + ".csv";

                            Time.log(
                                    () -> run(stock, srcFile, OUT, pList),
                                    stock
                            );

                        }
                );

    }


    private static void run(
            final String stock,
            final String srcFile,
            final String outPath,
            final Parameters[] pList) {

        double[] price = OneColumnCSVReader.read(srcFile);

        List<double[]> apEns = ApproximateEntropy.apEn(price, pList);

        CSVExporter.export(apEns, pList, outPath, stock);
    }

}
