package com.ray.study.dockercompose.dcorderservice.controller;


import com.ray.study.dockercompose.dcmodel.entity.order.Order;
import com.ray.study.dockercompose.dcorderservice.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理
 *
 * @author shira 2019/06/04 16:06
 */
@Api(tags = {"订单管理"})
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;

	public OrderController( OrderService orderService ){
		this.orderService = orderService;
	}

	@ApiOperation(value = "查询订单列表")
	@GetMapping
	public List<Order> list() {
		return  orderService.list();
	}

	@ApiOperation(value = "查询订单",  notes = "根据订单id查询订单，订单id需大于0")
	@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public Order get(@PathVariable Long id) {
		return orderService.query(id);
	}



	@ApiOperation(value = "创建订单", notes="创建订单，订单id不用传，会自增")
	@ApiImplicitParam(name = "order", value = "订单信息实体", required = true, dataType = "Order", paramType = "body")
	@PostMapping
	public Order create(@RequestBody Order order) {
		return orderService.create(order);
	}



	@ApiOperation(value = "支付订单")
	@ApiImplicitParam(name = "order", value = "订单信息实体", required = true, dataType = "Order", paramType = "body")
	@PutMapping
	public Order pay(@RequestBody Order order) {
		return orderService.pay(order);
	}


	@ApiOperation(value = "删除订单")
	@ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		orderService.delete(id);
		return "success";
	}
}
