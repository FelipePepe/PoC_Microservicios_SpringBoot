package es.mi.microservicio.bookingmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.mi.microservicio.bookingmicroservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
