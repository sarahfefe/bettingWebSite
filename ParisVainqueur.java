package siteParis;

import java.util.Collection;


public class ParisVainqueur extends Paris {

	/** 
	 * @uml.property name="competiteur"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="parisVainqueur:siteParis.Competiteur"
	 * @uml.association name="mise sur"
	 */
	private Competiteur competiteur = new siteParis.Competiteur();
	/** 
	 * @uml.property name="competition"
	 * @uml.associationEnd multiplicity="(1 -1)" inverse="parisVainqueur:siteParis.Competition"
	 * @uml.association name="mise correspond à"
	 */
	private Collection competition;
	/**
	 * @uml.property  name="vainqueurEnvisage"
	 */
	private Competiteur vainqueurEnvisage;
	/**
	 * Getter of the property <tt>vainqueurEnvisage</tt>
	 * @return  Returns the vainqueurEnvisage.
	 * @uml.property  name="vainqueurEnvisage"
	 */
	private Competiteur getVainqueurEnvisage() {
		return vainqueurEnvisage;
	}
	/**
	 * Setter of the property <tt>vainqueurEnvisage</tt>
	 * @param vainqueurEnvisage  The vainqueurEnvisage to set.
	 * @uml.property  name="vainqueurEnvisage"
	 */
	private void setVainqueurEnvisage(Competiteur vainqueurEnvisage) {
		this.vainqueurEnvisage = vainqueurEnvisage;
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
