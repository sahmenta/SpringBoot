package com.example.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;


	public List<CarroDTO> getCarros() {
		List<Carro> carros = rep.findAll();
		return carros.stream().map(CarroDTO::create).collect(Collectors.toList());
	}


	public Optional<CarroDTO> getCarroById(Long id) {

		return rep.findById(id).map(CarroDTO::create);
	}


	public List<CarroDTO> getCarroByTipo(String tipo) {
		List<Carro> carros = rep.findByTipo(tipo);
		return carros.stream().map(CarroDTO::create).collect(Collectors.toList());
	}


	public CarroDTO saveCarro(Carro carro) {

		Assert.isNull(carro.getId(), "Não foi possível inserir o registro");

		return CarroDTO.create(rep.save(carro));
	}


	public CarroDTO updateCarro(Carro carro, Long id) {
		Assert.notNull(id, "Id inválido!");

		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()){
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id " + db.getId());

			rep.save(db);

			return CarroDTO.create(db);
		}
		else{
			return null;
		}

		// Optional<Carro> c = rep.findById(id);
		// if (c.isPresent()) {
		//     Carro db = c.get();
		//     db.setNome(carro.getNome());
		//     db.setTipo(carro.getTipo());
		//     return rep.save(db);
		// }
	}


	public boolean delete(Long id) {
		Optional<CarroDTO> c = getCarroById(id);
		if (c.isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;

	}
}
