package com.ray.study.dockercompose.dcorderservice.service.impl;


import com.ray.study.dockercompose.dcmodel.entity.order.Order;
import com.ray.study.dockercompose.dcmodel.entity.product.Product;
import com.ray.study.dockercompose.dcorderservice.client.ProductClient;
import com.ray.study.dockercompose.dcorderservice.repository.OrderRepository;
import com.ray.study.dockercompose.dcorderservice.service.OrderService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author shira 2019/06/04 15:47
 */
@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private ProductClient productClient;

	public OrderServiceImpl(OrderRepository orderRepository, ProductClient productClient) {
		this.orderRepository = orderRepository;
		this.productClient = productClient;
	}


	// 命中率低，因此不采用缓存
	@Override
	public List<Order> list() {
		List<Order> orderList = orderRepository.findAll();
		return orderList.stream().map(order -> {
			Product product = productClient.query(order.getProductId());
			order.setProduct(product);
			return order;
		}).collect(Collectors.toList());

	}

	@Cacheable(value = "dc-order",key = "#id")
	@Override
	public Order query(Long id) {
		Optional<Order> orderOptional = orderRepository.findById(id);
		orderOptional.ifPresent(order -> {
			Product product = productClient.query(order.getProductId());
			order.setProduct(product);
		});

		return orderOptional.orElse(null);
	}

	@CachePut(value = "dc-order", key = "#order.id")
	@Override
	public Order create(Order order) {

		if (order.getProductId() == null) {
			throw new IllegalArgumentException("未选择商品");
		}

		Product product = productClient.query(order.getProductId());

		Order newOrder = new Order();
		newOrder.setProductId(order.getProductId());
		newOrder.setPayment(product.getPrice());
		newOrder.setOrderStatus(0);
		newOrder.setCreateDate(new Date());
		newOrder.setLastUpdateDate(new Date());

		return orderRepository.save(order);
	}

	@CachePut(value = "dc-order", key = "#order.id")
	@Override
	public Order pay(Order order) {
		order.setOrderStatus(1);
		order.setPaymentDate(new Date());
		order.setLastUpdateDate(new Date());

		return orderRepository.save(order);
	}

	@CacheEvict(value = "dc-order", key = "#id")
	@Override
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
}
