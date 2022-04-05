package it.polito.tdp.model;

import java.util.LinkedList;
import java.util.List;

import it.polito.anagrammi.db.DizionarioDAO;

public class Model {

	List<String>anagrammi=  new LinkedList<String>();
	DizionarioDAO diz= new DizionarioDAO();
	public void anagramma(String s) {
		anagrammi.clear();
		anagramma_ricorsiva("",0,s);
	}

	private void anagramma_ricorsiva(String parziale, int livello, String rimanenti) {
		if(rimanenti.length()==0) {
			System.out.println(parziale);
			anagrammi.add(parziale);
		}
		else {
			for(int pos=0;pos<rimanenti.length();pos++) {
				
				String nuova_parziale = parziale + rimanenti.charAt(pos);
				String nuova_rimanenti = rimanenti.substring(0, pos) + rimanenti.substring(pos + 1);
				
				anagramma_ricorsiva(nuova_parziale, livello + 1, nuova_rimanenti);
				
			}
		}
		
	}
	
	public List<String >paroleGiuste(String s) {
		this.anagramma(s);
		return diz.getparoleGiuste(anagrammi);
		
	}
	public List<String >paroleSbagliate(String s) {
		this.anagramma(s);
		return diz.getResultSbagliate();
		
	}
	
}
