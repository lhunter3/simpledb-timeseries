package simpledb.parse;

import simpledb.query.*;
import java.util.*;

/**
 * Data for the SQL <i>select</i> statement.
 * @author Edward Sciore
 */
public class QueryData {
   private Collection<String> fields;
   private Collection<String> tables;
   private Predicate pred;
   private boolean isAggregate;
   private String aggregateField;
   private String aggregateFunction;
   
   /**
    * Saves the field and table list and predicate.
    */
   public QueryData(Collection<String> fields, Collection<String> tables, Predicate pred) {
      this.fields = fields;
      this.tables = tables;
      this.pred = pred;
      this.isAggregate = false;
   }

   /**
    * Saves aggregateField and aggregateFunction
    *
    */
   public void setAggregateFunction(String aggregateField, String aggregateFunction) {
      this.isAggregate = true;
      this.aggregateField = aggregateField;
      this.aggregateFunction = aggregateFunction;
   }

   /**
    * Returns true if this query is an aggregate query. 
    * Used in the BasicQueryPlanner to determine if a Aggregate plan is required.
    * @return
    */
   public boolean isAggregate() {
      return isAggregate;
   }

   public String getAggregateField() {
      return aggregateField;
   }

   public String getAggregateFunction() {
      return aggregateFunction;
   }
   
   /**
    * Returns the fields mentioned in the select clause.
    * @return a collection of field names
    */
   public Collection<String> fields() {
      return fields;
   }
   
   /**
    * Returns the tables mentioned in the from clause.
    * @return a collection of table names
    */
   public Collection<String> tables() {
      return tables;
   }
   
   /**
    * Returns the predicate that describes which
    * records should be in the output table.
    * @return the query predicate
    */
   public Predicate pred() {
      return pred;
   }
   
   public String toString() {
      String result = "select ";
      for (String fldname : fields)
         result += fldname + ", ";
      result = result.substring(0, result.length()-2); //remove final comma
      result += " from ";
      for (String tblname : tables)
         result += tblname + ", ";
      result = result.substring(0, result.length()-2); //remove final comma
      String predstring = pred.toString();
      if (!predstring.equals(""))
         result += " where " + predstring;
      return result;
   }
}
