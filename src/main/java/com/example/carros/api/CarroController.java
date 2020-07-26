package com.example.carros.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.carros.domain.dto.CarroDTO;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService carroService;

	@GetMapping()
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(carroService.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> getById (@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = carroService.getCarroById(id);

		return carro
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());

		// return carro.isPresent() ?
		//     ResponseEntity.ok(carro.get()) :
		//     ResponseEntity.notFound().build();

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getByTipo (@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = carroService.getCarroByTipo(tipo);

		return carros.isEmpty() ?
				ResponseEntity.noContent().build() :
				ResponseEntity.ok(carros);
	}

	@PostMapping
	public ResponseEntity saveCarro (@RequestBody Carro carro) {

		try {
			CarroDTO c = carroService.saveCarro(carro);

			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		}catch (Exception ex){
			System.out.println(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id){
		String idLocation = "/"+ id;
		return ServletUriComponentsBuilder.fromCurrentRequest().path(idLocation)
				.buildAndExpand().toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity updateCarro (@PathVariable("id") Long id, @RequestBody Carro carro) {

		carro.setId(id);

		CarroDTO c = carroService.updateCarro(carro, id);

		return c != null ?
				ResponseEntity.ok(c) :
				ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteCarro (@PathVariable("id") Long id) {

		boolean ok = carroService.delete(id);

		return ok ?
				ResponseEntity.ok().build() :
				ResponseEntity.notFound().build();
	}

}
