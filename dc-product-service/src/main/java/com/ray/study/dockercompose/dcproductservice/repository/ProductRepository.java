package com.ray.study.dockercompose.dcproductservice.repository;

import com.ray.study.dockercompose.dcmodel.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author shira 2019/06/04 14:25
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
