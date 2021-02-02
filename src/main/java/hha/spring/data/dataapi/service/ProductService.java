package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductService {

	@Autowired
    private ProductRepository repo;

	public List<Product> listAllProducts() {
		return repo.findAll();
	}

	public List<Item> listAllSearch(String keyword) { return (List<Item>) repo.findBySearchKeyword(keyword);}

}
