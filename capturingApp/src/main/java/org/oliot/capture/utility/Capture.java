package org.oliot.capture.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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
import org.oliot.model.TransactionEventType;
import org.oliot.model.TransformationEventType;
import org.oliot.model.extensionType;

public class Capture {
	
	public String simpleObjectEventmarshal(){
		StringWriter stWr=new StringWriter();
		EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
	   	  ObjectEventType objectEvent=sampleEpcisEvents.getObjectEventTypeSample();
	   	  JAXBElement element=new JAXBElement(new QName("ObjectEvent"),ObjectEventType.class,objectEvent);
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.mysite2.com");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				System.out.println(stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stWr.toString();
	}
	
	public String sampleObjectEventMarshal(int count){
		StringWriter stWr=new StringWriter();
		EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
	   	  ObjectEventType objectEvent=sampleEpcisEvents.getObjectEventTypeSample(count);
	   	  JAXBElement element=new JAXBElement(new QName("ObjectEvent"),ObjectEventType.class,objectEvent);
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stWr.toString();
	}
	
	public String eventCapture(String urlStr, String body){
		String resp="";
		try {
			byte[] postData       = body.getBytes( StandardCharsets.UTF_8 );
			int    postDataLength = postData.length;
			URL url_ = new URL(urlStr);
			HttpURLConnection conn= (HttpURLConnection) url_.openConnection();           
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/xml"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setUseCaches( false );
			conn.getOutputStream().write(postData);
//			Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//			System.out.println("Response :"+ in);
//	        for (int c; (c = in.read()) >= 0;)
//	            System.out.print((char)c);
//			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String readline;
			while ((readline = br.readLine()) != null) {

				resp = resp + readline;

			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resp;
	}
	
	public String sampleTransactionEventMarshal(int count){
		StringWriter stWr=new StringWriter();
		EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
		TransactionEventType transactionEvent=sampleEpcisEvents.getTransactionEventTypeSample(count);
	   	  JAXBElement element=new JAXBElement(new QName("TransactionEvent"),TransactionEventType.class,transactionEvent);
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stWr.toString();
	}
	
	public String sampleAggregationEventMarshal(int count){
		StringWriter stWr=new StringWriter();
		EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
		AggregationEventType ggregationEvent=sampleEpcisEvents.getAggregationEventTypeSample(count);
	   	  JAXBElement element=new JAXBElement(new QName("AggregationEvent"),AggregationEventType.class,ggregationEvent);
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stWr.toString();
	}
	
	public String sampleTransformationEventMarshal(int count){
		StringWriter stWr=new StringWriter();
		EPCISDocumentType epcisDocument=makeBaseResultDocument();
		   
		   extensionType extension = new extensionType();
		  
		  TransformationEventType transformationEvent=sampleEpcisEvents.getTransformationEventTypeSample(count);
		  
		  extension.setTransformationEvent(transformationEvent);
		
		  JAXBElement element=new JAXBElement(new QName("extension"),extensionType.class,extension);
	   	  epcisDocument.getEPCISBody().getEventList().getObjectEventOrAggregationEventOrQuantityEvent().add(element);
	   	  	 try {
				JAXBContext jaxbContext = JAXBContext.newInstance(EPCISDocumentType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				 
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.w3.org/2001/XMLSchema-instance");
				//jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "urn:epcglobal:epcis-query:xsd:1");
				
				jaxbMarshaller.marshal(epcisDocument, stWr);
				
				
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return stWr.toString();
	}
	
	public EPCISDocumentType makeBaseResultDocument() {
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
