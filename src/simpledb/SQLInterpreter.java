package simpledb;

import java.sql.*;

import simpledb.remote.SimpleDriver;
import java.io.*;

public class SQLInterpreter {
    private static Connection conn = null;

    public static void main(String[] args) {
	   try {
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);

			Reader rdr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(rdr);

			while (true) {
				// process one line of input
				System.out.print("\nSQL> ");
				String cmd = br.readLine().trim();
				System.out.println();
				if (cmd.startsWith("exit"))
					break;
				else if (cmd.startsWith("select"))
					if(cmd.contains("sum")){
						doAggregrateQuery(cmd);
					}
					else
					doQuery(cmd);
				else
					doUpdate(cmd);
		    }
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void doQuery(String cmd) {
		try {
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(cmd);
		    ResultSetMetaData md = rs.getMetaData();
		    int numcols = md.getColumnCount();
		    int totalwidth = 0;

		    // print header
		    for(int i=1; i<=numcols; i++) {
				int width = md.getColumnDisplaySize(i);
				totalwidth += width;
				String fmt = "%" + width + "s";
				System.out.format(fmt, md.getColumnName(i));
			}
			System.out.println();
			for(int i=0; i<totalwidth; i++)
			    System.out.print("-");
		    System.out.println();

		    // print records
		    while(rs.next()) {
				for (int i=1; i<=numcols; i++) {
					String fldname = md.getColumnName(i);
					int fldtype = md.getColumnType(i);
					String fmt = "%" + md.getColumnDisplaySize(i);
					if (fldtype == Types.INTEGER)
						System.out.format(fmt + "d", rs.getInt(fldname));
					else if(fldtype == Types.JAVA_OBJECT){
						System.out.format(fmt + "s", rs.getObject(fldname));
					}
					else
						System.out.format(fmt + "s", rs.getString(fldname));
				}
				System.out.println();
			}
			rs.close();
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void doUpdate(String cmd) {
		try {
		    Statement stmt = conn.createStatement();
		    int howmany = stmt.executeUpdate(cmd);
		    System.out.println(howmany + " records processed");
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}



	/*
	 * Due to the fact that the aggregate function is only sum, we can hardcode the aggregate function
	 * Also the aggregate only works on integer fields and can only be used at one time. 
	 * Ie select name, sum(id) from student; will not work but select sum(id) from student; will not work
	 * This can be fixed but I did not have time to do so.
	 * -Lucas
	 */
	private static void doAggregrateQuery(String cmd){
		try {
			Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(cmd);
		    ResultSetMetaData md = rs.getMetaData();
		    int numcols = md.getColumnCount();
		    int totalwidth = 0;

			String feildname = "";
		    // print header
		    for(int i=1; i<=numcols; i++) {
				//hardcoding for now
				int width = 4;
				totalwidth += width;
				String fmt = "%" + width + "s";
				//hardcoded new aggregate field name

				feildname = "sum(" + md.getColumnName(i) + ")";
				System.out.format(fmt, feildname);
			}
			System.out.println();
			for(int i=0; i<feildname.length(); i++)
			    System.out.print("-");
		    System.out.println();
			
			// print the aggregate record
			String aggregateField = md.getColumnName(1);
			while(rs.next()){
				System.out.println(rs.getInt(aggregateField));
			}
			
		}
		catch (SQLException e) {
			System.out.println("SQL Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}