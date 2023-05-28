package es.mi.microservicio.bookingmicroservice.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import es.mi.microservicio.bookingmicroservice.client.StockClient;
import es.mi.microservicio.bookingmicroservice.dto.OrderDto;
import es.mi.microservicio.bookingmicroservice.entity.Order;
import es.mi.microservicio.bookingmicroservice.repository.OrderRepository;

@RestController
@RequestMapping("api/booking")

public class BookingController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private StockClient stockClient;

	@PostMapping("/order")
	@HystrixCommand(fallbackMethod = "fallbackToStockService")
	public String saveOrder(@RequestBody OrderDto orderDto) {

		boolean inStock = orderDto.getOrderItems().stream()
				.allMatch(orderItem -> stockClient.stockAvailable(orderItem.getCode()));

		if (inStock) {

			Order order = new Order();

			order.setOrderNumber(UUID.randomUUID().toString());
			order.setOrderItems(orderDto.getOrderItems());

			orderRepository.save(order);

			return "Order saved";
		} else {
			return "Don't have stock; Order cannot be saved";
		}

	}
	
	private String fallbackToStockService() {
		return "Something went wrong, please try after some time";
	}

}
