package hha.spring.data.dataapi.domain;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@SqlResultSetMapping(
        name = "allBannerInfoMapping",
        classes = @ConstructorResult(
                targetClass = BannerItem.class,
                columns = {
                        @ColumnResult(name = "Banner_id"),
                        @ColumnResult(name = "title"),
                        @ColumnResult(name = "description"),
                        @ColumnResult(name = "url"),
                }
        )
)

@NamedNativeQuery(name = "allBannerInfo", resultClass = BannerItem.class,
        query = "select * from ("
                + "select hb.home_banner_id as banner_id , hb.banner_image_url as url ,"
                + " bt.banner_type as type   from Home_banner as hb  left join Banner_type as "
                + "bt on hb.home_banner_id = hb.banner_type_id "
                + "WHERE (CURRENT_DATE >= hb.start_date AND CURRENT_DATE < hb.end_date + INTERVAL 1 DAY))"
)

@Table(name = "BannerItem")
public class BannerItem {

    @Id
    private int banner_id;

    private String type;

    private String url;
}
