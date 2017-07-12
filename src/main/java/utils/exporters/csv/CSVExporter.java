package utils.exporters.csv;

import entropy.parameters.Parameters;
import utils.exporters.FileExporter;

import java.util.List;

public class CSVExporter {

    private static final String SEPARATOR = ",";
    private static final String END_OF_LINE = "\n";
    private static final String CSV = ".csv";

    public static void export(
            List<double[]> apEns,
            Parameters[] pList,
            String path,
            String name) {

        FileExporter.export(getCSV(apEns, pList), path + name + CSV);

    }

    public static String getCSV(
            List<double[]> apEns,
            Parameters[] pList) {

        StringBuilder sb = new StringBuilder();

        addList(sb, pList);

        for (int i = 0; i < apEns.get(0).length; i++) {

            Double[] vals = new Double[apEns.size()];
            int j = 0;
            for (double[] apEn : apEns) {
                vals[j++] = apEn[i];

            }
            addList(sb, vals);
        }

        return sb.toString();

    }

    private static <T> void addList(
            StringBuilder sb,
            T[] objs) {

        for (int i = 0; i < objs.length; i++) {
            sb.append(objs[i].toString());
            if (i != objs.length - 1) {
                sb.append(SEPARATOR);
            } else {
                sb.append(END_OF_LINE);
            }
        }
    }

}
