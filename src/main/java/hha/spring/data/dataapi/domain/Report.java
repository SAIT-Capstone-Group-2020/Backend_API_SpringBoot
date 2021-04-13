package hha.spring.data.dataapi.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is a Data Entity that bridges between
 * relational database and Java object
 * by using Java Persistence API
 *
 * retrieve report data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Entity
@SqlResultSetMapping(
        name = "ReportProdDataMapping",
        classes = @ConstructorResult(
                targetClass = Report.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "name"),
                        @ColumnResult(name = "sales"),
                        @ColumnResult(name = "qty"),
                        @ColumnResult(name = "paid_date")

                }
        )
)

public class Report {

    @Id
    private int id;
    private String name;
    private double sales;
    private int qty;
    private Date paid_date;

    public Report() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSales() {
        return sales;
    }

    public int getQty() {
        return qty;
    }

    public Date getPaid_date() {
        return paid_date;
    }
}
