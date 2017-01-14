package org.oliot.accessingApp;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.oliot.accessingApp.utility.QueryModel;
import org.oliot.accessingApp.utility.QueryUtility;

import org.oliot.model.QueryResults;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, JAXBException, InterruptedException
    {
    	ArrayList<String> parameters=new ArrayList<String>();
    	
    	//parameters.add("ObjectEventType");
    	//parameters.add("TransactionEventType");
    	//parameters.add("AggregationEventType");
    	parameters.add("TransformationEventType");
    	//parameters.add("GE_eventTime");
    	//parameters.add("GE_recordTime");
    	parameters.add("EQ_eventID");
    	//parameters.add("EQ_action");
    	//parameters.add("EQ_bizStep");
    	//parameters.add("EQ_readPoint");
    	//parameters.add("EQ_bizLocation");
 //   	parameters.add("EQ_bizTransaction_urn:epcglobal:cbv:btt:po");
 //   	parameters.add("EQ_source_urn:epcglobal:cbv:sdt:possessing_party");
 //   	parameters.add("EQ_destination_urn:epcglobal:cbv:sdt:owning_party");
    	//parameters.add("EQ_transformationID");
  //  	parameters.add("MATCH_epc");
    	
    	QueryUtility.client="143.248.55.183";
    	ArrayList<QueryModel> queries;
    	String url="";
    	
    	
    	queries=QueryUtility.sampleQueryParameter(parameters, 3);
    	url=QueryUtility.generateQueryStrin(queries);
    	
    	
    	int count=3;
    	
    	/*
    	if((count%4)==0){
    		parameters.add("AggregationEventType");
    		queries=QueryUtility.sampleQueryParameter(parameters, count);
    		url=QueryUtility.generateQueryStrin(queries);
		}else if((count%4)==1){
			parameters.add("ObjectEventType");
			queries=QueryUtility.sampleQueryParameter(parameters, count);
			url=QueryUtility.generateQueryStrin(queries);
		}else if((count%4)==2){
			parameters.add("TransactionEventType");
			queries=QueryUtility.sampleQueryParameter(parameters, count);
			url=QueryUtility.generateQueryStrin(queries);
		}else if((count%4)==3){
			parameters.add("TransformationEventType");
			queries=QueryUtility.sampleQueryParameter(parameters, count);
			url=QueryUtility.generateQueryStrin(queries);
		}
    	*/
    	System.out.println( url );
    	
    	QueryResults queryResults=QueryUtility.getQueryResults(url);
    	String jsonString=QueryUtility.toJsonString(queryResults);
    	System.out.println(jsonString);
    	System.out.println( "Program End!" );
    	
    	
    }
    
    
}
