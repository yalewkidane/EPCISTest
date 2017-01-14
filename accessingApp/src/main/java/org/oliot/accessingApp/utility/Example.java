package org.oliot.accessingApp.utility;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.oliot.model.QueryResults;

public class Example {
	   public static void main( String[] args ) throws IOException, JAXBException, InterruptedException
	    {
	    	ArrayList<String> parameters=new ArrayList<String>();
	    	
	    	parameters.add("eventType");
	    	parameters.add("EQ_readPoint");
	    	
	    	ArrayList<QueryModel> queries=QueryUtility.sampleQueryParameter(parameters,3);
	    	
	    	QueryUtility.client="143.248.55.183";
	    	String url=QueryUtility.generateQueryStrin(queries);
	    	System.out.println( url );
	    	
	    	QueryResults queryResults=QueryUtility.getQueryResults(url);
	    	String jsonString=QueryUtility.toJsonString(queryResults);
	    	System.out.println(jsonString);
	    	System.out.println( "Program End!" );
	    	

	        
	    }
}
