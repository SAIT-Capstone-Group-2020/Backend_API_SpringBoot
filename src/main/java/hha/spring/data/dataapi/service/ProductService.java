package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public String addProductBulkOw(List<Product> prodList) {

		String url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		//need to make feature upload image file to the cloud

		try {

			for(int i=0; i <prodList.size(); i++) {

				Product prod = repo.findByNameAndBrand(prodList.get(i).getName(), prodList.get(i).getBrand());
				Product source = prodList.get(i);

				if(prod != null) {

					prod.setActive(source.isActive());
					prod.setCategory(source.getCategory());
					prod.setDescription(source.getDescription());
					prod.setPrice(source.getPrice());
					prod.setWeightValue(source.getWeightValue());
					prod.setWeightType(source.getWeightType());
					prod.setQuantity(source.getQuantity());

					repo.save(prod);
				}

				else {
					repo.save(new Product(prodList.get(i).getName(),
							prodList.get(i).getDescription(),
							prodList.get(i).getBrand(),
							prodList.get(i).getPrice(),
							prodList.get(i).isActive(),
							url,
							prodList.get(i).getCategory(),
							prodList.get(i).getQuantity(),
							prodList.get(i).getWeightValue(),
							prodList.get(i).getWeightType()));
				}
			}

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return "Successfully added";
	}

	public String addProductBulk(List<Product> prodList) {

		String url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		//need to make feature upload image file to the cloud

		try {

			for(int i=0; i <prodList.size(); i++) {

				System.out.println(prodList.get(i).getName());

				if(repo.findByNameAndBrand(prodList.get(i).getName(), prodList.get(i).getBrand()) == null) {
					repo.save(new Product(prodList.get(i).getName(),
							prodList.get(i).getDescription(),
							prodList.get(i).getBrand(),
							prodList.get(i).getPrice(),
							prodList.get(i).isActive(),
							url,
							prodList.get(i).getCategory(),
							prodList.get(i).getQuantity(),
							prodList.get(i).getWeightValue(),
							prodList.get(i).getWeightType()));
				}
			}

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

	public Page<Product> searchProductsAdmin(String page, String prodName, String bran, String cate,String sort) {

		String sortProp = "product_id";
		String order = "desc";
		int pageNumber = 1;

		if ( page != null ) pageNumber = Integer.parseInt(page);

		if ( sort != null) {
			sortProp= sort.split(":")[0];
			order = sort.split(":")[1];
		}

		Pageable pageable = PageRequest.of(pageNumber - 1, 10, Sort.Direction.fromString(order), sortProp);

		String name = "";
		String category = "";
		String brand = "";

		if ( prodName != null ) name = prodName;
		if ( cate != null) category = cate;
		if ( bran != null) brand = bran;

		return repo.findAll(name, category, brand, pageable);
	}

}
