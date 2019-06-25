package siteParis;


public class CompteJoueur {

	/** 
	 * @uml.property name="joueur"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="compte_Joueur:siteParis.Joueur"
	 * @uml.association name="possède"
	 */
	private Joueur joueur = new siteParis.Joueur();
	/**
	 * @uml.property  name="soldeJetons"
	 */
	private int soldeJetons;

	/**
	 * Getter of the property <tt>soldeJetons</tt>
	 * @return  Returns the soldeJetons.
	 * @uml.property  name="soldeJetons"
	 */
	private int getSoldeJetons() {
		return soldeJetons;
	}

	/**
	 * Setter of the property <tt>soldeJetons</tt>
	 * @param soldeJetons  The soldeJetons to set.
	 * @uml.property  name="soldeJetons"
	 */
	private void setSoldeJetons(int soldeJetons) {
		this.soldeJetons = soldeJetons;
	}

	/**
	 * @uml.property  name="jetonsEngagesMises"
	 */
	private int jetonsEngagesMises = 0;

	/**
	 * Getter of the property <tt>jetonsEngagesMises</tt>
	 * @return  Returns the jetonsEngagesMises.
	 * @uml.property  name="jetonsEngagesMises"
	 */
	private int getJetonsEngagesMises() {
		return jetonsEngagesMises;
	}

	/**
	 * Setter of the property <tt>jetonsEngagesMises</tt>
	 * @param jetonsEngagesMises  The jetonsEngagesMises to set.
	 * @uml.property  name="jetonsEngagesMises"
	 */
	private void setJetonsEngagesMises(int jetonsEngagesMises) {
		this.jetonsEngagesMises = jetonsEngagesMises;
	}

}
