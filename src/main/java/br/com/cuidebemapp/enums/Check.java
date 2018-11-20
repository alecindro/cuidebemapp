package br.com.cuidebemapp.enums;

public enum Check {
	
	CHECKIN("Entrou"),
	CHECKOUT("Saiu");
	
	
	private String descricao;
	
	private Check(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	

}
