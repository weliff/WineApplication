package com.algaworks.wine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.wine.model.Vinho;
import com.algaworks.wine.repository.Vinhos;

@Service
public class CadastroVinhoService {
	
	@Autowired
	private Vinhos vinhos;
	
	public void salvar(Vinho vinho) {
		//regras de négocios
		vinhos.save(vinho);
		
	}
}
