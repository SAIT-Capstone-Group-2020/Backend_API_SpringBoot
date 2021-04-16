package hha.spring.data.dataapi.domain.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * The type Home banner.
 */
@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "HomeBanner.queryCurrentHomeBanner",
                        query = "select title, description, banner_image_url from curr_home_banner"
                        , resultClass = CurrHomeBanner.class,
                        resultSetMapping = "map_to_tf_current_home_banner"
                ),
                @NamedNativeQuery(
                        name = "HomeBanner.queryCurrentHolidayBanner",
                        query = "select banner_image_url from curr_holiday"
                        , resultClass = CurrHoliday.class,
                        resultSetMapping = "map_to_tf_current_holiday_banner"
                )

        }
)

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "map_to_tf_current_home_banner",
                classes = @ConstructorResult(
                        targetClass = CurrHomeBanner.class,
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
                        targetClass = CurrHoliday.class,
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

    @Column(name = "comment")
    private String comment;

    @Column(name = "begin_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private java.sql.Date endDate;

    @OneToMany(targetEntity = HomeBannerItem.class, mappedBy = "homeBanner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HomeBannerItem> homeBannerItems;


    /**
     * Add home banner item.
     *
     * @param item the item
     */
    public void addHomeBannerItem(HomeBannerItem item) {
        homeBannerItems.add(item);
        item.setHomeBanner(this);
    }

    /**
     * Remove home banner item.
     *
     * @param item the item
     */
    public void removeHomeBannerItem(HomeBannerItem item) {
        homeBannerItems.remove(item);
        item.setHomeBanner(null);
    }

    /**
     * Gets home banner items.
     *
     * @return the home banner items
     */
    public List<HomeBannerItem> getHomeBannerItems() {
        return homeBannerItems;
    }

    /**
     * Sets home banner items.
     *
     * @param homeBannerItems the home banner items
     */
    public void setHomeBannerItems(List<HomeBannerItem> homeBannerItems) {
        this.homeBannerItems = homeBannerItems;
    }

    /**
     * Gets home banner id.
     *
     * @return the home banner id
     */
    public Integer getHomeBannerId() {
        return homeBannerId;
    }

    /**
     * Sets home banner id.
     *
     * @param homeBannerId the home banner id
     */
    public void setHomeBannerId(Integer homeBannerId) {
        this.homeBannerId = homeBannerId;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startData the start data
     */
    public void setStartDate(Date startData) {
        this.startDate = startData;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endData the end data
     */
    public void setEndDate(Date endData) {
        this.endDate = endData;
    }
}
