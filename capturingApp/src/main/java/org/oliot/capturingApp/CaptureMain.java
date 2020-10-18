package org.oliot.capturingApp;

import org.oliot.capture.utility.Capture;

/**
 * Hello world!
 *
 */
public class CaptureMain 
{
	//private String client="143.xxx.xx.183:8080";
	private String client="localhost:8080";
    public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public static void main( String[] args )
    {
    	CaptureMain capmain=new CaptureMain();
    	capmain.sampleObjectEventCapture(38);
    	//capmain.sampleAggregationEventCapture(41);
    	//capmain.sampleTransactionEventCapture(50);
    	//capmain.sampleTransformationEventCapture(60);
    	//capmain.sampeEventCapture(20);
    }
    
    void simpleObjectEventCapture(){
    	String url="http://"+client+"/epcis/Service/EventCapture";
    	String xmlString="";
    	String response="";
    	Capture capture=new Capture();
    	xmlString=capture.simpleObjectEventmarshal();
    	long startTime = System.currentTimeMillis();
    	response=capture.eventCapture(url, xmlString);
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	System.out.println( "url : " +url);
    	System.out.println( "Method : " +"POST");
    	System.out.println( "Response : " +response);
    	System.out.println( "Elapsed Time : " +elapsedTime +"ms");
        System.out.println( "Program End!" );
    }
    void sampleObjectEventCapture(int count){
    	String url="http://"+client+"/epcis/Service/EventCapture";
    	String xmlString="";
    	String response="";
    	Capture capture=new Capture();
    	xmlString=capture.sampleObjectEventMarshal(count);
    	System.out.println(xmlString);
    	long startTime = System.currentTimeMillis();
    	response=capture.eventCapture(url, xmlString);
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	System.out.println( "url : " +url);
    	System.out.println( "Method : " +"POST");
    	System.out.println( "Response : " +response);
    	System.out.println( "Elapsed Time : " +elapsedTime +"ms");
        System.out.println( "Program End!" );
    }
    
    void sampleTransactionEventCapture(int count){
    	String url="http://"+client+"/epcis/Service/EventCapture";
    	String xmlString="";
    	String response="";
    	Capture capture=new Capture();
    	xmlString=capture.sampleTransactionEventMarshal(count);
    	long startTime = System.currentTimeMillis();
    	response=capture.eventCapture(url, xmlString);
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	System.out.println( "url : " +url);
    	System.out.println( "Method : " +"POST");
    	System.out.println( "Response : " +response);
    	System.out.println( "Elapsed Time : " +elapsedTime +"ms");
        System.out.println( "Program End!" );
    }
    
    void sampleAggregationEventCapture(int count){
    	String url="http://"+client+"/epcis/Service/EventCapture";
    	String xmlString="";
    	String response="";
    	Capture capture=new Capture();
    	xmlString=capture.sampleAggregationEventMarshal(count);
    	long startTime = System.currentTimeMillis();
    	response=capture.eventCapture(url, xmlString);
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	System.out.println( "url : " +url);
    	System.out.println( "Method : " +"POST");
    	System.out.println( "Response : " +response);
    	System.out.println( "Elapsed Time : " +elapsedTime +"ms");
        System.out.println( "Program End!" );
    }
    
    void sampleTransformationEventCapture(int count){
    	String url="http://"+client+"/epcis/Service/EventCapture";
    	String xmlString="";
    	String response="";
    	Capture capture=new Capture();
    	xmlString=capture.sampleTransformationEventMarshal(count);
    	long startTime = System.currentTimeMillis();
    	response=capture.eventCapture(url, xmlString);
    	long elapsedTime = System.currentTimeMillis() - startTime;
    	System.out.println( "url : " +url);
    	System.out.println( "Method : " +"POST");
    	System.out.println( "Response : " +response);
    	System.out.println( "Elapsed Time : " +elapsedTime +"ms");
        System.out.println( "Program End!" );
    }
    
    void sampeEventCapture( int max){
    	CaptureMain cap=new CaptureMain();
    	for(int i=0; i<max;){
    		if((i%4)==0){
    			
    			cap.sampleAggregationEventCapture(i);
    			System.out.println( "AggregationEvent Capture count: " +i);
    			i++;
    		}else if((i%4)==1){
    			cap.sampleObjectEventCapture(i);
    			System.out.println( "ObjectEvent Capture count: " +i);
    			i++;
    		}else if((i%4)==2){
    			cap.sampleTransactionEventCapture(i);
    			System.out.println( "TransactionEvent Capture count: " +i);
    			i++;
    		}else if((i%4)==3){
    			cap.sampleTransformationEventCapture(i);
    			System.out.println( "TransformationEvent Capture count: " +i);
    			i++;
    		}    		
    		
    	}
    }
    
    
}
