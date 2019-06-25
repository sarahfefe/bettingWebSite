package siteParis;

import java.util.LinkedList;
import java.util.Collection;


/**
 * 
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les m�thodes "M�tier" de la gestion du site de paris. 
 * <br><br>
 * Dans toutes les m�thodes :
 * <ul>
 * <li>un param�tre de type <code>String</code> est invalide si il n'est pas instanci�.</li>
 *  <li>pour la validit� d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caract�res autoris�s </li>
 * <li>       il doit avoir une longueur d'au moins 8 caract�res </li>
 * </ul></li>       
 * <li>pour la validit� d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caract�res autoris�s  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caract�res</li>
 * </ul></li>       
 * <li>pour la validit� d'un pr�nom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caract�res autoris�s  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caract�re </li>
 * </ul></li>
 * <li>pour la validit� d'une comp�tition  :       
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et soulign� sont les seuls caract�res autoris�s </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caract�res</li>
 * </ul></li>       
 * <li>pour la validit� d'un comp�titeur  :       
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et soulign� sont les seuls caract�res autoris�s </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caract�res.</li>
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
	 * @throws MetierException  lev�e 
	 * si le <code>passwordGestionnaire</code>  est invalide 
	 */
       
	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException 
   {
      validitePasswordGestionnaire(passwordGestionnaire);
      gestionnaire = new Gestionnaire(passwordGestionnaire);
      
      }



	// Les m�thodes du gestionnaire (avec mot de passe gestionnaire)



	/**
	 * inscrire un joueur. 
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le pr�nom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   lev�e  
	 * si le <code>passwordGestionnaire</code> propos� est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   lev�e si un joueur existe avec les m�mes noms et pr�noms ou le m�me pseudo.
	 * @throws JoueurException lev�e si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le mot de passe (d�termin� par le site) du nouveau joueur inscrit.
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
	 * @param prenom   le pr�nom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   lev�e si il n'y a pas de joueur  avec le m�me <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException lev�e 
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 * 
	 * @return le nombre de jetons � rembourser  au joueur qui vient d'�tre d�sinscrit.
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
	 * ajouter une comp�tition.  
	 * 
	 * @param competition le nom de la comp�tition
	 * @param dateCloture   la date � partir de laquelle il ne sera plus possible de miser  
	 * @param competiteurs   les noms des diff�rents comp�titeurs de la comp�tition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException lev�e si le tableau des
	 * comp�titeurs n'est pas instanci�, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException lev�e si une comp�tition existe avec le m�me nom. 
	 * @throws CompetitionException lev�e si le nom de la
	 * comp�tition ou des comp�titeurs sont invalides, si il y a
	 * moins de 2 comp�titeurs, si un des competiteurs n'est pas instanci�,
	 * si deux comp�titeurs ont le m�me nom, si la date de cl�ture 
	 * n'est pas instanci�e ou est d�pass�e.
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
	 * solder une comp�tition vainqueur (comp�tition avec vainqueur).  
	 * 
	 * Chaque joueur ayant mis� sur cette comp�tition
	 * en choisissant ce comp�titeur est cr�dit� d'un nombre de
	 * jetons �gal � :
	 * 
	 * (le montant de sa mise * la somme des
	 * jetons mis�s pour cette comp�tition) / la somme des jetons
	 * mis�s sur ce comp�titeur.
	 *
	 * Si aucun joueur n'a trouv� le
	 * bon comp�titeur, des jetons sont cr�dit�s aux joueurs ayant
	 * mis� sur cette comp�tition (conform�ment au montant de
	 * leurs mises). La comp�tition est "supprim�e" si il ne reste
	 * plus de mises suite � ce solde.
	 * 
	 * @param competition   le nom de la comp�tition  
	 * @param vainqueur   le nom du vainqueur de la comp�tition 
	 * @param passwordGestionnaire  le password du gestionnaire du site 
	 * 
	 * @throws MetierException   lev�e 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   lev�e si il n'existe pas de comp�tition de m�me nom.
	 * @throws CompetitionException lev�e 
	 * si le nom de la comp�tition ou du vainqueur est invalide, 
	 * si il n'existe pas de comp�titeur du nom du vainqueur dans la comp�tition,
	 * si la date de cl�ture de la comp�tition est dans le futur.
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
	 * cr�diter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le pr�nom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons � cr�diter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   lev�e 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est n�gative.
	 * @throws JoueurException lev�e  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   lev�e si il n'y a pas de joueur  avec les m�mes nom,  pr�nom et pseudo.
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
	 * d�biter le compte en jetons d'un joueur du site de paris.
	 * 
	 * @param nom   le nom du joueur 
	 * @param prenom   le pr�nom du joueur   
	 * @param pseudo   le pseudo du joueur  
	 * @param sommeEnJetons   la somme en jetons � d�biter  
	 * @param passwordGestionnaire  le password du gestionnaire du site  
	 * 
	 * @throws MetierException   lev�e 
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est n�gative.
	 * @throws JoueurException lev�e  
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides 
	 * si le compte en jetons du joueur est insuffisant (il deviendrait n�gatif).
	 * @throws JoueurInexistantException   lev�e si il n'y a pas de joueur  avec les m�mes nom,  pr�nom et pseudo.
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

	 * @throws MetierException   lev�e  
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * 
	 * @return une liste de liste dont les �l�ments (de type <code>String</code>) repr�sentent un joueur avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le pr�nom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engag�s dans ses mises en cours. </li>
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
	








	// Les m�thodes avec mot de passe utilisateur



	/**
	 * miserVainqueur  (parier sur une comp�tition, en d�signant un vainqueur).
	 * Le compte du joueur est d�bit� du nombre de jetons mis�s.
	 * 
	 * @param pseudo   le pseudo du joueur  
	 * @param passwordJoueur  le password du joueur  
	 * @param miseEnJetons   la somme en jetons � miser  
	 * @param competition   le nom de la comp�tition  relative au pari effectu�
	 * @param vainqueurEnvisage   le nom du comp�titeur  sur lequel le joueur mise comme �tant le  vainqueur de la comp�tition  
	 * 
	 * @throws MetierException lev�e si la somme en jetons est n�gative.
	 * @throws JoueurInexistantException lev�e si il n'y a pas de
	 * joueur avec les m�mes pseudos et password.
	 * @throws CompetitionInexistanteException   lev�e si il n'existe pas de comp�tition de m�me nom. 
	 * @throws CompetitionException lev�e 
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un comp�titeur de  nom <code>vainqueurEnvisage</code> dans la comp�tition,
	 * si la comp�tition n'est plus ouverte (la date de cl�ture est dans le pass�).
	 * @throws JoueurException   lev�e 
	 * si <code>pseudo</code> ou <code>password</code> sont invalides, 
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait n�gatif).
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


    

	// Les m�thodes sans mot de passe


	/**
	 * conna�tre les comp�titions en cours.
	 * 
	 * @return une liste de liste dont les �l�ments (de type <code>String</code>) repr�sentent une comp�tition avec dans l'ordre : 
	 *  <ul>
	 *  <li>       le nom de la comp�tition,  </li>
	 *  <li>       la date de cl�ture de la comp�tition. </li>
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
	 * conna�tre  la liste des noms des comp�titeurs d'une comp�tition.  
	 * 
	 * @param competition   le nom de la comp�tition  
	 * 
	 * @throws CompetitionException   lev�e  
	 * si le nom de la comp�tition est invalide.
	 * @throws CompetitionInexistanteException   lev�e si il n'existe pas de comp�tition de m�me nom. 
	 * 
	 * @return la liste des comp�titeurs de la  comp�tition.
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
	 * v�rifier la validit� du password du gestionnaire.
	 * 
	 * @param passwordGestionnaire   le password du gestionnaire � v�rifier
	 * 
	 * @throws MetierException   lev�e 
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