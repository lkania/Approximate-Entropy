import entropy.parameters.Parameters;
import utils.importers.csv.QuantQuoteCSVReader;
import utils.time.Time;

import java.util.Arrays;

public class IncrementalApEnComputation {


    public static final String CWD = System.getProperty("user.dir");
    public static final String OUT = CWD + "/../data/apEn/incremental/";

    /**
     * It has to be changed to the desired directory
     */
    public static final String SRC = CWD + "/../data/incremental/";

    public static final String[] stocks = {"spy"};

    public static void main(String[] args) {

        Time.log(() -> run(), "IncrementalApEnComputation");

    }

    private static void run() {

        final Parameters[] pList = new Parameters[1];
        pList[0] = new Parameters(3, 1000, 0.1);


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

        //List<ArrayList<Double>> apEns = ApproximateEntropy.apEn(price, pList);

        //CSVExporter.export(apEns, pList, outPath, stock);
    }

}
