package it.polito.anagrammi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;



public class DizionarioDAO {

	List<String> resultSbagliate;
	public List<String> getparoleGiuste(List<String> parole) {

		final String sql = "SELECT nome "
				+ "FROM dizionario.parola "
				+ "WHERE nome=?;";

		
		List<String> result = new LinkedList<String>();
		resultSbagliate=new LinkedList<String>();
		
		System.out.println("qui");
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			
			for(String par:parole) {
			
			st.setString(1,par);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {  //se è qui c'è almeno una parola
			
				result.add(par);
				}
			else {
				resultSbagliate.add(par);
			}
			
			}
			conn.close();
			
			
			return result;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public List<String> getResultSbagliate() {
		return resultSbagliate;
	}
	
	
	
}
