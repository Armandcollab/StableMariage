package parcoursup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RankedElement extends Element{

	/* List of ElementRankeds order by preference */
	protected Map<Integer, RankedElement> rank;

	/*
	 * Number in the rank List of the first Favorite ElementRanked who have'nt already say
	 * NO
	 */
	protected int firstFavorite;

	/* ElementRankeds bind with the actual ElementRanked */
	protected List<RankedElement> connected;

	public RankedElement(String name, int capacity) {
		super(name,capacity);
		this.rank = new HashMap<Integer, RankedElement>();
		this.firstFavorite = 1; // default value, start to try the first favorit value
		this.connected = new ArrayList<RankedElement>();
	}
	
	public RankedElement(Element element) {
		this(element.name, element.capacity);
	}

	public RankedElement(Element element, Map<Integer, RankedElement> rank) {
		super(element.name,element.capacity);
		this.rank = rank;
		this.firstFavorite = 1; // default value, start to try the first favorit value
		this.connected = new ArrayList<RankedElement>();
	}

	public void setRank(Map<Integer, RankedElement> rank) {
		this.rank = rank;
	}

	/**
	 * Get the favorite and increase it 
	 * @return the favorite
	 */
	public RankedElement getFavorite() {
		RankedElement ElementRankedFavorit = rank.get(firstFavorite);
		increaseFavorite();
		return ElementRankedFavorit;
	}

	private void increaseFavorite() {
		firstFavorite++;
	}

	/* Add a candidat to the connected List */
	private void addBind(RankedElement ElementRanked) {
		connected.add(ElementRanked);
	}

	/* Remove a candidat to the connected List */
	private void removeBind(RankedElement ElementRanked) {
		connected.remove(ElementRanked);
	}

	public void beSerenadedBy(RankedElement ElementRanked) {
		this.addBind(ElementRanked);
		ElementRanked.addBind(this);
	}

	/* Remove the Candidats who have the worst rank */
	public Set<RankedElement> eliminateWorst() {
		Set<RankedElement> eliminated = new HashSet<RankedElement>();

		Map<RankedElement, Integer> connectedRank = new HashMap<RankedElement, Integer>();
		for (RankedElement ElementRanked : connected) {
			connectedRank.put(ElementRanked, getRankOf(ElementRanked));
		}

		while (connected.size() > capacity) {
			// find worst rank
			RankedElement ElementRankedToRemove = null;
			int max = 0;
			for (RankedElement ElementRanked : connectedRank.keySet()) {
				int ElementRankedRank = connectedRank.get(ElementRanked);
				if (ElementRankedRank > max) {
					ElementRankedToRemove = ElementRanked;
					max = ElementRankedRank;
				}
			}

			// remove him
			removeBind(ElementRankedToRemove);
			ElementRankedToRemove.removeBind(this);
		}
		return eliminated;

	}

	/**
	 * Find the rank of an ElementRanked
	 * 
	 * @param ElementRanked the ElementRanked
	 * @return the rank of the ElementRanked
	 */
	private Integer getRankOf(RankedElement ElementRanked) {
		for (Integer ElementRankedRank : this.rank.keySet()) {
			if (this.rank.get(ElementRankedRank).equals(ElementRanked)) {
				return ElementRankedRank;
			}
		}

		return null;
	}

	@Override
	public boolean equals(Object obj) {
				if (super.equals(obj)) {
					if (((RankedElement) obj).rank.equals(this.rank)) {
						return true;
			}
		}
		return false;
	}

	public boolean haveTheGoodNumberOfBind() {
		return this.connected.size() >= this.capacity;
	}

	public List<RankedElement> getConnected() {
		return connected;
	}
	
}