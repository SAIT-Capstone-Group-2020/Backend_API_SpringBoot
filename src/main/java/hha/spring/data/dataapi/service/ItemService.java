package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

/**
 * This class is a business logic to manage Item data object
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository repo;

    public Page<Item> listKeywordSearch(String keyword, String price, String category, String sort, String page) {

        String sortProp = "product_id";
        String order = "desc";
        int pageNumber = 1;
        int contentNumber = 999;

        String key = "";
        double gt = 0;
        double lt = 9999.99;
        String cate = "";

        if(keyword != null) {
            key = keyword.toLowerCase(Locale.ROOT);
        }

        if ( price != null ) {
            gt = Double.parseDouble(price.split(":")[0]);
            lt = Double.parseDouble(price.split(":")[1]);
        }

        if(category != null) {
            cate = category.toLowerCase(Locale.ROOT);
        }

        if(sort != null) {
            //discount_price, product_id, product_name
            sortProp= sort.split(":")[0];
            order = sort.split(":")[1];
        }

        if ( page != null ) {
            pageNumber = Integer.parseInt(page);
            contentNumber = 5;
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, contentNumber, Sort.Direction.fromString(order), sortProp);

        return repo.findBySearchKeyword(key, gt, lt, cate, pageable);}

    public Page<Item> listKeywordSearchProm(String keyword, String price, String category, String sort, String page) {

        String sortProp = "product_id";
        String order = "desc";
        int pageNumber = 1;

        String key = "";
        double gt = 0;
        double lt = 9999.99;
        String cate = "";

        if(keyword != null) {
            key = keyword.toLowerCase(Locale.ROOT);
        }

        if ( price != null ) {
            gt = Double.parseDouble(price.split(":")[0]);
            lt = Double.parseDouble(price.split(":")[1]);
        }

        if(category != null) {
            cate = category.toLowerCase(Locale.ROOT);
        }

        if(sort != null) {
            //discount_price, product_id, product_name
            sortProp= sort.split(":")[0];
            order = sort.split(":")[1];
        }

        if ( page != null ) {
            pageNumber = Integer.parseInt(page);
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 999, Sort.Direction.fromString(order), sortProp);

        return repo.findBySearchKeywordProm(key, gt, lt, cate, pageable);}

    public List<Item> listAllItem() {
        return repo.listAllItem();}

    public Item listSingleItem(int id) { return repo.findByProductId(id);}

}
