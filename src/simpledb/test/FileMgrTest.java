package simpledb.test;

import simpledb.server.SimpleDB;
import simpledb.file.*;

//sray: CS3543 handson3
public class FileMgrTest {
	public static void main(String[] args) throws Exception {
		SimpleDB.init("testdb");
		Block blk = new Block("testfile", 0);
		Page pg = new Page();
		pg.setInt(396, 345);
		pg.write(blk);
		pg.setString(40, "xyz");
		pg.write(blk);

		Page pg2 = new Page();
		pg2.read(blk);
 		System.out.println("value at location 20 = "
 						+ pg2.getInt(20));
 		System.out.println("value at location 40 = "
 						+ pg2.getString(40));
 		
 		//sray:  CS3543 handson3
 		// uncomment the lines below
 		System.out.println("Number of blocks read: " + SimpleDB.fileMgr().blocksRead());
 		System.out.println("Number of blocks written: " + SimpleDB.fileMgr().blocksWritten());
	}
}