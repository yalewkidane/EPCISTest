package org.oliot.capture.utility;

public class Example {
	public static void main( String[] args )
    {
		Capture capture=new Capture();
    	String xmlString=capture.sampleObjectEventMarshal(3);
    	System.out.println(xmlString);
    	
    }
}
