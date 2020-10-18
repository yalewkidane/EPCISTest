package org.oliot.accessingApp.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.oliot.model.EPCISQueryBodyType;
import org.oliot.model.EPCISQueryDocumentType;
import org.oliot.model.QueryResults;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueryUtility {
	
	public static String client="143.xx.xx.xxx";
	public static String generateQueryStrin(ArrayList<QueryModel> queryList){
		String url="http://"+client+":8080/epcis/Service/Poll/SimpleEventQuery?";
		
		for(int i=0; i<queryList.size(); i++){
			if(i==0){
				url+=queryList.get(i).getQueryType()+"=";
				ArrayList<String> valueList=queryList.get(i).values;
				for(int j=0; j<valueList.size(); j++){
					if(j==0){
						url+=valueList.get(j);
					}else{
						url+=","+valueList.get(j);
					}
				}
			}else{
				url+="&"+queryList.get(i).getQueryType()+"=";
				ArrayList<String> valueList=queryList.get(i).values;
				for(int j=0; j<valueList.size(); j++){
					if(j==0){
						url+=valueList.get(j);
					}else{
						url+=","+valueList.get(j);
					}
				}
			}
			
		}
		return url;
	}
	
	public static QueryResults getQueryResults(String urlStr)throws IOException, JAXBException,
	InterruptedException{
		   QueryResults queryResults=new QueryResults();
			
		   try {

				URL url_ = new URL(urlStr);
				HttpURLConnection conn = (HttpURLConnection) url_.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

				String readline;
				String output = "";
				while ((readline = br.readLine()) != null) {

					output = output + readline;

				}
				JAXBContext jAXBContext = JAXBContext
						.newInstance(EPCISQueryDocumentType.class);
				@SuppressWarnings("unused")
				Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();

				Source source = new StreamSource(new StringReader(output));
				EPCISQueryDocumentType ePCISQueryDocumentType = JAXB.unmarshal(
						source, EPCISQueryDocumentType.class);

				EPCISQueryBodyType epcisBody = ePCISQueryDocumentType
						.getEPCISBody();
				
				if (epcisBody != null) {
					queryResults = epcisBody.getQueryResults();
				}
				else{
					System.out.println("Empty  query result");
				}
				conn.disconnect();

			} catch (MalformedURLException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}
				
		
		return queryResults;
	}
	
	
	public static String toJsonString(Object obj){
		ObjectMapper mapper = new ObjectMapper();
	   	String jsonInString="";
	   	  try {
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   	
	   	return jsonInString;
	}
	
	
	public static ArrayList<QueryModel> sampleQueryParameter(){
    	ArrayList<QueryModel> queries= new ArrayList<QueryModel>();
    	
    	QueryModel eventType1=new QueryModel();
    	eventType1.setQueryType("eventType");
    	eventType1.getValues().add("ObjectEvent");
    	queries.add(eventType1);
    	
    	QueryModel eventType2=new QueryModel();
    	eventType2.setQueryType("EQ_readPoint");
    	eventType2.getValues().add("ReadPoint_object");
    	queries.add(eventType2);
    	
    	return queries;
	}
	
	public static ArrayList<QueryModel> sampleQueryParameter(ArrayList<String> parameters,  int count){
    	ArrayList<QueryModel> queries= new ArrayList<QueryModel>();
    	/**
    	 * GE_eventTime
    	 * LT_eventTime	GE_recordTime	LT_recordTime	EQ_action	EQ_bizStep
    	 * EQ_disposition	EQ_readPoint	WD_readPoint	EQ_bizLocation	WD_bizLocation	
    	 * EQ_bizTransaction_type	EQ_source_type	EQ_destination_type	EQ_transformationID
    	 * MATCH_epc	MATCH_parentID	MATCH_inputEPC	MATCH_outputEPC	MATCH_anyEPC	
    	 * MATCH_epcClass	MATCH_inputEPCClass		MATCH_outputEPCClass		MATCH_anyEPCClass
    	 * EQ_quantity	GT_quantity	GE_quantity	LT_quantity	LE_quantity	EQ_fieldname	EQ_fieldname
    	 * GT_fieldname	GE_fieldname LT_fieldname LE_fieldname	EQ_ILMD_fieldname	
    	 * EQ_ILMD_fieldname	GT_ILMD_fieldname	GE_ILMD_fieldname	LT_ILMD_fieldname	
    	 * LE_ILMD_fieldname	EQ_INNER_fieldname	EQ_INNER_fieldname	GT_INNER_fieldname	
    	 * GE_INNER_fieldname	LT_INNER_fieldname	LE_INNER_fieldname	EQ_INNER_ILMD_fieldname	
    	 * EQ_INNER_ILMD_fieldname	GT_INNER_ ILMD_fieldname	GE_INNER_ ILMD_fieldname
    	 * LT_INNER_ ILMD_fieldname	LE_INNER_ ILMD_fieldname	EXISTS_fieldname
    	 * EXISTS_INNER_fieldname	EXISTS_ILMD_fieldname	EXISTS_INNER_ILMD_fieldname
    	 * HASATTR_fieldname	EQATTR_fieldname _attrname	EQ_eventID	EXISTS_errorDeclaration
    	 * GE_errorDeclarationTime	LT_erroorDeclarationTime	EQ_errorReason	EQ_correctiveEventID
    	 * EQ_ERROR_DECLARATION_fieldname	EQ_ERROR_DECLARATION_fieldname	GT_ERROR_DECLARATION_fieldname	
    	 * GE_ERROR_DECLARATION_fieldname	LT_ERROR_DECLARATION_fieldname	LE_ERROR_DECLARATION_fieldname
    	 * EQ_INNER_ERROR_DECLARATION_fieldname	EQ_INNER_ERROR_DECLARATION_fieldname
    	 * GT_INNER_ERROR_DECLARATION_fieldname	GE_INNER_ERROR_DECLARATION_fieldname
    	 * LT_INNER_ERROR_DECLARATION_fieldname	LE_INNER_ERROR_DECLARATION_fieldname	
    	 * EXISTS_ERROR_DECLARATION_fieldname	EXISTS_INNER_ERROR_DECLARATION_fieldname	orderBy
    	 * orderDirection	eventCountLimit	maxEventCount
    	 */
    	for(int i=0; i<parameters.size(); i++){
    		String parametr=parameters.get(i);
    		if(parametr.equals("ObjectEventType")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("eventType");
    	    	eventType.getValues().add("ObjectEvent");
    	    	queries.add(eventType);
    		}else if(parametr.equals("TransactionEventType")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("eventType");
    	    	eventType.getValues().add("TransactionEvent");
    	    	queries.add(eventType);
    		}else if(parametr.equals("AggregationEventType")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("eventType");
    	    	eventType.getValues().add("AggregationEvent");
    	    	queries.add(eventType);
    		}else if(parametr.equals("TransformationEventType")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("eventType");
    	    	eventType.getValues().add("TransformationEvent");
    	    	queries.add(eventType);
    		}else if(parametr.equals("GE_eventTime")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("GE_eventTime");
    	    	eventType.getValues().add("2004-04-04T20:33:31.116-06:00");
    	    	queries.add(eventType);
    		}else if(parametr.equals("GE_recordTime")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("GE_recordTime");
    	    	eventType.getValues().add("2004-04-04T20:33:31.116-06:00");
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_eventID")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_eventID");
    	    	eventType.getValues().add("5722d7e1deab322596705"+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_action")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_action");
    	    	eventType.getValues().add("OBSERVE");
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_bizStep")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_bizStep");
    	    	eventType.getValues().add("urn:epcglobal:cbv:bizstep:receiving");
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_readPoint")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_readPoint");
    	    	eventType.getValues().add("urn:epc:id:sgln:0012345.11111."+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_bizLocation")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_bizLocation");
    	    	eventType.getValues().add("urn:epc:id:sgln:0012345.11111."+count);
    	    	queries.add(eventType);			
    		}else if(parametr.equals("EQ_bizTransaction_urn:epcglobal:cbv:btt:po")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_bizTransaction_urn:epcglobal:cbv:btt:po");
    	    	eventType.getValues().add("http://transaction.acme.com/po/"+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_source_urn:epcglobal:cbv:sdt:possessing_party")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_source_urn:epcglobal:cbv:sdt:possessing_party");
    	    	eventType.getValues().add("urn:epc:id:sgln:4012345.00001."+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_destination_urn:epcglobal:cbv:sdt:owning_party")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_destination_urn:epcglobal:cbv:sdt:owning_party");
    	    	eventType.getValues().add("urn:epc:id:sgln:0614141.00001."+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("EQ_transformationID")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("EQ_transformationID");
    	    	eventType.getValues().add("transformationID_"+count);
    	    	queries.add(eventType);
    		}else if(parametr.equals("MATCH_epc")){
    			QueryModel eventType=new QueryModel();
    	    	eventType.setQueryType("MATCH_epc");
    	    	eventType.getValues().add("urn:epc:id:sgtin:0614141.107346."+count);
    	    	queries.add(eventType);
    		}
//    		else if(parametr.equals("EQ_fieldname")){
//    			QueryModel eventType=new QueryModel();
//    	    	eventType.setQueryType("EQ_fieldname");
//    	    	eventType.getValues().add("example0");
//    	    	queries.add(eventType);
//    		}else if(parametr.equals("EQ_ILMD_fieldname")){
//    			QueryModel eventType=new QueryModel();
//    	    	eventType.setQueryType("EQ_ILMD_fieldname");
//    	    	eventType.getValues().add("ReadPoint_object");
//    	    	queries.add(eventType);
//    		}else if(parametr.equals("EQ_INNER_fieldname")){
//    			QueryModel eventType=new QueryModel();
//    	    	eventType.setQueryType("EQ_INNER_fieldname");
//    	    	eventType.getValues().add("ReadPoint_object");
//    	    	queries.add(eventType);
//    		}
    	}
    	
    	return queries;
	}

}
