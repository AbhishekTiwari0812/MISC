package org.neo4j.neo4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

//creating database from the text file.
public class Database {
	// private static final String TrainStationMapping =
	// "I:/AppDB/trainstationmapping.csv";
	private static final String TrainDetails = "I:/AppDB/trains.csv";
	private static final String TrainStationMapping = "I:/AppDB/sample.txt";
	private static Set<String> TrainNumbers = new HashSet<String>();
	private static Map<String, String> TrainRunDays = new HashMap<String, String>();
	private static Set<String> StationCodes = new HashSet<String>();
	private static ArrayList<String> EachLine = new ArrayList<String>();
	private static final String DB_PATH = "I:/AppDB/new_database";
	private Transaction tx = null;
	private GraphDatabaseService db;
	private Relationship relation;

	void ReadFromFile1() {
		BufferedReader in = null;
		try {
			boolean flag = false;
			in = new BufferedReader(new FileReader(TrainDetails));
			String CurrentLine;
			int days = 6;
			while ((CurrentLine = in.readLine()) != null) {
				if (flag) { // ignoring the first line of the input text file.
					String[] info = CurrentLine.split(",");
					StringBuilder s = new StringBuilder("");
					for (int i = days; i < days + 7; i++) {
						// if place is empty,assumes that the train doesn't run
						// on that day
						try {
							if (info[i].toLowerCase().equals("true"))
								s.append('1');
							else
								s.append('0');

						} catch (ArrayIndexOutOfBoundsException e) {
							s = new StringBuilder("1111111");
							/*
							 * if there's some problem with input, code assumes
							 * that the train runs at all the 7 days.
							 */
							break;
						}
					}
					TrainRunDays.put(info[2], s.toString());

				}
				flag = true;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void ReadFromFile2() {
		BufferedReader in = null;
		try {
			boolean flag = false;
			in = new BufferedReader(new FileReader(TrainStationMapping));
			String CurrentLine;
			while ((CurrentLine = in.readLine()) != null) {
				String[] Info = CurrentLine.split(",");
				// If there's problem with the input text file.
				if (Info.length < 11)
					continue;
				// for ignoring the first line of the input file as it
				// contains only the column headers.
				if (flag) {
					if (!TrainNumbers.contains(Info[0]))
						TrainNumbers.add(Info[0]);
					if (!TrainNumbers.contains(Info[1]))
						StationCodes.add(Info[1]);
					EachLine.add(CurrentLine); // all the info is stored here.

				}
				flag = true;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	void CreateDatabase() {
		this.db = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
		try {
			// set up the database here...!!

			_("Setting up the nodes......");
			tx = db.beginTx();
			for (String i : TrainNumbers) {
				Node n = db.createNode();
				n.setProperty("Number", i);
				if (TrainRunDays.containsKey(i))
					/*
					 * if the train running days is missing code assumes that
					 * the train runs 7 days a week.
					 */
					n.setProperty("RunDays", TrainRunDays.get(i));

				else
					n.setProperty("RunDays", "1111111");
				n.addLabel(NodeType.TRAIN);
			}
			for (String i : StationCodes) {
				Node n = db.createNode();
				n.setProperty("StationCode", i);
				n.addLabel(NodeType.STATION);
			}
			tx.success();
			_("Setting up nodes......DONE  :) ");

		} finally {
			_("finishing the transactions....");
			tx.finish();
			_("finishing  transactions.... DONE :)");
		}

	}

	@SuppressWarnings("deprecation")
	void SetRelations() {
		_("Setting up the relations now.");
		try {
			tx = db.beginTx();
			for (String i : EachLine) {
				String[] info = i.split(",");
				String query = "MATCH (n:TRAIN) WHERE n.Number=\"" + info[0]
						+ "\" RETURN n";
				Result r = db.execute(query);
				// Alternate way to do this with one line of query.Properties
				// are still to be set
				/*
				 * String query = "MATCH (train:TRAIN{Number:\"" + info[0] +
				 * "\"}),(station:STATION{StationCode:\"" + info[1] +
				 * "\"}) CREATE (train-[:GOES_THROUGH{StopNumber:" + info[3] +
				 * "}]->station)"; // can be syntax error here.
				 */

				// db.execute(query);
				Node Train = (Node) r.next().entrySet().iterator().next()
						.getValue();
				r = db.execute("MATCH (n:STATION) WHERE n.StationCode=\""
						+ info[1] + "\" RETURN n");
				Node Station = (Node) r.next().entrySet().iterator().next()
						.getValue();
				relation = Train.createRelationshipTo(Station,
						NodeRelationshipType.GOES_THROUGH);
				// relation.setProperty("StopNumber",
				try {
					// Integer.parseInt(info[3])); we don't need this
					// information
					relation.setProperty("TimeArr", info[6]);
					relation.setProperty("TimeDep", info[7]);
					relation.setProperty("DayOnRoute",
							Integer.parseInt(info[10]));
					relation.setProperty("DistFromSource",
							Integer.parseInt(info[9].split(" ")[0]));
				} catch (Exception e) {
					_("=========>THERE IS A TYPO OR MISSING DATA IN THE INPUT FILE.");
					relation.setProperty("TimeArr", "00:30");
					relation.setProperty("DayOnRoute", 1);
					relation.setProperty("TimeDep", "03:20");
					relation.setProperty("DistFromSource", 1000);
				}

			}
			_("Done setting up the relations");
			tx.success();
			_("Transaction successful");
		} finally {
			_("Now finishing the transaction.Takes some time");
			tx.finish();
			_("Transaction finished :) \nDatabase is all set.\nGO NUTS :P ");
		}
	}

	void Shutdown() {
		_("Shutting down the database");
		db.shutdown();
		_("THE DATABASE HAS BEEN SHUT DOWN");
	}

	void _(String str) {

		System.out.println(str);
	}

}