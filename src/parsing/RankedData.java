package parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parcoursup.Element;
import parcoursup.RankedElement;

public class RankedData {

	/**
	 * List of non ranked schools
	 */
	private List<RankedElement> schools;

	/**
	 * List of non ranked students
	 */
	private List<RankedElement> students;
	
	/**
	 * Data with rank and non ranked Element
	 */
	private Data data;

	public RankedData(Data data) {
		this.data = data;
		students = new ArrayList<>();
		schools = new ArrayList<>();
		ordered();
	}
	
	private void ordered() {
		int i = 0;
		// Create all the rankedElement
		for (Element school : data.getSchools()) {
			schools.add(new RankedElement(school));
		}
		for (Element student : data.getStudents()) {
			students.add(new RankedElement(student));
		}

		// Assign them their rank
		for (RankedElement school : schools) {
			school.setRank(getElementRank(i, false, students));
			i++;
		}		
		i = 0;
		for (RankedElement student : students) {
			student.setRank(getElementRank(i, true, schools));
			i++;
		}
	}

	/**
	 * Find the rank of all the Element with the Data
	 * @param i row or column in the data table
	 * @param first true, if the Element is on the column, false if it is on the row
	 * @param elements 
	 * @return
	 */
	private Map<Integer, RankedElement> getElementRank(int i, boolean first, List<RankedElement> elements) {
		HashMap<Integer, RankedElement> rank = new HashMap<Integer, RankedElement>();
		int bindedIndice = 0;
		if (first) {
			for (List<List<Integer>> list : data.getData()) {
				// récupère chaque rank indiqué dans le tableau data
				rank.put(list.get(i).get(1), 
						elements.get(bindedIndice));
				bindedIndice++;
			}
		} else {
			for (List<Integer> list : data.getData().get(i)) {
				// récupère chaque rank indiqué dans le tableau data
				rank.put(list.get(0), elements.get(bindedIndice));
				bindedIndice++;
			}
		}

		return rank;
	}
	
	public List<RankedElement> getSchools() {
		return schools;
	}

	public List<RankedElement> getStudents() {
		return students;
	}
	
	
}
