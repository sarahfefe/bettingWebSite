package siteParis;

import java.util.Collection;


public class Joueur extends Personne {

	/** 
	 * @uml.property name="siteDeParisMetier"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="joueur:siteParis.SiteDeParisMetier"
	 * @uml.association name="consulte"
	 */
	private SiteDeParisMetier siteDeParisMetier = new siteParis.SiteDeParisMetier();
	/** 
	 * @uml.property name="paris"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="joueur:siteParis.Paris"
	 * @uml.association name="effectue"
	 */
	private Collection paris;
	/** 
	 * @uml.property name="compte_Joueur"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="joueur:siteParis.Compte_Joueur"
	 * @uml.association name="possÃ¨de"
	 */
    
	private Compte_Joueur compte_Joueur = new siteParis.Compte_Joueur();
   private List<Paris> listeParis; 
   private long totalJetons;
   
   public long getTotalJetons() {
        return totalJetons;
    }

   public int getJetonsMises(){
        int jetonsMises = 0;
        for (paris p : this.listeParis) {
            jetonsMises += p.getJetons(); // méthode getJetons dans classe Paris
        }
        return coinsBet;
    }
    
    public List<Bet> getListBet() {
        return listBet;
    }





   

}