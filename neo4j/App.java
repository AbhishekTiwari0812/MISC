package org.neo4j.neo4j;

public class App {

	public static void main(String[] args) {

		// "Make sure that the database is empty before running the CreateDatabase"
		long startTime = System.currentTimeMillis();
		CreateDatabase();
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Time taken to set up database:" + elapsedTime);

		
		 QueryMethods mQuery = new QueryMethods(); 
		 mQuery.GetDirectTrains("DDN","UMB",2);
		 mQuery.GetRouteWithOneIntermediate("BEA", "ET", 2);
		 mQuery.Shutdown();
		
	}

	// should be called the first time the code is run.To set up the database.
	static void CreateDatabase() {
		Database db = new Database();
		db.ReadFromFile1();
		db.ReadFromFile2();
		db.CreateDatabase();
		db.SetRelations();
		db.Shutdown();
	}

}
