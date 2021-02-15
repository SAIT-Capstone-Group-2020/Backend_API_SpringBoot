package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class ProductService {

	@Autowired
    private ProductRepository repo;

	public List<Product> listAllProducts() {
		return repo.findAll();
	}

	public Product findByName(String name) {
		return repo.findByName(name);
	}

	public String addProduct(Product prod) {

		String url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		//need to make feature upload image file to the cloud

		try {
			repo.save(new Product(prod.getName(), prod.getDescription(), prod.getBrand(), prod.getPrice(), prod.isActive(), url, prod.getCategory(), prod.getQuantity(), prod.getWeightValue(), prod.getWeightType()));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return "Successfully added";
	}

	public Product findById(int id) {
		return repo.findById(id);
	}

	public String removeProduct(Product prod) {

		try {
			repo.delete(prod);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return "Sccessfully removed";
	}

	public String editProduct(Product prod) {

		String url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		//need to make feature upload image file to the cloud

		try {
			repo.save(prod);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return "Successfully edited";
	}

	public List<Product> listAllSearch(String keyword) {
			return repo.findByKeyword(keyword);
	}

}
