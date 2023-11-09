package simpledb.query;
// importing SimpleDateFormat to parse timestamps
import java.text.SimpleDateFormat;
// In case input string is not a timestamp 
import java.text.ParseException;
import java.util.Date;


public class TimeseriesConstant implements Constant {
   private Date time;
   private int value;
   
   /**
    * Create a constant by wrapping the specified string.
    * @param value - int  timeseries value
    */
   public TimeseriesConstant(int value) throws ParseException {
      this.time = new Date();
      this.value = value;
   }

  
   
   /**
    * Unwraps the object and returns a formated  string. [yyyy-MM-dd HH:mm:ss  value]
    * @see simpledb.query.Constant#asJavaVal()
    */
   public Object asJavaVal() {
      return this;
   }
   
   public boolean equals(Object obj) {
      TimeseriesConstant t = (TimeseriesConstant) obj;
      return t != null && this.value == t.value;
   }

   public int compareTo(Constant obj) {
      TimeseriesConstant t = (TimeseriesConstant) obj;
      return Float.compare(this.value, t.value);
   }

   public int hashCode() {
      return Float.hashCode(value);
   }
   
   public String toString() {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String formattedDate = simpleDateFormat.format(time);
      return  "["+ formattedDate +"\t" + Float.toString(value) + "]";
   }
}
