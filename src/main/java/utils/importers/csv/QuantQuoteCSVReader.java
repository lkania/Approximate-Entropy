package utils.importers.csv;

import org.eclipse.collections.impl.list.mutable.primitive.DoubleArrayList;

public class QuantQuoteCSVReader {

    public static double[] read(String path) {

        DoubleArrayList ans = new DoubleArrayList();

        CSVReader.readTimeSerie(path,
                line -> ans.add(Double.parseDouble(line)));

        return ans.toArray();
    }
}
