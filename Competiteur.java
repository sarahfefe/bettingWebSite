package siteParis;

import java.util.Collection;
import java.util.LinkedList;

public class Competiteur {

	/** 
	 * @uml.property name="parisVainqueur"
	 * @uml.associationEnd multiplicity="(1 -1)" inverse="competiteur:siteParis.ParisVainqueur"
	 * @uml.association name="mise sur"
	 */
	public Collection parisVainqueur;
	/** 
	 * @uml.property name="parisPodium"
	 * @uml.associationEnd multiplicity="(1 -1)" inverse="competiteur:siteParis.ParisPodium"
	 * @uml.association name="mise sur"
	 */
	public Collection parisPodium;
	/** 
	 * @uml.property name="competition"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="competiteur:siteParis.Competition"
	 * @uml.association name="vainqueur"
	 */
	public Collection competition;
	/**
	 * @uml.property  name="competition1"
	 * @uml.associationEnd  multiplicity="(1 -1)" inverse="competiteur1:siteParis.Competition"
	 * @uml.association  name="participe à"
	 */
	public Collection competition1;
	/**
	 * @uml.property  name="nomCompetiteur"
	 */
	public String nomCompetiteur;
	/**
	 * Getter of the property <tt>nomCompetiteur</tt>
	 * @return  Returns the nomCompetiteur.
	 * @uml.property  name="nomCompetiteur"
	 */
	public String getNomCompetiteur() {
		return nomCompetiteur;
	}
	/**
	 * Setter of the property <tt>nomCompetiteur</tt>
	 * @param nomCompetiteur  The nomCompetiteur to set.
	 * @uml.property  name="nomCompetiteur"
	 */
	public void setNomCompetiteur(String nomCompetiteur) {
		this.nomCompetiteur = nomCompetiteur;
	}
   
   public LinkedList<Joueur> liste_parieur=new LinkedList();
   
   public void add_parieur(Joueur parieur){
      liste_parieur.add(parieur);
      }
      
   public LinkedList<Joueur> getListeParieur(){
   return liste_parieur;
   }

}