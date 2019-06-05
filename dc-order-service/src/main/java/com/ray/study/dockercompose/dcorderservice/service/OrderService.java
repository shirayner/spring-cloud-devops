package com.ray.study.dockercompose.dcorderservice.service;


import com.ray.study.dockercompose.dcmodel.entity.order.Order;

import java.util.List;

/**
 * description
 *
 * @author shira 2019/06/04 15:45
 */
public interface OrderService {
	/**
	 * 获取订单列表
	 * @return
	 */
	List<Order> list();

	/**
	 * 根据id获取订单
	 * @param id
	 * @return
	 */
	Order query(Long id);

	/**
	 * 新增订单
	 * @param order
	 * @return
	 */
	Order create(Order order);

	/**
	 * 支付订单
	 * @param order
	 * @return
	 */
	Order pay(Order order);


	/**
	 * 删除订单
	 * @param id
	 * @return
	 */
	void delete(Long id);





}
