package br.edu.univas.vo;

public class VendasDados {
	
	private int QuantCafeVendido;
	private float Valor;
	private float total;
	
	public int getQuantCafeVendido() {
		return QuantCafeVendido;
	}
	public void setQuantCafeVendido(int quantCafeVendido) {
		QuantCafeVendido = quantCafeVendido;
	}
	public float getValor() {
		return Valor;
	}
	public void setValor(float valor) {
		Valor = valor;
	}
	
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
}
