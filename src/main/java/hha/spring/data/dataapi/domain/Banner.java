package hha.spring.data.dataapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "banner"})
@Table
@Entity
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private String objectId;
    private String description;
    private String title;

    @ManyToOne
    private Swipper swipper;

    @Transient
    private String data;

    @Transient
    private String fileName;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Swipper getSwipper() {
        return swipper;
    }

    public void setSwipper(Swipper swipper) {
        this.swipper = swipper;
    }

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

    public Swipper getBanner() {
        return swipper;
    }

    public void setBanner(Swipper swipper) {
        this.swipper = swipper;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }


}
