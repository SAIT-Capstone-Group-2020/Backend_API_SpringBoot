package hha.spring.data.dataapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
//@SqlResultSetMapping(
//        name = "allBannerInfoMapping",
//        classes = @ConstructorResult(
//                targetClass = BannerItem.class,
//                columns = {
//                        @ColumnResult(name = "Banner_id"),
//                        @ColumnResult(name = "title"),
//                        @ColumnResult(name = "description"),
//                        @ColumnResult(name = "url"),
//                }
//        )
//)
//
//@NamedNativeQuery(name = "allBannerInfo",
//        resultSetMapping = "allBannerInfoMapping",
//        resultClass = BannerItem.class,
//        query = "select * from ("
//                + "select hb.home_banner_id as banner_id , hb.banner_image_url as url ,"
//                + " bt.banner_type as type   from Home_banner as hb  left join Banner_type as "
//                + "bt on hb.home_banner_id = hb.banner_type_id "
//                + "WHERE (CURRENT_DATE >= hb.start_date AND CURRENT_DATE < hb.end_date + INTERVAL 1 DAY))"
//)
@JsonIgnoreProperties({"hibernateLazyInitializer", "banner"})
@Table(name = "BannerItem")
public class BannerItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String objectId;

    @ManyToOne
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @Transient
    private String data;

    @Transient
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


}
