package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ItemRepository;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public List<Item> listAllSearch(String keyword) { return repo.findBySearchKeyword(keyword);}

}
