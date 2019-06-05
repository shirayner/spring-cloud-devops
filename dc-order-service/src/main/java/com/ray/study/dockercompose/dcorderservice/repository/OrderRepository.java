package com.ray.study.dockercompose.dcorderservice.repository;

import com.ray.study.dockercompose.dcmodel.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author shira 2019/06/04 14:25
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
