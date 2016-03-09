package org.neo4j.neo4j;

import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.RelationshipType;

enum NodeRelationshipType implements RelationshipType {
	GOES_THROUGH, // train goes through a station
	EN_ROUTE; // station is in route of the train.

}

enum NodeType implements Label {

	TRAIN, STATION;
}