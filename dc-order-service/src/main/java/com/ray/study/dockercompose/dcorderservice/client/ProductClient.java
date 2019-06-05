package com.ray.study.dockercompose.dcorderservice.client;

import com.ray.study.dockercompose.dcmodel.entity.base.ServiceList;
import com.ray.study.dockercompose.dcmodel.entity.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description
 *
 * @author shira 2019/06/04 16:16
 */
@FeignClient(name = ServiceList.DC_PRODUCT_SERVICE, path = "/product")
public interface ProductClient {

	@GetMapping
	public List<Product> list();

	@GetMapping("/{id}")
	public Product query(@PathVariable("id") Long id);

	@PostMapping
	public Product insert(@RequestBody Product product);

	@PutMapping
	public Product update(@RequestBody Product product);

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id);

}
