package siteParis;

import java.util.Collection;


public abstract class Paris {

	/** 
	 * @uml.property name="joueur"
	 * @uml.associationEnd multiplicity="(1 -1)" inverse="paris:siteParis.Joueur"
	 * @uml.association name="effectue"
	 */
	private Collection joueur;
   
   //nombre de jetons placés sur le pari
   protected long miseEnJetons; 
   //le joueur qui a effectué le pari
   protected Joueur joueur ;
   
   //retourne le nombre de jetons misés
	public long getJetons() {
		return jetons;
	}

   // retroune le joueur qui a effectué le pari
	 public Player getPlayer() {
		return player;
	 }


}
