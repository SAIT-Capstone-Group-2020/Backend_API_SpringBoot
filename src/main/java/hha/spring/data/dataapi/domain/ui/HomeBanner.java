package hha.spring.data.dataapi.domain.ui;

import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.domain.ui.tf.TfCurrHoliday;
import hha.spring.data.dataapi.domain.ui.tf.TfCurrHomeBanner;

import javax.persistence.*;

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "HomeBanner.queryCurrentHomeBanner",
                        query = "select product_id, product_name, description, banner_image_url from current_home_banner"
                        , resultClass = CurrentPromotion.class,
                        resultSetMapping = "map_to_current_home_banner"
                ),
                @NamedNativeQuery(
                        name = "HomeBanner.queryCurrentHolidayBanner",
                        query = "select product_id, banner_image_url from current_holiday_banner"
                        , resultClass = CurrentHolidayBanner.class,
                        resultSetMapping = "map_to_current_holiday_banner"
                ),
                @NamedNativeQuery(
                        name = "HomeBanner.queryCurrentPromotion",
                        query = "select product_id, product_name, retail_price, discount_price, banner_image_url from current_promotion"
                        , resultClass = CurrentHolidayBanner.class,
                        resultSetMapping = "map_to_current_promotion"
                ),
                @NamedNativeQuery(
                        name = "HomeBanner.queryTFCurrentHomeBanner",
                        query = "select title, description, banner_image_url from tf_curr_home_banner"
                        , resultClass = TfCurrHomeBanner.class,
                        resultSetMapping = "map_to_tf_current_home_banner"
                ),
                @NamedNativeQuery(
                        name = "HomeBanner.queryTFCurrentHolidayBanner",
                        query = "select banner_image_url from tf_curr_holiday"
                        , resultClass = TfCurrHoliday.class,
                        resultSetMapping = "map_to_tf_current_holiday_banner"
                )

        }
)

@SqlResultSetMappings({

        @SqlResultSetMapping(
                name = "map_to_current_promotion",
                classes = @ConstructorResult(targetClass = CurrentPromotion.class,
                        columns = {
                                @ColumnResult(name = "product_id", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "retail_price", type = Double.class),
                                @ColumnResult(name = "discount_price", type = Double.class),
                                @ColumnResult(name = "banner_image_url", type = String.class)

                        })
        ),
        @SqlResultSetMapping(
                name = "map_to_current_holiday_banner",
                classes = @ConstructorResult(
                        targetClass = CurrentHolidayBanner.class,
                        columns = {
                                @ColumnResult(name = "product_id", type = Integer.class),
                                @ColumnResult(name = "banner_image_url", type = String.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "map_to_current_home_banner",
                classes = @ConstructorResult(
                        targetClass = CurrentHomeBanner.class,
                        columns = {
                                @ColumnResult(name = "product_id", type = Integer.class),
                                @ColumnResult(name = "product_name", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "banner_image_url", type = String.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "map_to_tf_current_home_banner",
                classes = @ConstructorResult(
                        targetClass = TfCurrHomeBanner.class,
                        columns = {
                                @ColumnResult(name = "title", type = String.class),
                                @ColumnResult(name = "description", type = String.class),
                                @ColumnResult(name = "banner_image_url", type = String.class)
                        }
                )
        ),
        @SqlResultSetMapping(
                name = "map_to_tf_current_holiday_banner",
                classes = @ConstructorResult(
                        targetClass = TfCurrHoliday.class,
                        columns = {
                                @ColumnResult(name = "banner_image_url", type = String.class)
                        }
                )
        )
})

@Entity
@Table(name = "home_banner")
public class HomeBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "home_banner_id")
    private Integer homeBannerId;

    @Column(name = "banner_image_url")
    private String bannerImageUrl;

    @ManyToOne
    @JoinColumn(name = "banner_type", nullable = false)
    private BannerType bannerType;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "start_data")
    private java.sql.Date startData;

    @Column(name = "end_data")
    private java.sql.Date endData;


    public Integer getHomeBannerId() {
        return homeBannerId;
    }

    public void setHomeBannerId(Integer homeBannerId) {
        this.homeBannerId = homeBannerId;
    }


    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }


    public BannerType getBannerType() {
        return bannerType;
    }

    public void setBannerType(BannerType bannerType) {
        this.bannerType = bannerType;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product productId) {
        this.product = productId;
    }


    public java.sql.Date getStartData() {
        return startData;
    }

    public void setStartData(java.sql.Date startData) {
        this.startData = startData;
    }


    public java.sql.Date getEndData() {
        return endData;
    }

    public void setEndData(java.sql.Date endData) {
        this.endData = endData;
    }

}
