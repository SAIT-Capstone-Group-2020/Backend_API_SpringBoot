package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.repository.ItemRepository;
import hha.spring.data.dataapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.sql.Date;
import java.util.List;


@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public List<Item> listAllSearch(String keyword, String filter) {
        /**
        if ( filter != null ) {
            String keyword = filter.split(":")[0];
            String operator = filter.split(":")[1];
            String val = filter.split(":")[2];

            String whereClause = "";

            if ( operator.equals("eq") ) whereClause = field + " = " + val;
            else if ( operator.equals("gt") ) whereClause = field + " > " + val;
            else if ( operator.equals("lt") ) whereClause = field + " < " + val;
            else if ( operator.equals("like") ) whereClause = field + " LIKE %" + val + "%";

            return repo.findAll(whereClause, pageable);
        }
        */
        return repo.findBySearchKeyword(keyword);}

    public List<Item> listAllItem(String filter) {
        return repo.listAllItem();}

    public Item listSingleItem(int id) { return repo.findByProductId(id);}

}
