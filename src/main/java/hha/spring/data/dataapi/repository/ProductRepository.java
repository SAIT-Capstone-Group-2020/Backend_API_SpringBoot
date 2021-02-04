package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
    "SELECT p.id, p.name, p.description, d.discount_price, p.image_url, c.name, p.weight_value, wt.name, p.brand_name "
            +"FROM product p JOIN weight_type wt on(wt.id = p.weight_type) JOIN category c on(c.id = p.category) "
    +"JOIN discount d on(d.product_id = p.id) JOIN event e on(e.id = d.event_id) "
    +"WHERE (p.name LIKE LOWER(CONCAT('%', :keyword, '%')) OR c.name LIKE LOWER(CONCAT('%', :keyword, '%'))) "
    +"AND CURDATE() >= e.start_date and CURDATE() < e.end_date + INTERVAL 1 day "
    +"AND p.active = 1 "
            +"UNION SELECT pp.id, pp.name, pp.description, pp.retail_price, pp.image_url, cc.name, pp.weight_value, wtt.name, pp.brand_name "
            +"FROM weight_type wtt JOIN product pp on(wtt.id = pp.weight_type) JOIN category cc on(cc.id = pp.category) "
    +"WHERE pp.id NOT IN (SELECT dd.product_id FROM discount dd JOIN event ee on(ee.id = dd.event_id) "
    +"WHERE CURDATE() >= ee.start_date and CURDATE() < ee.end_date + INTERVAL 1 day) "
    +"AND (pp.name LIKE LOWER(CONCAT('%', :keyword, '%')) OR cc.name LIKE LOWER(CONCAT('%', :keyword, '%'))) "
    +"AND pp.active = 1 "
            , nativeQuery = true)
    Item findBySearchKeyword(String keyword);

}
