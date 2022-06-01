package parsing;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import jdk.jfr.StackTrace;
import parcoursup.Element;
import parcoursup.Main;

public class Data {

	/**
	 * List of non ranked schools
	 */
	private List<Element> schools;

	/**
	 * List of non ranked students
	 */
	private List<Element> students;

	/**
	 * The double entry list of correspondence between schools and students
	 */
	private List<List<List<Integer>>> data;
	
	/**
	 * The boolean that correspond to the Element who choose the bind
	 */
	private Boolean studentChoose;

	/**
	 * Load schools, students and data from json 
	 * 
	 * @return the data
	 */
	public static Data loadDataFromJson() {
		// Extracts schools and students names / capacities from data.json file
		Data data = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			data = mapper.readValue(Main.class.getClassLoader().getResourceAsStream(Main.DATA_PATH), Data.class);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unable to read the json file");
			System.exit(1);
		}
		return data;
	}

	public List<List<List<Integer>>> getData() {
		//FIXME warning make a copy
		return data;
	}

	public List<Element> getStudents(){
		return students;
	}
	
	public List<Element> getSchools(){
		return schools;
	}
	
	public boolean getStudentChoose() {
		return studentChoose;
	}

}
