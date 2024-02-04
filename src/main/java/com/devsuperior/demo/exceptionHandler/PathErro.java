package com.devsuperior.demo.exceptionHandler;

public enum PathErro {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada" , "entidade nao encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso" , "violação de integridade"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro Inválido");

	private String Url;
	private String title;
	
	PathErro(String Url, String title) {
		this.Url = "https//kaique.com.br" + Url;
		this.title = title;
	}

	public String getUrl() {
		return Url;
	}

	public String getTitle() {
		return title;
	}
	
}
