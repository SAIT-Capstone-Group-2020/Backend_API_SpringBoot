package hha.spring.data.dataapi.domain.ui;

import com.fasterxml.jackson.annotation.JsonFormat;
import hha.spring.data.dataapi.domain.ui.data.CurrHoliday;
import hha.spring.data.dataapi.domain.ui.data.CurrHomeBanner;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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


    public void addHomeBannerItem(HomeBannerItem item) {
        homeBannerItems.add(item);
        item.setHomeBanner(this);
    }

    public void removeHomeBannerItem(HomeBannerItem item) {
        homeBannerItems.remove(item);
        item.setHomeBanner(null);
    }

    public List<HomeBannerItem> getHomeBannerItems() {
        return homeBannerItems;
    }

    public void setHomeBannerItems(List<HomeBannerItem> homeBannerItems) {
        this.homeBannerItems = homeBannerItems;
    }

    public Integer getHomeBannerId() {
        return homeBannerId;
    }

    public void setHomeBannerId(Integer homeBannerId) {
        this.homeBannerId = homeBannerId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startData) {
        this.startDate = startData;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endData) {
        this.endDate = endData;
    }
}
