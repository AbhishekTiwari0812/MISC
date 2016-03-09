package org.neo4j.neo4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

//creating database from the text file.
public class Test {

	private static final String SourceTextFilePath = "I:/AppDB/trainstationmapping.csv";
	// private static final String SourceTextFilePath = "I:/AppDB/sample.txt";
	private static Set<String> TrainNumbers = new HashSet<String>();
	private static Set<String> StationCodes = new HashSet<String>();
	private static ArrayList<String> EachLine = new ArrayList<String>();

	private static final String DB_PATH = "I:/AppDB/new_database";
	private Transaction tx = null;
	private GraphDatabaseService db;
	private Relationship relation;

	void ReadFromFile() {
		BufferedReader in = null;
		try {
			boolean flag = false;
			in = new BufferedReader(new FileReader(SourceTextFilePath));
			String CurrentLine;
			while ((CurrentLine = in.readLine()) != null) {
				String[] Info = CurrentLine.split(",");
				if (!TrainNumbers.contains(Info[0]))
					TrainNumbers.add(Info[0]);
				if (!TrainNumbers.contains(Info[1]))
					StationCodes.add(Info[1]);
				if (flag) // for ignoring the first line of the input file as it
							// contains only the column headers.
					EachLine.add(CurrentLine); // all the info is stored here.
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

				db.execute(query);
				Node Train = (Node) r.next().entrySet().iterator().next()
						.getValue();
				r = db.execute("MATCH (n:STATION) WHERE n.StationCode=\""
						+ info[1] + "\" RETURN n");
				Node Station = (Node) r.next().entrySet().iterator().next()
						.getValue();
				relation = Train.createRelationshipTo(Station,
						NodeRelationshipType.GOES_THROUGH);
				// relation.setProperty("StopNumber",
				// Integer.parseInt(info[3])); we don't need this information

				try {
					relation.setProperty("DistFromSource",
							Integer.parseInt(info[9].split(" ")[0]));
				} catch (NumberFormatException e) {
					_("=========>THERE IS A TYPO OR MISSING DATA IN THE INPUT FILE.");
					relation.setProperty("DistFromSource", 10000); // this is a
																	// bug but
																	// what can
																	// we do :P
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