package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static junit.framework.TestCase.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {

	@Autowired
	private CarroService carroService;

	@Test
	public void test1() {

		Carro carro  = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		Carro c = carroService.saveCarro(carro);

		Long id = c.getId();

		assertNotNull(c);

		Optional<Carro> op = carroService.getCarroById(id);
		assertTrue(op.isPresent());
		c = op.get();

		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());

		carroService.delete(id);

		assertFalse(carroService.getCarroById(id).isPresent());

	}
}