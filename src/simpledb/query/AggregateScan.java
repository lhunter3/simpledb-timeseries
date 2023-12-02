package simpledb.query;

public class AggregateScan implements Scan {

    private Scan s;
    private String aggregateField;
    private String aggregateFunction;
    private int aggregateResult = 0;
    private boolean calculated = false;

    /**
     * Creates a new aggregate scan.
     *
     * @param s              the underlying scan
     * @param aggregateField the field on which the aggregate function is applied
     * @param aggregateFunction    the aggregate function (e.g., AggregationOperator.SUM)
     * TODO: Modify scan so that it works with regular scans and aggregate scans
     */
    public AggregateScan(Scan s, String aggregateField, String aggregateFunction) {
        this.s = s;
        this.aggregateField = aggregateField;
        this.aggregateFunction = aggregateFunction;
    }

    /**
     * Calculates the aggregate value using the given aggregate function that satisfies the previous scan predicates.
     */
    public boolean next() {
        if (!calculated) {
            while (s.next()) {
                int currentValue = s.getInt(aggregateField);
                switch (aggregateFunction) {
                    case "sum":
                        aggregateResult += currentValue;
                        break;
                    // Add cases for other aggregate operations (e.g., AVG, MAX, MIN) as needed
                }
            }
            calculated = true;
            return true;
        }

        return false;
    }


    /**
     * Closes the scan by closing the underlying scan.
     *
     * @see simpledb.query.Scan#close()
     */
    public void close() {
        s.close();
    }

    /**
     * Positions the scan before its first record.
     * In other words, the scan has no current record.
     *
     * @see simpledb.query.Scan#beforeFirst()
     */
    public void beforeFirst() {
        s.beforeFirst();
    }


    public Constant getVal(String fldname) {
        return new IntConstant(aggregateResult);
    }

    /**
     * @param fldname -  the name of the field
     * @return the aggregate value of the specified field
     */
    public int getInt(String fldname) {
        return aggregateResult;
    }

    public String getString(String fldname) {
        return s.getString(fldname);
    }

    public TimeseriesConstant getTimeseries(String fldname) {
        return s.getTimeseries(fldname);
    }

    public boolean hasField(String fldname) {
        return s.hasField(fldname);
    }

}