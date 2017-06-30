package utils.exporters.csv;

import entropy.parameters.Parameters;
import utils.exporters.FileExporter;

import java.util.List;

public class CSVExporter {

    private static final String SEPARATOR = ",";
    private static final String END_OF_LINE = "\n";
    private static final String CSV = ".csv";

    public static void export(
            List<Double[]> apEns,
            Parameters[] pList,
            String path,
            String name) {

        FileExporter.export(getCSV(apEns, pList), path + name + CSV);

    }

    public static String getCSV(
            List<Double[]> apEns,
            Parameters[] pList) {

        StringBuilder sb = new StringBuilder();

        addList(sb, pList);

        apEns.forEach(apEn -> addList(sb, apEn));

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
