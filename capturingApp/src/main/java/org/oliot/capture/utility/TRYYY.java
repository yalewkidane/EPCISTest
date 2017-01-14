package org.oliot.capture.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.oliot.model.AggregationEventType;
import org.oliot.model.EPCISBodyType;
import org.oliot.model.EPCISDocumentType;
import org.oliot.model.EPCISHeaderType;
import org.oliot.model.EventListType;
import org.oliot.model.ObjectEventType;
import org.oliot.model.TransformationEventType;
import org.oliot.model.extensionType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class TRYYY {
	   public static void main( String[] args )
	    {
		  EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
	   	  //ObjectEventType objectEvent=sampleEpcisEvents.getObjectEventTypeSample();
	   	  //AggregationEventType aggregationEvent=sampleEpcisEvents.getAggregationEventTypeSample(24);
		 // TransformationEvent
		 // List<TransformationEventType> extension=new ArrayList<TransformationEventType>();
		  
		  extensionType extension = new extensionType();
		  
		  TransformationEventType transformationEvent=sampleEpcisEvents.getTransformationEventTypeSample(2);
		  
		  extension.setTransformationEvent(transformationEvent);
	   	  //JAXBElement element=new JAXBElement(new QName("ObjectEvent"),ObjectEventType.class,objectEvent);
	   	  JAXBElement element=new JAXBElement(new QName("extension"),extensionType.class,extension);
	   	System.out.println( "Checking1" );
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	System.out.println( "Checking2" );
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.mysite2.com");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				StringWriter stWr=new StringWriter();
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				System.out.println(stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println( "Program End!" );
	    }
	   
	   
	   public static void postEvent(String urlStr){
		   URL url_;
		try {
			url_ = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url_.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");

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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	   }
	   
	   
	   
		public static EPCISDocumentType makeBaseResultDocument() {
			// Make Base Result Document
			EPCISDocumentType epcisDocument = new EPCISDocumentType();
			epcisDocument.setSchemaVersion(new BigDecimal(1.2, MathContext.DECIMAL32));
			   
		   GregorianCalendar gcreationDate=new GregorianCalendar();		   
		   XMLGregorianCalendar creationDate;
		   try {
			creationDate=DatatypeFactory.newInstance().newXMLGregorianCalendar(gcreationDate);
			epcisDocument.setCreationDate(creationDate);
		   } catch (DatatypeConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		   }
		   EPCISHeaderType epcisHeader =new EPCISHeaderType();
		   //epcisDocument.setEPCISHeader(epcisHeader);
			 
			EPCISBodyType epcisBody = new EPCISBodyType();
			EventListType eventListType = new EventListType();
			
			List<Object> eventObjects = new ArrayList<Object>();
			eventListType.setObjectEventOrAggregationEventOrQuantityEvent(eventObjects);
			
			eventListType.setObjectEventOrAggregationEventOrQuantityEvent(eventObjects);
			epcisBody.setEventList(eventListType);
			epcisDocument.setEPCISBody(epcisBody);
			return epcisDocument;
		}
}
