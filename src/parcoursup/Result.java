package parcoursup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Result {

	private Map<RankedElement, List<RankedElement>> result;
	private int nbrRound;

	public Result(List<RankedElement> bindingElements, List<RankedElement> bindedElements, int round) {
		this.result = new HashMap<RankedElement, List<RankedElement>>();
		this.nbrRound = round;

		for (RankedElement bindedElement : bindedElements) {
			result.put(bindedElement, bindedElement.getConnected());
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
