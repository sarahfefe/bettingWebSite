package siteParis;

import java.util.Collection;
import java.util.LinkedList;


public class Competition {


	public Collection parisVainqueur;

	public Collection parisPodium;

	public Collection competiteur;

	public Collection competiteur1;
   
   public String[] competiteurs;
   
   public void setCompetiteurs(String[] compete) {
		competiteurs = compete;
	}
   public String[] getCompetiteurs() {
		return competiteurs;
   }




	public String nomCompetition;

	public String getNomCompetition() {
		return nomCompetition;
   }
   
   public void setNomCompetition(String nomCompetition) {
		this.nomCompetition = nomCompetition;
	}
	
   
   
   public char dateCloture;

	public char getDateCloture() {
		return dateCloture;
	}

	public void setDateCloture(char dateCloture) {
		this.dateCloture = dateCloture;
	}

}