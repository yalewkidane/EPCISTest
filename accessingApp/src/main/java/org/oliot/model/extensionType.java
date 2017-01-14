package org.oliot.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "extensionType", propOrder = { "TransformationEvent" })
public class extensionType {
	
	private TransformationEventType TransformationEvent;

	public TransformationEventType getTransformationEvent() {
		return TransformationEvent;
	}

	public void setTransformationEvent(TransformationEventType transformationEvent) {
		TransformationEvent = transformationEvent;
	}

	

	
	

	

}
