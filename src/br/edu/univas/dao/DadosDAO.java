package br.edu.univas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.univas.vo.CT;
import br.edu.univas.vo.CafeDados;
import br.edu.univas.vo.DiasDados;
import br.edu.univas.vo.LP;
import br.edu.univas.vo.TA;
import br.edu.univas.vo.TM;
import br.edu.univas.vo.VendasDados;


public class DadosDAO {
	
	private Connection connection;
	
	public DadosDAO() throws SQLException {
		connection = ConnectionUtil.getConnection();
	}
	
	public void save(DiasDados diasDados) throws SQLException {
		
		String sql = "insert into diadados (nome, quantidade)"
				+ "values (?, ?)";
		
		
			PreparedStatement statement = connection.prepareStatement(sql);
			int index=1;
			statement.setString(index++, diasDados.getNome());
			statement.setFloat(index++, diasDados.getQuantidadeM());
			statement.executeUpdate();
				
	}
	
	public List<DiasDados> getAll(){
		
		List<DiasDados> dados = new ArrayList<DiasDados>();
		
		String sql = "select * from diadados";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()){
				DiasDados dado = new DiasDados();
				dado.setNome(result.getString("nome"));
				dado.setQuantidadeM(result.getFloat("quantidade"));
				
				dados.add(dado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return dados;
	}
	
	
	public void saveCafe(CafeDados cafeDados) throws SQLException {
		
		String sql = "insert into Cafedados (medida, medidasAPagar, valorMedida)"
				+ "values (?, ?, ?)";
		
		
			PreparedStatement statement = connection.prepareStatement(sql);
			int index=1;
			statement.setFloat(index++, cafeDados.getMedidas());
			statement.setInt(index++, cafeDados.getMedidasPagar());
			statement.setFloat(index++, cafeDados.getValorMedida());
			statement.executeUpdate();
				
	}
	
	public List<CafeDados> getCafé(){
		
		List<CafeDados> Cdados = new ArrayList<CafeDados>();
		
		String sql = "select * from cafedados";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()){
				CafeDados dado = new CafeDados();
				dado.setMedidas(result.getFloat("medida"));
				dado.setMedidasPagar(result.getInt("medidasAPagar"));
				dado.setValorMedida(result.getFloat("valorMedida"));
				Cdados.add(dado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Cdados;
	}
	
	public void saveVendas(VendasDados vendaDados) throws SQLException {
		
		String sql = "insert into Vendadados (quantCafeVendido, valor)"
				+ "values (?, ?)";
		
		
			PreparedStatement statement = connection.prepareStatement(sql);
			int index=1;
			statement.setFloat(index++, vendaDados.getQuantCafeVendido());
			statement.setFloat(index++, vendaDados.getValor());
			statement.executeUpdate();
				
	}
	
	public List<VendasDados> getVendas(){
		
		List<VendasDados> Vdados = new ArrayList<VendasDados>();
		
		String sql = "select * from Vendadados";
		
		try {
			
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()){
				VendasDados dado = new VendasDados();
				dado.setQuantCafeVendido(result.getInt("quantCafeVendido"));
				dado.setValor(result.getFloat("valor"));
				Vdados.add(dado);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Vdados;
	}
	
	public float getAllTM() {
		
		String sql = "select SUM(medida) as medida from cafedados";
		
		TM tm= new TM();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				tm.setTm(Float.parseFloat(result.getString("medida")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		float a1 = tm.getTm();
		return a1;
	}
	
	public float getAllTA() {
		
		String sql = "SELECT SUM((quantCafeVendido* valor)) AS total FROM vendadados";
		
		TA ta= new TA();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				ta.setTa(Float.parseFloat(result.getString("total")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		float a = ta.getTa();
		return a;
	}
	
	
	public float getAllCT() {
		
		String sql = "SELECT SUM(valormedida * medidasAPagar) AS total FROM cafedados";
		
		CT ct= new CT();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				ct.setCt(Float.parseFloat(result.getString("total")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		float a = ct.getCt();
		return a;
	}
	
	public float getAllLP() {
		
		String sql = "SELECT (SUM(quantcafevendido * valor) - (SELECT SUM(medidasapagar * valormedida) FROM cafedados)) total FROM vendadados";
		
		LP lp= new LP();
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				lp.setLp(Float.parseFloat(result.getString("total")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		float a = lp.getLp();
		return a;
	}
}