package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Page<Product> listAllProducts(String page, String sortField, String filter) {
		Sort sort = Sort.by(sortField);
		sort = sort.ascending();
		
		int pageNumber = 1;
		if ( page != null ) pageNumber = Integer.parseInt(page);
		Pageable pageable = PageRequest.of(pageNumber - 1, 10, sort);
		
		if ( filter != null ) {
//			String field = filter.split(":")[0];
//			String operator = filter.split(":")[1];
			String val = filter.split(":")[2];
			return repo.findAll(val, pageable);
		}
		
		return repo.findAll(pageable);
	}

}
