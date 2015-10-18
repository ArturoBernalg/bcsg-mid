package com.bcsg.utils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvDeserializer;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

/**
 * @author Arturo Bernal
 * 
 */
public class Deserialization {

	private CsvConfiguration config;
	private String resource;

	public Deserialization() {
		config = new CsvConfiguration();
		config.setLineFilter(new HeaderAndFooterFilter(0, false, true));
		config.setFieldDelimiter((char) 44); 
	}
	/**
	 * Get the list of Object from CVS File 
	 * @param  classtoDes   
	 * @return the value listDes
	 */
	public List<Object> start(Object classtoDes) {
		List<Object> listDes = new ArrayList<Object>();
		// header of size 0, footer 0, store the filtered lines
		CsvDeserializer deserializer = CsvIOFactory.createFactory(config, classtoDes.getClass()).createDeserializer();
		
		deserializer.open(createFileReader());
		
		while (deserializer.hasNext()) {
			Object cls = deserializer.next();
			listDes.add(cls);
		}
		deserializer.close(true);
		return listDes;
	}

	private Reader createFileReader() {
		//return new InputStreamReader(this.getClass().getResourceAsStream(".."+File.separator+"resources"+File.separator+this.getResource()));
		return new InputStreamReader(this.getClass().getResourceAsStream(resource));
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
	
	

}
