
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.plaf.nimbus.State;

import simpledb.metadata.MetadataMgr;
import simpledb.record.Schema;
import simpledb.remote.SimpleDriver;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

public class DbDriver {
	  
		public static void main(String args[]) {
			DbDriver hon3 = new DbDriver();
			
			//hon3.createTable();	// First, run this class (as a Java application) to create the customer table directly.
			hon3.PrintTableInfo();	//Then, comment out the previous line,  uncomment this line and save. Start simpledb server and then run this class.
			


		}
		
		void createTable() {
			SimpleDB.init("dbWithTimeseries"); 
			MetadataMgr mdMgr = SimpleDB.mdMgr(); 
			Transaction tx1 = new Transaction(); 
			
			//Instruction1: write your code below
			//Using the SimpleDB's MetadataMgr create a table customer (customerid, name, address)
			//**Hint: refer to lecture notes on simpledb metadata manager
			
			Schema s = new Schema();

			s.addIntField("tableid");
			s.addTimeseriesField("tsInteger");
			mdMgr.createTable("testTable", s, tx1);
			
			tx1.commit(); 

			
		
		}
		
		//IMPORTANT: you would need to start simpledb server separately for this to work
		void PrintTableInfo() {
			//Map<String,List<String>> tbls = new HashMap<String,List<String>>();

			Connection conn = null;
			try {
				// Step 1: connect to database server
				Driver d = new SimpleDriver();
				conn = d.connect("jdbc:simpledb://localhost", null);

				/* 
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("insert into customer(customerid, name, address) values(1, 'John', '123 Main St.')");
				stmt.executeUpdate("insert into customer(customerid, name, address) values(2, 'Joe', '456 Main St.')");
				stmt.executeUpdate("insert into customer(customerid, name, address) values(3, 'Jane', '789 Main St.')");
				stmt.executeUpdate("insert into customer(customerid, name, address) values(4, 'Bob', '1011 Main St.')");

				*/

				Statement st = conn.createStatement();
				//stmt.executeUpdate("insert into tableWithTimeseriesDatatype(tableid, timeseriesInteger) values(1, 2)");
				//Statement st = conn.createStatement();



				ResultSet rs = st.executeQuery("select tblname, fldname, type, length, offset from fldcat");
				
				while(rs.next()) {
					
					System.out.println(rs.getString("TblName") + ":" + rs.getString("fldName"));
					
				}
				


				
				/*ResultSet rs2 = st.executeQuery("select tableid, timeseriesInteger from customer");


				


				System.out.println("test ");

				
				while(rs2.next()) {
					
					System.out.println(rs2.getInt("tableid") + " " + rs2.getString("timeseriesInteger"));
					
				}
				*/
				

				
				
				
				

				//Instruction2: write your code below
				// By querying the table fldcat(tblname, fldname, type, length, offset) 
				// directly using JDBC APIs
				// print the names and fields of ALL tables in the database 
				//(e.g., table T having fields A and B should print as  
				// Table T: Field A
				// Table T: Field B
				//)
				//**Hint: Using JDBC APIs create a statement, and execute the query with the statement and iterate over the resultset
				
				
				
				
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				// Step 4: close the connection
				try {
					if (conn != null)
						conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}


}