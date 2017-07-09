import entropy.parameters.Parameters;
import entropy.parameters.ParametersFactory;
import utils.importers.csv.QuantQuoteCSVReader;
import utils.time.Time;

import java.util.ArrayList;
import java.util.Arrays;

public class EntropyComputation {


    public static final String CWD = System.getProperty("user.dir");
    public static final String OUT = CWD + "/../data/apEn/";

    public static final String SRC = CWD + "/../data/close/";

    public static final String[] stocks = {"dia", "qqqq", "spy"};


    private static double[] sdPercentages = new double[]{0.2, 0.1};

    //For m=2
    public static int[] nsM2 = new int[]{300, 400, 500, 600, 700, 800, 900, 1000};

    //For m=3
    public static int[] nsM3 = new int[]{1000};

    public static void main(String[] args) {

        Time.log(() -> run(), "EntropyComputation");

    }

    private static void run() {

        ArrayList<Parameters> pListOri = new ArrayList<>();
        ParametersFactory.addParameters(2, nsM2, sdPercentages, pListOri);
        ParametersFactory.addParameters(3, nsM3, sdPercentages, pListOri);
        final Parameters[] pList = pListOri.toArray(new Parameters[pListOri.size()]);

        Arrays.stream(stocks)
                .parallel()
                .forEach(
                        stock -> {
                            String srcFile = SRC + stock + ".csv";

                            double[] price = QuantQuoteCSVReader.read(srcFile);

                            //   List<Double[]> apEns = ApproximateEntropy.apEn(price, pList);

                            // CSVExporter.export(apEns, pList, OUT, stock);
                        }
                );
    }

}
