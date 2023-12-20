package br.fateczl.com.agis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fateczl.com.agis.model.Datas;
import br.fateczl.com.agis.repository.DatasRepository;

@Service
public class DatasService {
	@Autowired
	private DatasRepository drep;
	
	public void inserir(Datas d) {
		drep.save(d);
	}
	
	public List<Datas> buscaTudo() {
		return drep.findAll();
	}
}
