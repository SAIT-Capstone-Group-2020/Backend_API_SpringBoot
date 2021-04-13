package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * This class is a business logic to manage product data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class ProductService {

	@Autowired
    private ProductRepository repo;

	@Autowired
	private AwsS3Service s3;

	public List<Product> listAllProducts() {
		return repo.findAll();
	}

	public Product findByName(String name) {
		return repo.findByName(name);
	}

	public List<Product> addProduct(Product prod, MultipartFile file) {

		String url = "";

		if(file.isEmpty()) {
			url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		}

		else {
			Long time = new Date().getTime();

			String fileName = String.valueOf(time)+file.getOriginalFilename();

			url = "https://" + s3.getBucketName() + ".s3." + s3.getRegionName() + ".amazonaws.com/" + fileName;

			try {
				s3.upload(fileName, file.getBytes());

			} catch (IOException e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}

		prod.setActive(true);
		prod.setImage(url);

		try {
			repo.save(prod);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return repo.findAll();
	}

	public List<Product> addProductBulk(List<Product> prodList) {

		String url = "https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png";
		//need to make feature upload image file to the cloud

		try {

			for(int i=0; i <prodList.size(); i++) {

				if(prodList.get(i).getId() != 0) {
					Product prod = repo.findById(prodList.get(i).getId());
					if(prod != null) {
						repo.save(prodList.get(i)); //fix to update data
					}
					else {
						throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong product id:"+ prodList.get(i).getId());
					}
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

		return repo.findAll();
	}

	public Product findById(int id) {
		return repo.findById(id);
	}

	public List<Product> removeProduct(Product prod) {

		try {
			repo.delete(prod);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return repo.findAll();
	}


	public List<Product> editProduct(
			int id,
			String name,
			String description,
			String brand,
			double price,
			Boolean active,
			int category,
			int quantity,
			double weightValue,
			int weightType,
			MultipartFile file
	) {

		try {
			Product prod = repo.findById(id);

			prod.setName(name);
			prod.setDescription(description);
			prod.setBrand(brand);
			prod.setPrice(price);
			prod.setActive(active);
			prod.setCategory(category);
			prod.setQuantity(quantity);
			prod.setWeightValue(weightValue);
			prod.setWeightType(weightType);

			String url = "";

			if(!file.isEmpty()) {

				String key = prod.getImage().split("/")[3];
				System.out.println(key);

				if(!key.equals("dev_image.png")) s3.delete(key);

				Long time = new Date().getTime();

				String fileName = String.valueOf(time)+file.getOriginalFilename();

				url = "https://" + s3.getBucketName() + ".s3." + s3.getRegionName() + ".amazonaws.com/" + fileName;

				try {
					s3.upload(fileName, file.getBytes());

				} catch (IOException e) {
					throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
				}

				prod.setImage(url);
			}

			repo.save(prod);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}

		return repo.findAll();
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

		Pageable pageable = PageRequest.of(pageNumber - 1, 999, Sort.Direction.fromString(order), sortProp);

		String name = "";
		String category = "";
		String brand = "";

		if ( prodName != null ) name = prodName;
		if ( cate != null) category = cate;
		if ( bran != null) brand = bran;

		return repo.findAll(name, category, brand, pageable);
	}

}
