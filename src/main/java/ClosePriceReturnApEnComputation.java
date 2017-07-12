import entropy.ApproximateEntropy;
import entropy.parameters.Parameters;
import utils.exporters.csv.CSVExporter;
import utils.importers.csv.QuantQuoteCSVReader;
import utils.time.Time;

import java.util.Arrays;
import java.util.List;

public class ClosePriceReturnApEnComputation {


    public static final String CWD = System.getProperty("user.dir");
    public static final String OUT = CWD + "/../data/apEn/test/";

    /**
     * It has to be changed to the desired directory
     */
    public static final String SRC = CWD + "/../data/closeReturn/";

    public static final String[] stocks = {"spy", "qqqq", "dia"};


    public static void main(String[] args) {

        Time.log(() -> run(), "ClosePriceReturnApEnComputation");

    }

    private static void run() {

        final Parameters[] pList = new Parameters[5];
        pList[0] = new Parameters(3, 1000, 0.1);
        pList[1] = new Parameters(3, 1100, 0.1);
        pList[2] = new Parameters(3, 1200, 0.1);
        pList[3] = new Parameters(3, 1300, 0.1);
        pList[4] = new Parameters(3, 1400, 0.1);


        Arrays.stream(stocks).parallel()
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

        double[] price = QuantQuoteCSVReader.read(srcFile);

        List<double[]> apEns = ApproximateEntropy.apEn(price, pList);

        CSVExporter.export(apEns, pList, outPath, stock);
    }

}
