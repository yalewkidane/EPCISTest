package org.oliot.accessingApp.utility;

import java.util.ArrayList;

public class QueryModel {
	
	String queryType;
	ArrayList<String> values =new ArrayList<String>();
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
	}
	
	
	

}
