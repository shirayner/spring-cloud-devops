package com.ray.study.dockercompose.dcproductservice.service.impl;

import com.ray.study.dockercompose.dcmodel.entity.product.Product;
import com.ray.study.dockercompose.dcproductservice.repository.ProductRepository;
import com.ray.study.dockercompose.dcproductservice.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductServiceImpl
 *
 * @author shira 2019/06/04 14:27
 */
@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository){
		this.productRepository = productRepository;
	}

	// 命中率低，因此不采用缓存
	@Override
	public List<Product> list() {
		return productRepository.findAll();
	}

	@Cacheable(value = "dc-product", key = "#id")
	@Override
	public Product query(Long id) {
		Optional<Product> productOptional = productRepository.findById(id);
		return productOptional.orElse(null);
	}

	@CachePut(value = "dc-product", key = "#product.id")
	@Override
	public Product insert(Product product) {
		return productRepository.save(product);
	}

	@CachePut(value = "dc-product", key = "#product.id")
	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@CacheEvict(value = "dc-product", key = "#id")
	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}
}
