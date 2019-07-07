package xyz.maktas.urlshortener.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.maktas.urlshortener.entities.URL;
import xyz.maktas.urlshortener.services.URLService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@RestController
@RequestMapping("/urls")
@CrossOrigin(origins = "http://localhost:4200")
public class URLController {
    @Autowired
    private URLService urlService;
    @CrossOrigin
    @GetMapping("/{userid}/all")
    public List<URL> getURLsByUser(@PathVariable("userid") Long id){
        return this.urlService.getURLsByUser(id);
    }

    @PostMapping("/{userid}/add")
    public URL addUrl(@PathVariable("userid") Long id, @RequestBody URL url){
        //System.out.println(url.getLongURL());
        return this.urlService.addURL(id,url);
    }
    @DeleteMapping("/{userid}/{urlsid}/delete")
    public URL deleteURL(@PathVariable("userid") Long userid, @PathVariable("urlsid") Long urlsid){
        return this.urlService.deleteURL(userid, urlsid);
    }

    @GetMapping("/getUrl/{shortURL}")
    public Map<String, String> getLongURL(@PathVariable("shortURL") String shortURL){
        Map<String,String> map = new HashMap<>();
        map.put("longURL", this.urlService.getLongURL(shortURL));
        return map;
    }
}
