package siteParis;

import java.util.LinkedList;
import java.util.Collection;


/**
 * 
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris. 
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>       
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :       
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>       
 * <li>pour la validité d'un compétiteur  :       
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier{
   
   private Gestionnaire gestionnaire ;
   private LinkedList<Joueur> listeJoueurs=new LinkedList(); //Liste des joueurs  
   private LinkedList<Competition> listeCompetitions=new LinkedList();      
   private LinkedList<Paris> listeParis=new LinkedList();          
    
  	/**
	 * constructeur de <code>SiteDeParisMetier</code>. 
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire.   
	 * 
	 * @throws MetierException  levée 
	 * si le <code>passwordGestionnaire</code>  est invalide 
	 */
       
	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException 
   {
      validitePasswordGestionnaire(passwordGestionnaire);
      gestionnaire = new Gestionnaire(passwordGestionnaire);
      
      }



	// Les méthodes du gestionnaire (avec mot de passe gestionnaire)



	/**
	 * inscrire un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
	public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurException {
   validitePasswordGestionnaire(passwordGestionnaire);
   if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
   if (nom == null) throw new JoueurException();
   if (prenom == null) throw new JoueurException();
   if (pseudo == null) throw new JoueurException();
   if (!nom.matches("[A-Za-z]{0,}")) throw new JoueurException();
   if (!prenom.matches("[A-Za-z]{0,}")) throw new JoueurException();
   if (!pseudo.matches("[0-9A-Za-z]{4,}")) throw new JoueurException();
   if (nom==prenom) throw new JoueurExistantException();
   for(Joueur j:listeJoueurs){
      if (j.getName().equals(nom) && j.getPrenom().equals(prenom))throw new JoueurExistantException();
      if (j.getPseudo().equals(pseudo))throw new JoueurExistantException();
   }
   
   Joueur joueur=new Joueur();
   joueur.setName(nom);
   joueur.setPrenom(prenom);
   joueur.setPseudo(pseudo);      
	listeJoueurs.add(joueur);
   String password=getPassword(joueur);
   return password;
   }

	/**
	 * supprimer un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée 
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 * 
	 */
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {
		boolean flag=true;
      validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      for(int i=0; i<listeJoueurs.size(); i++){
         Joueur j=listeJoueurs.get(i);
         if (j.getName().equals(nom) && j.getPrenom().equals(prenom) && j.getPseudo().equals(pseudo)){
            flag=false;
            listeJoueurs.remove(i);
            }
      }
      if (flag) throw new JoueurInexistantException();
      return 0;
	}



	/**
	 * ajouter une compétition.  
	 * 
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser  
	 * @param competiteurs   les noms des différents compétiteurs de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom. 
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture 
	 * n'est pas instanciée ou est dépassée.
	 */
	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
      validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      if(competiteurs==null) throw new MetierException();
      if (competition == null) throw new CompetitionException();
      if (!competition.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();
      if (dateCloture == null) throw new CompetitionException();
      if (dateCloture.estDansLePasse()) throw new CompetitionException();
      if (competiteurs == null) throw new CompetitionException();
      if (competiteurs.length<2) throw new CompetitionException();
      for(int i=0; i<competiteurs.length; i++){
         String c=competiteurs[i];
         if(c==null) throw new CompetitionException();
         if (!c.matches("[A-Za-z]{4,}")) throw new CompetitionException();
         for (int j=i+1; j<competiteurs.length; j++){
         String c_j=competiteurs[j];
         if (c.equals(c_j))throw new CompetitionException();
         } 
         }
      for (Competition compet:listeCompetitions){
         if(compet.getNom_competition().equals(competition))throw new CompetitionExistanteException();
         }
      Competition new_competition= new Competition();
      new_competition.setCompetiteurs(competiteurs);
      new_competition.setNom_competition(competition);
      new_competition.setDate_fin(dateCloture);
      listeCompetitions.add(new_competition);
      

	}


	/**
	 * solder une compétition vainqueur (compétition avec vainqueur).  
	 * 
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 * 
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 * 
	 * @param competition   le nom de la compétition  
	 * @param vainqueur   le nom du vainqueur de la compétition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée 
	 * si le nom de la compétition ou du vainqueur est invalide, 
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition,
	 * si la date de clôture de la compétition est dans le futur.
	 * 
	 */	
	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException  {
      validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      if(vainqueur==null) throw new CompetitionException();
      if (competition == null) throw new CompetitionException();
      if (!competition.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();
      if (dateCloture == null) throw new CompetitionException();
      if (!dateCloture.estDansLePasse()) throw new CompetitionException();
      if (!vainqueur.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();
      boolean flag_compet=true;
      for (Competition compet:listeCompetitions){
         if(compet.getNom_competition().equals(competition)){
         flag_compet=false;
         }
         }
      if( flag_compet) throw new CompetitionInexistanteException();
      long mise_total=0;
      long mise_vainqueur=0;
      LinkedList<Paris> listeParisVainqueur=new LinkedList();          
      LinkedList<Paris> listeParisCompetition=new LinkedList();          

      for (Paris paris:listeParis){
      if (paris.getCompetition.getNomCompetition().equals(competition)){
         mise_total+=paris.getMise();
         listeParisCompetition.add(paris);
         if( paris.getVainqueur.equals(vainqueur)){
         listeParisVainqueur.add(paris);
         mise_vainqueur+=paris.getMise();       
	      }
      }
      } 
      if (mise_vainqueur==0){
         for(Paris pariCompetition:listeParisCompetition){
         long ancienSolde=parisCompetition.getJoueur().getTotalJetons();
         parisCompetition.getJoueur().setTotalJetons(ancienSolde + parisCompetition.getMise());
         } 
      }
      else{
         for(Paris parisVainqueur:listeParisVainqueur){
            long ancienSolde=parisCompetition.getJoueur().getTotalJetons();
            parisCompetition.getJoueur().setTotalJetons(ancienSolde + (parisCompetition.getMise()*mise_total)/mise_vainqueur);

         }
      }
}

	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à créditer  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
      validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      if(sommeEnJetons<0) throw new MetierException();
      boolean flag = true;
      Joueur joueur_a_crediter=new Joueur();
      for(int i=0; i<listeJoueurs.size(); i++){
      Joueur j=listeJoueurs.get(i);
      if (j.getName().equals(nom) && j.getPrenom().equals(prenom) && j.getPseudo().equals(pseudo)){
         flag=false;
         joueur_a_crediter=j;
         }
      }
      if (flag) throw new JoueurInexistantException();
      if (nom == null) throw new JoueurException();
      if (prenom == null) throw new JoueurException();
      if (pseudo == null) throw new JoueurException();
      if (!nom.matches("[A-Za-z]{0,}")) throw new JoueurException();
      if (!prenom.matches("[A-Za-z]{0,}")) throw new JoueurException();
      if (!pseudo.matches("[0-9A-Za-z]{4,}")) throw new JoueurException();
      float ancien_solde=joueur_a_crediter.getSolde_joueur();
      joueur_a_crediter.setSolde_joueur(ancien_solde+sommeEnJetons);
	}


	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le prénom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons à débiter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides 
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 * 
	 */

	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {
      validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      if(sommeEnJetons<0) throw new MetierException();
      boolean flag = true;
      Joueur joueur_a_debiter=null;
      for(int i=0; i<listeJoueurs.size(); i++){
      Joueur j=listeJoueurs.get(i);
      if (j.getName().equals(nom) && j.getPrenom().equals(prenom) && j.getPseudo().equals(pseudo)){
         flag=false;
         joueur_a_debiter=j;
         }
      }
      if (flag) throw new JoueurInexistantException();
      if (nom == null) throw new JoueurException();
      if (prenom == null) throw new JoueurException();
      if (pseudo == null) throw new JoueurException();
      if (!nom.matches("[A-Za-z]{0,}")) throw new JoueurException();
      if (!prenom.matches("[A-Za-z]{0,}")) throw new JoueurException();
      if (!pseudo.matches("[0-9A-Za-z]{4,}")) throw new JoueurException();
      float ancien_solde=joueur_a_debiter.getSolde_joueur();
      if (ancien_solde<sommeEnJetons) throw new JoueurException();
      joueur_a_debiter.setSolde_joueur(ancien_solde-sommeEnJetons);
   }



	/** 
	 * consulter les  joueurs.
	 * 
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris 

	 * @throws MetierException   levée  
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
    
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {
		validitePasswordGestionnaire(passwordGestionnaire);
      if(!passwordGestionnaire.equals(gestionnaire.getPassword()))throw new MetierException();
      LinkedList <LinkedList <String>> listeConsultable = new LinkedList <LinkedList <String>>();
      LinkedList <String> temporaire = new LinkedList <String>();
      for(Joueur joueur:listeJoueurs){
      temporaire.clear();
      temporaire.add(joueur.getName());
      temporaire.add(joueur.getPrenom());
      temporaire.add(joueur.getPseudo());
      temporaire.add(joueur.getTotalJetons());
      listeConsultable.add(temporaire);
      }
      return listeConsultable;
   }
	








	// Les méthodes avec mot de passe utilisateur



	/**
	 * miserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 * 
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordJoueur  le password du joueur  
	 * @param miseEnJetons   la somme en jetons à miser  
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition  
	 * 
	 * @throws MetierException levée si la somme en jetons est négative.
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * @throws CompetitionException levée 
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition,
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé).
	 * @throws JoueurException   levée 
	 * si <code>pseudo</code> ou <code>password</code> sont invalides, 
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif).
	 */
    public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {
      if(miseEnJetons<0) throw new MetierException();
      if (pseudo==null) throw new JoueurException();
      if (!pseudo.matches("[0-9A-Za-z]{4,}")) throw new JoueurException();
      if (passwordJoueur==null) throw new JoueurException();
	   if (!passwordJoueur.matches("[0-9A-Za-z]{8,}")) throw new JoueurException();
      Joueur joueur=new Joueur();
      boolean flag_joueur= true;
      for(Joueur j:listeJoueurs){
      System.out.println(j.getPassWord());
         if (j.getPseudo().equals(pseudo) && j.getPassWord().equals(passwordJoueur)){
         joueur=j;
         flag_joueur=false;}
        }
      if(flag_joueur) throw new JoueurInexistantException();
      boolean flag_compet=true;
      boolean flag_competiteur= true;
      for(Competition c:listeCompetitions){
         if (c.getNom_competition().equals(competition)){
         Competition competition_concerne=competition;
         if (c.getDate_fin().estDansLePasse()) throw new CompetitionException(); 
         String[] competiteur=c.getCompetiteur();
         for (String vainqueur:competiteur){ 
            if(vainqueur.equals(vainqueurEnvisage)){
             flag_competiteur=false;
             competiteur_concerne=vainqueur;
            }
            }
         
         
         flag_compet=false;}
        }
      
      if(flag_compet) throw new CompetitionInexistanteException();
      if (flag_competiteur) throw new CompetitionException();
      long ancien_solde=joueur.getTotalJetons();
      if (ancien_solde<miseEnJetons) throw new JoueurException();
      Paris paris = new Paris();
      paris.setCompetition(competition_concerne);
      paris.setVainqueur(vainqueurEnvisage);
      paris.setMise(misEnJetons);
      joueur.setSolde_joueur(ancien_solde-sommeEnJetons);
      listeParis.add(paris);
	}


    

	// Les méthodes sans mot de passe


	/**
	 * connaître les compétitions en cours.
	 * 
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom de la compétition,  </li>
	 *  <li>       la date de clôture de la compétition. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterCompetitions(){
      LinkedList <LinkedList <String>> listeConsultable = new LinkedList <LinkedList <String>>();
      LinkedList <String> temporaire = new LinkedList <String>();
      for (Competition competition:listeCompetitions){
      temporaire.clear();
      temporaire.add(competition.getNomCompetition());
      temporaire.add(joueur.getDateCloture());
      listeConsultable.add(temporaire);
      }
      return listeConsultable;
	} 

	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.  
	 * 
	 * @param competition   le nom de la compétition  
	 * 
	 * @throws CompetitionException   levée  
	 * si le nom de la compétition est invalide.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom. 
	 * 
	 * @return la liste des compétiteurs de la  compétition.
	 */
	public String[] consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
		if (competition == null) throw new CompetitionException();
      if (!competition.matches("[0-9A-Za-z]{4,}")) throw new CompetitionException();
      boolean flag_compet=true;
      Competition competition;
      for (Competition compet:listeCompetitions){
         if(compet.getNom_competition().equals(competition)){
         flag_compet=false;
         competition=compet;
         }
         }
      if( flag_compet) throw new CompetitionInexistanteException();
     

      
      return competition.getCompetiteurs();
	}

	/**
	 * vérifier la validité du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 * 
	 * @throws MetierException   levée 
	 * si le <code>passwordGestionnaire</code> est invalide.  
	 */
	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
	    if (passwordGestionnaire==null) throw new MetierException();
	    else if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
     	}

	/** 
	 * @uml.property name="consulte_competition"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="compose_competition:siteParis.Competition"
	 */
	private Collection competition = new java.util.ArrayList();

	/** 
	 * @uml.property name="consulte_competiteur"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="compose_competiteur:siteParis.Competiteur"
	 */
	private Collection competiteur;

	/** 
	 * @uml.property name="consulte_cj"
	 * @uml.associationEnd inverse="compose_cj:siteParis.CompteJoueur"
	 */
	private CompteJoueur compteJoueur;

	/**
	 * @uml.property  name="consulte_joueur"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="consulte_sdp:siteParis.Joueur"
	 */
	private Collection consulte_joueur;

	/**
	 * Getter of the property <tt>consulte_joueur</tt>
	 * @return  Returns the consulte_joueur.
	 * @uml.property  name="consulte_joueur"
	 */
	public Collection getConsulte_joueur() {
		return consulte_joueur;
	}





	/**
	 * Setter of the property <tt>consulte_joueur</tt>
	 * @param consulte_joueur  The consulte_joueur to set.
	 * @uml.property  name="consulte_joueur"
	 */
	public void setConsulte_joueur(Collection consulte_joueur) {
		this.consulte_joueur = consulte_joueur;
	}



}