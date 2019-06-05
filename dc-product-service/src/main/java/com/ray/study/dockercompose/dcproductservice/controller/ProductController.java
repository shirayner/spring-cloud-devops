package com.ray.study.dockercompose.dcproductservice.controller;

import com.ray.study.dockercompose.dcmodel.entity.product.Product;
import com.ray.study.dockercompose.dcproductservice.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理
 *
 * @author shira 2019/06/04 14:29
 */

@Api(tags = {"商品管理"})
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	private ProductService productService;

	public ProductController( ProductService productService){
		this.productService = productService;
	}

	@ApiOperation(value = "查询商品列表")
	@GetMapping
	public List<Product> list() {
		return  productService.list();
	}

	@ApiOperation(value = "查询商品",  notes = "根据商品id查询商品，商品id需大于0")
	@ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long")
	@GetMapping("/{id}")
	public Product query(@PathVariable Long id) {
		return productService.query(id);
	}



	@ApiOperation(value = "新增商品", notes="新增商品，商品id不用传，会自增")
	@ApiImplicitParam(name = "product", value = "商品信息实体", required = true, dataType = "Product", paramType = "body")
	@PostMapping
	public Product insert(@RequestBody Product product) {
		return productService.insert(product);
	}



	@ApiOperation(value = "更新商品")
	@ApiImplicitParam(name = "product", value = "商品信息实体", required = true, dataType = "Product", paramType = "body")
	@PutMapping
	public Product update(@RequestBody Product product) {
		return productService.update(product);
	}


	@ApiOperation(value = "删除商品")
	@ApiImplicitParam(name = "id", value = "商品ID", required = true, dataType = "Long")
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		productService.delete(id);
		return "success";
	}

}

