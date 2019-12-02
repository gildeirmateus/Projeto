package br.edu.univas.vo;

public class DiasDados {
	
	private String nome;
	private int data;
	private float quantidadeM;
	private boolean DiaOuMedida;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	
	
	public float getQuantidadeM() {
		return quantidadeM;
	}
	public void setQuantidadeM(float quantidadeM) {
		this.quantidadeM = quantidadeM;
	}
	
	
	public boolean isDiaOuMedida() {
		return DiaOuMedida;
	}
	public void setDiaOuMedida(boolean diaOuMedida) {
		DiaOuMedida = diaOuMedida;
	}
}
