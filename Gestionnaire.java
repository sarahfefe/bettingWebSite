package siteParis;


public class Gestionnaire extends Personne {

	/** 
	 * @uml.property name="siteDeParisMetier"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="gestionnaire:siteParis.SiteDeParisMetier"
	 * @uml.association name="gère"
	 */
	private SiteDeParisMetier siteDeParisMetier = new siteParis.SiteDeParisMetier();
}
