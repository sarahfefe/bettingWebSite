package siteParis;


public class Personne {

	protected String nom;
   protected String prenom;
   protected String pseudo;
   protected String motDePasse;

   //***********constructeur************
   
   public Personne(String nom, String prenom, String pseudo, String motDePasse) 
   {
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.motDePasse = motDePasse;
   }       

   //***********accesseurs**************
   
	public String getNom() {
		return nom;
	}
   
   public String getPrenom() {
		return prenom;
	}
   	
	public String getPseudo() {
		return pseudo;
	}
   	
	public String getMotDePasse() {
		return motDePasse;
	}

   //***********mutateurs***************
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

}
