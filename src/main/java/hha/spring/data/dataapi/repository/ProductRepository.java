package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import hha.spring.data.dataapi.domain.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value =
    "SELECT p.id, p.name, p.description, d.discount_price, p.img_url, c.name, p.weight_value, wt.name, p.brand_name "
            +"FROM Product p JOIN weight_type wt on(wt.id = p.weight_type) JOIN Category c on(c.id = p.category) "
    +"JOIN Discount d on(d.product_id = p.id) JOIN Event e on(e.id = d.event_id) "
    +"WHERE (p.name LIKE CONCAT(`%`, :keyword, `%`) OR c.name LIKE CONCAT(`%`, :keyword, `%`)) "
    +"AND CURDATE() >= e.star_tdate and CURDATE() < e.end_date + INTERVAL 1 day "
    +"AND p.active = 1 "
            +"UNION SELECT p.id, p.name, p.description, p.retail_price, p.img_url, c.name, p.weight_value, wt.name, p.brand_name "
            +"FROM weight_type wt JOIN Product p on(wt.id = p.weight_type) JOIN Category c on(c.id = p.category) "
    +"WHERE p.id NOT IN (SELECT product_id FROM Discount d JOIN Event e on(e.id = d.event_id) "
    +"WHERE CURDATE() >= e.star_tdate and CURDATE() < e.end_date + INTERVAL 1 day) "
    +"AND (p.name LIKE CONCAT(`%`, :keyword, `%`) OR c.name LIKE CONCAT(`%`, :keyword, `%`)) "
    +"AND p.active = 1 "
            , nativeQuery = true)
    Item findBySearchKeyword(String keyword);

}
