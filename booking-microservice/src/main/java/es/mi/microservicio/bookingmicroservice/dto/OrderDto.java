package es.mi.microservicio.bookingmicroservice.dto;

import java.util.List;

import es.mi.microservicio.bookingmicroservice.entity.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class OrderDto {
	
	private List<OrderItem> OrderItems;
	
}
