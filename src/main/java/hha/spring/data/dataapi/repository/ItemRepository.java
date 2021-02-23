package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(value =
            "SELECT * FROM "
                    +"(SELECT p.product_id AS product_id, p.product_name AS product_name, p.description AS description, p.retail_price as original_price, TRUE AS is_discount, d.discount_price AS discount_price, p.image_url AS image_url, c.category_name AS category_name, p.quantity AS quantity, p.weight_value AS weight_value, wt.weight_type_name AS weight_type_name, p.brand_name AS brand_name "
                    +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                    +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "

                    +"WHERE "
                    +"(p.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR c.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR p.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                    //+"AND (d.discount_price >= ?2 AND d.discount_price <= ?3)"
                    +"AND (c.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                    +"AND CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                    +"AND p.active = 1 "

                    +"UNION ALL "
                    +"SELECT pp.product_id AS product_id, pp.product_name AS product_name, pp.description AS description, pp.retail_price AS original_price, FALSE AS is_discount, pp.retail_price AS discount_price, pp.image_url AS image_url, cc.category_name AS category_name, pp.quantity AS quantity, pp.weight_value AS weight_value, wtt.weight_type_name AS weight_type_name, pp.brand_name AS brand_name "
                    +"FROM product AS pp JOIN weight_type AS wtt on(wtt.weight_type_id = pp.weight_type_id) JOIN category AS cc on(cc.category_id = pp.category_id) "
                    +"WHERE pp.product_id NOT IN (SELECT dd.product_id FROM discount AS dd JOIN event AS ee on(ee.event_id = dd.event_id) "
                    +"WHERE (CURRENT_DATE >= ee.start_date AND CURRENT_DATE < ee.end_date + INTERVAL 1 DAY)) "

                    +"AND (pp.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR cc.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR pp.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                    //+"AND (pp.retail_price >= ?2 AND pp.retail_price <= ?3)"
                    +"AND (cc.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                    +"AND pp.active = 1) AS s1"
            ,countQuery =
            "SELECT COUNT(*) FROM "
                    +"(SELECT p.product_id AS product_id "
                    +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                    +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "

                    +"WHERE "
                    +"(p.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR c.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR p.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                    //+"AND (d.discount_price >= ?2 AND d.discount_price <= ?3)"
                    +"AND (c.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                    +"AND CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                    +"AND p.active = 1 "

                    +"UNION ALL "
                    +"SELECT pp.product_id AS product_id "
                    +"FROM product AS pp JOIN weight_type AS wtt on(wtt.weight_type_id = pp.weight_type_id) JOIN category AS cc on(cc.category_id = pp.category_id) "
                    +"WHERE pp.product_id NOT IN (SELECT dd.product_id FROM discount AS dd JOIN event AS ee on(ee.event_id = dd.event_id) "
                    +"WHERE (CURRENT_DATE >= ee.start_date AND CURRENT_DATE < ee.end_date + INTERVAL 1 DAY)) "

                    +"AND (pp.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR cc.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR pp.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                    //+"AND (pp.retail_price >= ?2 AND pp.retail_price <= ?3)"
                    +"AND (cc.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                    +"AND pp.active = 1) AS s1"
            ,nativeQuery = true)
    Page<Item> findBySearchKeyword(String keyword, double gt, double lt, String category, Pageable pageable);

        @Query(value =
                        "SELECT p.product_id AS product_id, p.product_name AS product_name, p.description AS description, p.retail_price as original_price, TRUE AS is_discount, d.discount_price AS discount_price, p.image_url AS image_url, c.category_name AS category_name, p.quantity AS quantity, p.weight_value AS weight_value, wt.weight_type_name AS weight_type_name, p.brand_name AS brand_name "
                        +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                        +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "

                        +"WHERE "
                        +"(p.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR c.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR p.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                        //+"AND (d.discount_price >= ?2 AND d.discount_price <= ?3)"
                        +"AND (c.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                        +"AND CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                        +"AND p.active = 1 "
                ,countQuery =
                        "SELECT p.product_id AS product_id "
                        +"FROM product AS p JOIN weight_type AS wt on(wt.weight_type_id = p.weight_type_id) JOIN category AS c on(c.category_id = p.category_id) "
                        +"JOIN discount AS d on(d.product_id = p.product_id) JOIN event AS e on(e.event_id = d.event_id) "

                        +"WHERE "
                        +"(p.product_name LIKE LOWER(CONCAT('%', ?1, '%')) OR c.category_name LIKE LOWER(CONCAT('%', ?1, '%')) OR p.brand_name LIKE LOWER(CONCAT('%', ?1, '%'))) "
                        //+"AND (d.discount_price >= ?2 AND d.discount_price <= ?3)"
                        +"AND (c.category_name LIKE LOWER(CONCAT('%', ?4, '%'))) "

                        +"AND CURRENT_DATE >= e.start_date and CURRENT_DATE < e.end_date + INTERVAL 1 DAY "
                        +"AND p.active = 1 "
                ,nativeQuery = true)
        Page<Item> findBySearchKeywordProm(String keyword, double gt, double lt, String category, Pageable pageable);

    @Query(nativeQuery = true, name = "allItemDataMapping")
    List<Item> listAllItem();

    @Query(nativeQuery = true, name = "singleItemDataMapping")
    Item findByProductId(int id);
}