package main.java.com.keldorn.mains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import main.java.com.keldorn.music.Artist;

public class JPA {
    public static void main(String[] args) {
        try (var sessionFactory =
                     Persistence.createEntityManagerFactory(
                             "main.java.com.keldorn.music");
             EntityManager entityManager = sessionFactory.createEntityManager()
        ) {
            var transaction = entityManager.getTransaction();
            transaction.begin();
            Artist artist = entityManager.find(Artist.class, 204);
            System.out.println(artist);
            artist.addAlbum("The Best Of Muddy Waters");
//            artist.removeDuplicates();
            System.out.println(artist);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
