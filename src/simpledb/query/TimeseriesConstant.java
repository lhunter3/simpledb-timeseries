package simpledb.query;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;



public class TimeseriesConstant implements Constant, Serializable {
   private Integer trs;
   private Integer value;
   
   /**
    * Create a constant by wrapping the specified data array.
    * @param value - int[] array of {value, date}
    */
   public TimeseriesConstant(int[] arr) {
      this.value = arr[0];
      this.trs = arr[1];
   }

   
   /**
    * Unwraps and returns the object
    * @return Object
    */
   public Object asJavaVal() {
      return this;
   }

   /**
    * Gets the Integer value of the TimeseriesConstant
    * @return IntConstant
    */
   public IntConstant geIntConstant(){
      return new IntConstant(this.value);
   }

   /**
    * Gets the TRS Time value from the TimeseriesConstant as IntConstant
    * @return IntConstant
    */
   public IntConstant getTrsConstant(){
      return new IntConstant(this.trs);
   }
   
   /**
    * Gets the TRS Time value from the TimeseriesConstant as StringConstant
    * @return StringConstant
    */
   public StringConstant getTrsStringConstant(){
      return new StringConstant(this.trsToString());
   }


   public boolean equals(Object obj) {
         TimeseriesConstant t =  (TimeseriesConstant) obj;
         return t != null && this == t;
   }

   public int compareTo(Constant obj) {
      TimeseriesConstant t = (TimeseriesConstant) obj;
      return this.trs.compareTo(t.trs);
   }

 
   public int hashCode() {
      return Float.hashCode(value);
   }
 
   /**
    * Converts the TRS Time value from the TimeseriesConstant to a readable String
    * @return String
    */
   public String trsToString() { 
      long millis = this.trs * 60 * 60 * 1000L;
      Date date = new Date(millis);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
      return sdf.format(date);
   }

   public String toString() {
      return  "["+ this.trsToString()  +"\t" + this.value + "]";
   }
}
