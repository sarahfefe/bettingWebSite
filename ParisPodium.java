package siteParis;

import java.util.List;
import java.util.Collection;
import java.util.LinkedList;


public class ParisPodium extends Paris {

	/** 
	 * @uml.property name="competiteur"
	 * @uml.associationEnd multiplicity="(3 3)" inverse="parisPodium:siteParis.Competiteur"
	 * @uml.association name="mise sur"
	 */
	private List competiteur;
	/** 
	 * @uml.property name="competition"
	 * @uml.associationEnd multiplicity="(1 -1)" inverse="parisPodium:siteParis.Competition"
	 * @uml.association name="mise correspond à"
	 */
	private Collection competition;
	/**
	 * @uml.property  name="podiumEnvisage"
	 */
	private LinkedList podiumEnvisage;
	/**
	 * Getter of the property <tt>podiumEnvisage</tt>
	 * @return  Returns the podiumEnvisage.
	 * @uml.property  name="podiumEnvisage"
	 */
	private LinkedList getPodiumEnvisage() {
		return podiumEnvisage;
	}
	/**
	 * Setter of the property <tt>podiumEnvisage</tt>
	 * @param podiumEnvisage  The podiumEnvisage to set.
	 * @uml.property  name="podiumEnvisage"
	 */
	private void setPodiumEnvisage(LinkedList podiumEnvisage) {
		this.podiumEnvisage = podiumEnvisage;
	}
	/**
	 * @uml.property  name="miseEnJetons"
	 */
	private int miseEnJetons;
	/**
	 * Getter of the property <tt>miseEnJetons</tt>
	 * @return  Returns the miseEnJetons.
	 * @uml.property  name="miseEnJetons"
	 */
	private int getMiseEnJetons() {
		return miseEnJetons;
	}
	/**
	 * Setter of the property <tt>miseEnJetons</tt>
	 * @param miseEnJetons  The miseEnJetons to set.
	 * @uml.property  name="miseEnJetons"
	 */
	private void setMiseEnJetons(int miseEnJetons) {
		this.miseEnJetons = miseEnJetons;
	}

}
