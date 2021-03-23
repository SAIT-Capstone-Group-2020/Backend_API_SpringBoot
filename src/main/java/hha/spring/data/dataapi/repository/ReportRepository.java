package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT oi.product_id AS id, p.product_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, YEARWEEK(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) "
            +"WHERE (YEARWEEK(o.paid_date) >= YEARWEEK(?1) AND YEARWEEK(o.paid_date) <= YEARWEEK(?2)) AND oi.product_id = ?3 "
            +"GROUP BY YEARWEEK(o.paid_date) "
            +"ORDER BY 5 ",
            nativeQuery = true)
    List<Map<String, Object>> prodReport(Date startYear, Date endYear, int id);

    @Query(value = "SELECT oi.product_id AS id, p.product_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, MONTH(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) "
            +"WHERE (o.paid_date >= ?1 AND o.paid_date < ?2) AND oi.product_id = ?3 "
            +"GROUP BY MONTH(o.paid_date) "
            +"ORDER BY 5 ",
            nativeQuery = true)
    List<Map<String, Object>> prodReportMonth(Date startYear, Date endYear, int id);

    @Query(value = "SELECT p.category_id AS id, c.category_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, YEARWEEK(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) JOIN category AS c ON(p.category_id = c.category_id) "
            +"WHERE (YEARWEEK(o.paid_date) >= YEARWEEK(?1) AND YEARWEEK(o.paid_date) <= YEARWEEK(?2)) AND oi.product_id = ?3 "
            +"GROUP BY YEARWEEK(o.paid_date) "
            +"ORDER BY 5 ",
            nativeQuery = true)
    List<Map<String, Object>> cateReport(Date startYear, Date endYear, int id);

    @Query(value = "SELECT p.category_id AS id, c.category_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, MONTH(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) JOIN category AS c ON(p.category_id = c.category_id) "
            +"WHERE (o.paid_date >= ?1 AND o.paid_date < ?2) AND p.category_id = ?3 "
            +"GROUP BY MONTH(o.paid_date) "
            +"ORDER BY 5 ",
            nativeQuery = true)
    List<Map<String, Object>> cateReportMonth(Date startYear, Date endYear, int id);

    @Query(value = "SELECT oi.product_id AS id, p.product_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, YEARWEEK(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) "
            +"WHERE YEARWEEK(o.paid_date) = ?1 AND oi.product_id = ?2 "
            +"GROUP BY oi.product_id, p.product_name "
            +"ORDER BY 1 ",
            nativeQuery = true)
    Map<String, Object> periodReportProdWeek(String date, int id);

    @Query(value = "SELECT oi.product_id AS id, p.product_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, MONTH(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) "
            +"WHERE (o.paid_date >= ?1 AND o.paid_date < ?2) AND MONTH(o.paid_date) = ?3 AND p.product_id = ?4 "
            +"GROUP BY oi.product_id, p.product_name ",
            nativeQuery = true)
    Map<String, Object> periodReportProdMonth(Date startYear, Date endYear, String month, int id);

    @Query(value = "SELECT p.category_id AS id, c.category_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, YEARWEEK(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) JOIN category AS c ON(p.category_id = c.category_id) "
            +"WHERE YEARWEEK(o.paid_date) = ?1 AND oi.product_id = ?2 "
            +"GROUP BY p.category_id, c.category_name ",
            nativeQuery = true)
    Map<String, Object> periodCateReport(String date, int id);

    @Query(value = "SELECT p.category_id AS id, c.category_name AS name, "
            +"TRUNCATE(SUM(oi.total_price),2) AS sales, SUM(oi.qty) AS qty, MONTH(o.paid_date) AS paid_date "
            +"FROM orders AS o JOIN order_items AS oi on(o.orders_id = oi.orders_id) JOIN product AS p on(p.product_id = oi.product_id) JOIN category AS c ON(p.category_id = c.category_id) "
            +"WHERE (o.paid_date >= ?1 AND o.paid_date < ?2) AND MONTH(o.paid_date) = ?3 AND p.category_id = ?4 "
            +"GROUP BY p.category_id, c.category_name ",
            nativeQuery = true)
    Map<String, Object> periodReportCateMonth(Date startYear, Date endYear, String month, int id);

}
