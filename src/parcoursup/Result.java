package parcoursup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Result {

	private Map<RankedElement, List<RankedElement>> result;
	private int nbrRound;

	public Result(List<RankedElement> bindingElements, List<RankedElement> bindedElements, int round) {
		this.result = new HashMap<RankedElement, List<RankedElement>>();
		this.nbrRound = round;

		for (RankedElement bindedElement : bindedElements) {
			result.put(bindedElement, bindedElement.getConnected());
		}
		
		addAloneElement(bindingElements);

	}

	/**
	 * Add all Element that are not binded to the result
	 * @param bindingElements
	 */
	private void addAloneElement(List<RankedElement> bindingElements) {
		//find bindedElement who are not in the result
		Set<RankedElement> allTheBindedElement = new HashSet<RankedElement>();
		for (RankedElement rankedElement : result.keySet()) {
			allTheBindedElement.addAll(result.get(rankedElement));
		}
		List<RankedElement> alone = new ArrayList<RankedElement>();
		for (RankedElement rankedElement : bindingElements) {
			if (!allTheBindedElement.contains(rankedElement)) {
				alone.add(rankedElement);
			}
		}
		if (alone.size() != 0) {
			result.put(new RankedElement("**Alone**", 0), alone);
		}		
	}

	public void show() {
		System.out.println("turn : " + nbrRound);
		System.out.println("results : ");
		System.out.println("===================================================");
		for (Entry<RankedElement, List<RankedElement>> elementBinding : result.entrySet()) {
			System.out.print(elementBinding.getKey() + " |");

			for (RankedElement elementBinded : elementBinding.getValue()) {
				System.out.print("| " + elementBinded + " ");
			}
			System.out.println();
			System.out.println("---------------------------------------------------");
		}
	}

}
