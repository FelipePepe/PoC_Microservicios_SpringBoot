package es.mi.microservicio.stockmicroservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.mi.microservicio.stockmicroservice.entity.StockEntity;
import es.mi.microservicio.stockmicroservice.repository.StockRepository;

@RestController
@RequestMapping("/api/stock")
public class StockController {

	@Autowired
	private StockRepository stockRepository;
	
	@GetMapping("/{code}")
	public boolean stockAvailable(@PathVariable String code) {
		Optional<StockEntity> stock = stockRepository.findByCode(code);
		
		stock.orElseThrow(() -> new RuntimeException("Cannot find the product " + code));
		
		return stock.get().getQuantity() > 0;
	}
	
}
