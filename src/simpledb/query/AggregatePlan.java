package simpledb.query;

import simpledb.record.*;

public class AggregatePlan implements Plan {

    private Plan p;
    private String aggregateField;
    private String aggregateFunction;
    
    public AggregatePlan(Plan p, String aggregateField, String aggregateFunction) {
        this.p = p;
        this.aggregateField = aggregateField;
        this.aggregateFunction = aggregateFunction;
    }
    
    public Scan open() {
        Scan s = p.open();
        return new AggregateScan(s, aggregateField, aggregateFunction);
    }
    
    public int blocksAccessed() {
        return p.blocksAccessed();
    }
    
    public int recordsOutput() {
        return 1;
    }
    
    public int distinctValues(String fldname) {
        return 1;
    }
    
    public Schema schema() {
        return p.schema();
    }
    
}
