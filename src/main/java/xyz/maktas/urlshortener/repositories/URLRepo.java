package xyz.maktas.urlshortener.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.maktas.urlshortener.entities.URL;
import xyz.maktas.urlshortener.entities.User;

import java.util.List;

/**
 * @Project urlshortener
 * @Author Muammer Matthew Aktas
 * @Date 7/4/2019
 */
@Repository
public interface URLRepo extends JpaRepository<URL, Long> {
    URL findByShortURL(String shortId);
    List<URL> findAllByUsersEquals(User user);

    boolean existsByShortURL(String shr);

    URL findByLongURL(String longURL);
}
