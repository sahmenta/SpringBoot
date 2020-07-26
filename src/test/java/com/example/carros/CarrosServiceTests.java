package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTests {

	@Autowired
	private CarroService carroService;

	@Test
	public void testSave() {

		Carro carro  = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		CarroDTO c = carroService.saveCarro(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		Optional<CarroDTO> op = carroService.getCarroById(id);
		assertTrue(op.isPresent());
		c = op.get();

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		carroService.delete(id);

		assertFalse(carroService.getCarroById(id).isPresent());

	}

	@Test
	public void testLista(){
		List<CarroDTO> carros = carroService.getCarros();
		assertEquals(9, carros.size());
	}

	@Test
	public void testGet(){

		Optional<CarroDTO> op = carroService.getCarroById(8L);

		assertTrue(op.isPresent());

		CarroDTO c = op.get();

		assertEquals("Ferrari Enzo", c.getNome());
	}

	@Test
	public void testCarrosByTipo(){

		assertEquals(3, carroService.getCarroByTipo("esportivos").size());
		assertEquals(3, carroService.getCarroByTipo("classicos").size());
		assertEquals(3, carroService.getCarroByTipo("luxo").size());
		assertEquals(0, carroService.getCarroByTipo("x").size());
	}
}