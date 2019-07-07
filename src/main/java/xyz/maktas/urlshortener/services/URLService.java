package xyz.maktas.urlshortener.services;


import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.maktas.urlshortener.Helpers.URLShortenAlgorithm;
import xyz.maktas.urlshortener.entities.URL;
import xyz.maktas.urlshortener.entities.User;
import xyz.maktas.urlshortener.repositories.URLRepo;
import xyz.maktas.urlshortener.repositories.UserRepo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Service
public class URLService {
    @Autowired
    private URLRepo urlRepo;
    @Autowired
    private UserRepo userRepo;

    public URL getUrlByShortId(String shortId){
        return this.urlRepo.findByShortURL(shortId);
    }

    public URL addURL(Long id, URL url){
        UrlValidator urlValidator = new UrlValidator();
        if(!urlValidator.isValid(url.getLongURL())){
            throw new RuntimeException("Invalid URL");
        }

        User user = this.userRepo.findById(id).get();
        URL u = this.urlRepo.findByLongURL(url.getLongURL());
        if(u != null){
            url = u;
        }
        else{
            url.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            String shr = URLShortenAlgorithm.generateShortURL(url.getLongURL());
            url.setShortURL(shr);
        }


        List<URL> urls = this.userRepo.findByEmail(user.getEmail()).get().getUrls();
        if(!urls.contains(url)){
            urls.add(url);
            user.setUrls(urls);
            this.userRepo.save(user);
        }



        return url;
    }


    public List<URL> getURLsByUser(Long id) {
        User user = this.userRepo.findById(id).get();

        return this.urlRepo.findAllByUsersEquals(user);
    }

    public URL deleteURL(Long userid, Long urlsid) {
        User user = this.userRepo.findById(userid).get();
        List<URL> urls = user.getUrls();
        URL url = this.urlRepo.findById(urlsid).get();
        urls.remove(url);
        user.setUrls(urls);
        this.userRepo.save(user);
        return url;
    }

    public String getLongURL(String shortURL) {
        return this.urlRepo.findByShortURL(shortURL).getLongURL();
    }
}
