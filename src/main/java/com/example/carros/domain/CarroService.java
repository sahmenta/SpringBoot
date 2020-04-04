package com.example.carros.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;

	public Iterable<Carro> getCarros(){
		return repository.findAll();
		
	}

	public Optional<Carro> getCarroById(Long id) {
		return repository.findById(id);
	}
	
	public Iterable<Carro> getCarrosByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public Carro insert(Carro carro) {
		return repository.save(carro);
		
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possível atualizar o registro");
		
		
		return getCarroById(id).map(db->{
			
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			repository.save(db);
			
			return db;	
		}).orElseThrow(()-> new RuntimeException("Não foi possível atualizar o registro"));
	
	}

	public String delete(Long id) {
		
		Optional<Carro> carro = getCarroById(id);
		if(carro.isPresent()){
			repository.deleteById(id);
			return "Carro deletado com sucesso";
		}
		return "Carro não encontrado";
		
	}
	
}
