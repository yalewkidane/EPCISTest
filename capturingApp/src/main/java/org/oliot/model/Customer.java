
package org.oliot.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Customer", namespace="urn:epcglobal:epcis-query:xsd:1")
//@XmlType(name="Customer", namespace="urn:epcglobal:epcis-query:xsd:1")
public class Customer {

	@XmlAttribute(name="id")
	int id;
	@XmlElement(name="name")
	String name;
	@XmlElement(name="age")
	int age;


	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

}