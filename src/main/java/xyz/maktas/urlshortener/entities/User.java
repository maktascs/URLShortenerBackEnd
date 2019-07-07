package xyz.maktas.urlshortener.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;


    private String firstname;
    private String lastname;
    @Email
    @Column(unique = true, length = 255)
    private String email;
    private String password;
    private int enabled;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(unique = true, length = 255)
    private String username;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="user_urls", joinColumns = {@JoinColumn(name="userid")},inverseJoinColumns = {@JoinColumn(name="urlsid")})
    private List<URL> urls;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    public User(){}

    public User(String firstname, String lastname, String email, String password, int enabled, Date createdAt, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;


        this.username = username;
    }

    public User(User user) {
        this.email=user.getEmail();
        this.firstname=user.getFirstname();
        this.lastname=user.getLastname();
        this.userid=user.getUserid();
        this.password=user.getPassword();
        this.createdAt=user.getCreatedAt();
        this.enabled=user.getEnabled();
        this.roles = user.getRoles();
        this.username = user.getUsername();
    }

    public String getUsername() {
        return this.username;
    }
    private void setUsername(String username){
        this.username=username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<URL> getUrls() {
        return urls;
    }

    public void setUrls(List<URL> urls) {
        this.urls = urls;
    }
}
