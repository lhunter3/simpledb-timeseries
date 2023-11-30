package simpledb.query;
import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;



public class TimeseriesConstant implements Constant, Serializable {
   private Integer trs;
   private Integer value;
   
   /**
    * Create a constant by wrapping the specified data array.
    * @param value - Long[] array of {value, date}
    */
   public TimeseriesConstant(Long[] arr) {

      this.value = arr[0].intValue();
      this.trs = arr[1].intValue();
   }

  
   
   /**
    * Unwraps the object and returns a formated  string. [yyyy-MM-dd HH:mm:ss  value]
    * @see simpledb.query.Constant#asJavaVal()
    */
   public Object asJavaVal() {
      return this;
   }

   public IntConstant geIntConstant(){
      return new IntConstant(this.value);
   }

   public IntConstant getTrsConstant(){
      return new IntConstant(this.trs);
   }
   
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
   
   public String toString() {
      return  "["+ this.trsToString()  +"\t" + this.value + "]";
   }

   public String trsToString() { 
       long millis = this.trs * 60 * 60 * 1000L;

      // Create a Date object based on the milliseconds from epoch
      Date date = new Date(millis);

      // Create a SimpleDateFormat to format the date
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH");
      return sdf.format(date);
   }
}
