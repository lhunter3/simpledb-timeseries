package simpledb.query;
import java.io.Serializable;
// importing SimpleDateFormat to parse timestamps
import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeseriesConstant implements Constant, Serializable {
   private Date time;
   private int value;
   
   /**
    * Create a constant by wrapping the specified data array.
    * @param value - Long[] array of {value, date}
    */
   public TimeseriesConstant(Long[] arr) {

      this.value = arr[0].intValue();
      this.time = new Date(arr[1]);
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

      //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      //String formattedDate = simpleDateFormat.format(time);
      return  "["+ this.time +"\t" + this.value + "]";
   }
}
