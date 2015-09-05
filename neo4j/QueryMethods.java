//TODO: Don't forget to begin transaction and finish it!!

//TODO:And also closing the database after all the queries.

package org.neo4j.neo4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class QueryMethods {

	private static final String DB_PATH = "I:/AppDB/new_database";
	private Transaction tx = null;
	private GraphDatabaseService db;

	String RotateString(String s, int i) {
		String temp = s.substring(s.length() - i);
		char[] tempArray = s.toCharArray();
		for (int iter = s.length() - i - 1; iter >= 0; iter--) {
			tempArray[iter + i] = tempArray[iter];
		}
		for (int iter = 0; iter < i; iter++) {
			tempArray[iter] = temp.charAt(iter);
		}
		return new String(tempArray);
	}

	@SuppressWarnings("rawtypes")
	private class IntermediatePath implements Comparable {
		private static final long MAX_TIME_DIFF = 10l * 60l * 60l * 1000l; // 10
																			// hours
		private static final long MIN_TIME_DIFF = 2l * 60l * 60l * 1000l; // 2
																			// hours
		Node IntermediateStation;
		Node FirstTrain;
		Node SecondTrain;
		int DistOne;
		int DayOnRouteT1;
		int DayOnRouteT2;
		int DistTwo;

		// for the method isFeasible()
		String ArrivalTime;
		String DeptTime;
		String DaysOfRunTrain1;
		String DaysOfRunTrain2;

		@Override
		public String toString() {

			return ("1." + FirstTrain.getProperty("Number") + " 2."
					+ SecondTrain.getProperty("Number")
					+ " Intermediate Station:"
					+ IntermediateStation.getProperty("StationCode") + " Arr:"
					+ ArrivalTime + " Dep:" + DeptTime + " Dist:" + (DistOne + DistTwo));
		}

		public int compareTo(Object arg0) {
			// less the distance,more the priority
			IntermediatePath CompTo = (IntermediatePath) arg0;
			int flag = (this.DistOne + this.DistTwo)
					- (CompTo.DistOne + CompTo.DistTwo);
			if (flag > 0)
				return 1;
			else if (flag < 0)
				return -1;
			else
				return 0;
		}

		public boolean isFeasible(int day) {
			if (!FirstTrain.getLabels().equals(SecondTrain.getLabels())) {
				_("Error in setting up the result to the values.");
				return false;
			}
			boolean flag1 = false, flag2 = false;
			/*
			 * rotating the train day of run because the train may not arrive at
			 * the station on the same as it departed from the source So if
			 * TrainRunDays are 1100101 and train reaches on 3rd day on route at
			 * the station on that station TrainRunDays must be 0111001.This is
			 * what RotateString is doing.
			 */
			DaysOfRunTrain1 = RotateString(
					(String) FirstTrain.getProperty("RunDays"),
					DayOnRouteT1 - 1);
			DaysOfRunTrain2 = RotateString(
					(String) SecondTrain.getProperty("RunDays"),
					DayOnRouteT2 - 1);
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy hh:mm",
					Locale.ENGLISH);
			// if the first train doesn't run on that day.
			if (DaysOfRunTrain1.charAt(day) != '1') {
				return false;
			} else {
				// if the second train runs on the
				// same day as the first train
				if (DaysOfRunTrain2.charAt(day) == '1') {
					String TempTime1 = "Dec 8, 1994 " + ArrivalTime;
					String TempTime2 = "Dec 8, 1994 " + DeptTime;
					Date date1 = null, date2 = null;
					try {
						date1 = sdf.parse(TempTime1);
						date2 = sdf.parse(TempTime2);
						long TimeDiff = date2.getTime() - date1.getTime();
						if (TimeDiff >= MIN_TIME_DIFF
								&& TimeDiff <= MAX_TIME_DIFF)
							flag1 = true;
					} catch (ParseException e) {
						flag1 = false; // if there's a problem with input file
						// method returns a false value by default
					}
				}
				if (day == 6)
					day = -1;
				// checking if the 2nd trains runs next day
				// with the time constraints being met(of course)
				if (DaysOfRunTrain2.charAt(day + 1) == '1') {
					String TempTime1 = "Dec 8, 1994 " + ArrivalTime;
					String TempTime2 = "Dec 9, 1994 " + DeptTime;
					Date date1 = null, date2 = null;
					try {
						date1 = sdf.parse(TempTime1);
						date2 = sdf.parse(TempTime2);
						long TimeDiff = date2.getTime() - date1.getTime();
						if (TimeDiff >= MIN_TIME_DIFF
								&& TimeDiff <= MAX_TIME_DIFF)
							flag2 = true;
					} catch (ParseException e) {
						// e.printStackTrace();
						flag2 = false;
					}
				}
			}
			return (flag1 || flag2);

		}

	}

	QueryMethods() {
		db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
	}

	/*
	 * LOGIC-> Get the trains going through station 1,put those in a hash map.
	 * Get the trains going through station 2,put those in a hash map For each
	 * entry in hash map1 ,check if it's in hash map2 . if yes(means that train
	 * goes through both the stations) then compare the StopNumber of the
	 * station. If Stop Number of station 2 > Stop Number of station 1 for a
	 * particular train then it means train goes from station 1 to station 2. In
	 * conclusion,The train is one possible answer.Put it in a set. Do it for
	 * all the trains in hash map 1 and Voila :)
	 */

	@SuppressWarnings({ "deprecation", "unchecked" })
	void GetDirectTrains(String station_code_1, String station_code_2, int day) {
		// TODO:Add day argument and change code accordingly.
		try {
			Set<Node> TrainList = new HashSet<Node>();
			tx = db.beginTx();
			String query = "MATCH (:STATION {StationCode:\""
					+ station_code_1
					+ "\"})<-[relation1:GOES_THROUGH]-(TrainList)-[relation2:GOES_THROUGH]->(:STATION {StationCode:\""
					+ station_code_2
					+ "\"})WHERE relation1.DistFromSource < relation2.DistFromSource  RETURN TrainList ,relation1.DayOnRoute";
			Result Result1 = db.execute(query); // train list
			while (Result1.hasNext()) {
				Map<String, Object> row = Result1.next();
				Node temp = null;

				temp = (Node) ((Entry<String, Object>) ((Result) row.entrySet())
						.next()).getValue();
				int RotateBy = (Integer) ((Entry<String, Object>) ((Result) row
						.entrySet()).next()).getValue() - 1;
				if ((RotateString((String) temp.getProperty("RunDays"),
						RotateBy)).charAt(day) == '1')
					TrainList.add(temp);// the train nodes are here.

			}
			for (Node i : TrainList) {
				_("Train:" + i.getProperty("Number"));

			}
			tx.success();
		} finally {
			tx.finish();
		}
	}

	/*
	 * LOGIC-> Get the stations which are directly connected(Meaning,there
	 * exists a train which can take you from station1 to that station) to
	 * station 1.Put them in a set...Get the stations which are directly
	 * connected to station 2.Put them in a set.Take intersection of the
	 * sets.This set contains the intermediate stations.
	 */

	@SuppressWarnings({ "unchecked", "deprecation" })
	void GetRouteWithOneIntermediate(String Station1, String Station2, int day) {
		/*
		 * from Station 1 to Station 2.The day value represents the day of the
		 * week you're looking for the train on. Sunday is day 0.Monday is 1 and
		 * so on
		 */

		try {
			tx = db.beginTx();
			String query = "MATCH (:STATION{StationCode:\""
					+ Station1
					+ "\"})<-[rel1:GOES_THROUGH]-(TrainList1)-[rel2:GOES_THROUGH]->(NearByStations:STATION)<-[rel3:GOES_THROUGH]-(TrainList2)-[rel4:GOES_THROUGH]->(:STATION{StationCode:\""
					+ Station2
					+ "\"})  WHERE rel1.DistFromSource < rel2.DistFromSource AND rel3.DistFromSource < rel4.DistFromSource"
					+ " RETURN TrainList1,rel2.DistFromSource-rel1.DistFromSource,NearByStations,TrainList2,"
					+ "rel4.DistFromSource-rel3.DistFromSource,rel2.TimeArr,rel3.TimeDep,rel2.DayOnRoute,rel3.DayOnRoute";
			// TODO:Check for arithmetic operator in return statement.
			Result res = db.execute(query);
			PriorityQueue<IntermediatePath> intermediatePath = new PriorityQueue<IntermediatePath>();
			IntermediatePath temp = null;
			_(res.toString());
			while (res.hasNext()) {
				// TODO:Check if it's getting assigned correctly.
				// because the result may return object in different order.

				@SuppressWarnings("rawtypes")
				Iterator i = res.next().entrySet().iterator();
				temp = new IntermediatePath();
				temp.DistTwo = (Integer) ((Entry<String, Object>) i.next())
						.getValue();
				temp.DistOne = (Integer) ((Entry<String, Object>) i.next())
						.getValue();
				temp.SecondTrain = (Node) ((Entry<String, Object>) i.next())
						.getValue();
				temp.FirstTrain = (Node) ((Entry<String, Object>) i.next())
						.getValue();
				temp.ArrivalTime = (String) ((Entry<String, Object>) i.next())
						.getValue();
				temp.DeptTime = (String) ((Entry<String, Object>) i.next())
						.getValue();
				//this order can be in reverse manner.
				//check this!
				temp.DayOnRouteT1 = (Integer) ((Entry<String, Object>) i.next())
						.getValue();
				temp.DayOnRouteT2 = (Integer) ((Entry<String, Object>) i.next())
						.getValue();
				temp.IntermediateStation = (Node) ((Entry<String, Object>) i
						.next()).getValue();
				intermediatePath.add(temp);

			}

			_("The Alternate Paths are\n=============================================>");
			int count = 0;
			final int NumberOfOptionalRoutesNeeded = 20;
			while (!intermediatePath.isEmpty()) {
				temp = intermediatePath.peek();
				if (temp.isFeasible(day)) {
					count++;
					_("R"
							+ count
							+ ".)First Train:"
							+ temp.FirstTrain.getProperty("Number")
							+ " Distance:"
							+ temp.DistOne
							+ " Intermediate Station:"
							+ temp.IntermediateStation
									.getProperty("StationCode")
							+ " Second Train:"
							+ temp.SecondTrain.getProperty("Number")
							+ " Distance:" + temp.DistTwo
							+ " Total Distance is:"
							+ (temp.DistOne + temp.DistTwo));

					if (count >= NumberOfOptionalRoutesNeeded)
						break;

				}
				intermediatePath.remove(temp);
			}

			tx.success();
		} finally {
			tx.finish();
		}
	}

	// Must be called each time we finish querying.
	void Shutdown() {
		db.shutdown();
	}

	void _(String str) {
		System.out.println(str);
	}
}
