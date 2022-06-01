package parcoursup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parsing.Data;
import parsing.RankedData;

public class Main {

	public static final String DATA_PATH = "data/data3.json";;

	public static void main(String[] args) {
		List<RankedElement> schools = new ArrayList<RankedElement>();
		List<RankedElement> students = new ArrayList<RankedElement>();
		// get informations

		Data dataNonOrdonned = Data.loadDataFromJson();
		RankedData data = new RankedData(dataNonOrdonned);
		Boolean studentBinding = dataNonOrdonned.getStudentChoose();
		schools = data.getSchools();
		students = data.getStudents();

//		studentBinding = setBindedInformations(schools, students);
		// start mariage
		Result result = null;
		if (studentBinding) {
			result = stableMariage(schools, students);
		} else {
			result = stableMariage(students, schools);
		}
		// return mariage's result and number of turn
		result.show();

	}

	/**
	 * Execute the stableMariage Algorithm
	 * 
	 * @param bindingRankedElement List of RankedElement who bind other RankedElement
	 * @param bindedRankedElement  List of RankedElement who are binding by other RankedElement, they
	 *                       "choose"
	 * @return result of the stable mariage
	 */
	public static Result stableMariage(List<RankedElement> bindingRankedElement, List<RankedElement> bindedRankedElement) {
		int round = 0;
		while (!everyoneIsBinding(bindingRankedElement, bindedRankedElement)) {
			round++;
			/** put every bindingRankedElement under a balcony */
			for (RankedElement RankedElement : bindingRankedElement) {
				if (!RankedElement.haveTheGoodNumberOfBind()) {
					RankedElement favorit = RankedElement.getFavorite();
					favorit.beSerenadedBy(RankedElement);
				}
			}
			/* choose which RankedElement can go for a walk from the balcony */
			for (RankedElement RankedElement : bindedRankedElement) {
				RankedElement.eliminateWorst();
			}

		}
		return new Result(bindingRankedElement, bindedRankedElement, round);
	}

	private static boolean everyoneIsBinding(List<RankedElement> RankedElements, List<RankedElement> RankedElements2) {
		boolean fullBinding = true;
		boolean fullBinded = true;
		for (RankedElement RankedElement : RankedElements) {
			if (!RankedElement.haveTheGoodNumberOfBind()) {
				fullBinding = false;
			}
		}
		for (RankedElement RankedElement : RankedElements2) {
			if (!RankedElement.haveTheGoodNumberOfBind()) {
				fullBinded = false;
			}
		}
		return (fullBinded || fullBinding);
	}

	private static Boolean setBindedInformations(List<RankedElement> schools, List<RankedElement> students) {
		School school1 = new School("n7", 2);
		School school2 = new School("ENSC", 3);

		Student student1 = new Student("Armand");
		student1.setRank(Map.of(1, school1, 2, school2));
		Student student2 = new Student("Anaïs");
		student2.setRank(Map.of(1, school1, 2, school2));
		Student student3 = new Student("George");
		student3.setRank(Map.of(1, school1, 2, school2));
		Student student4 = new Student("Artur");
		student4.setRank(Map.of(1, school1, 2, school2));
		Student student5 = new Student("Joséphine");
		student5.setRank(Map.of(2, school1, 1, school2));
		Student student6 = new Student("Stéphanie");
		student6.setRank(Map.of(2, school1, 1, school2));
		Student student7 = new Student("Victor");
		student7.setRank(Map.of(2, school1, 1, school2));

		school1.setRank(
				Map.of(1, student1, 2, student2, 3, student3, 4, student4, 5, student5, 6, student6, 7, student7));
		school2.setRank(
				Map.of(2, student1, 1, student2, 3, student3, 4, student4, 5, student5, 6, student6, 7, student7));

		schools.add(school1);
		schools.add(school2);
		students.add(student1);
		students.add(student2);
		students.add(student3);
		students.add(student4);
		students.add(student5);
		students.add(student6);
		students.add(student7);

		return true;
	}
}
