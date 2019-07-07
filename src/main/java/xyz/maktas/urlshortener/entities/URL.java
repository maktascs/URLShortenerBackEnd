package xyz.maktas.urlshortener.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Entity
public class URL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long urlsid;
    @NotEmpty
    @Column(unique = true)
    private String longURL;
    @Column(unique = true)
    private String shortURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "urls")
    @JsonIgnore
    private List<User> users;

    private URL(){}
    public URL(@NotEmpty String longURL, @NotEmpty String shortURL, @NotEmpty Date createdAt) {
        this.longURL = longURL;
        this.shortURL = shortURL;
        this.createdAt = createdAt;
    }

    public long getUrlsid() {
        return urlsid;
    }

    public void setUrlsid(long urlsid) {
        this.urlsid = urlsid;
    }

    public String getLongURL() {
        return longURL;
    }

    public void setLongURL(String longURL) {
        this.longURL = longURL;
    }

    public String getShortURL() {
        return shortURL;
    }

    public void setShortURL(String shortURL) {
        this.shortURL = shortURL;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
