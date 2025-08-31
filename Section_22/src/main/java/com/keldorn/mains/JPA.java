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
            // Select
            Artist artist = entityManager.find(Artist.class, 204);
//            Artist artist = new Artist(204, "Muddy Water");
            System.out.println(artist);
            // Update
            artist.setArtistName("Muddy Waters");
//            entityManager.merge(artist);
            // Delete
//            entityManager.remove(artist);
            // Create
//            entityManager.persist(new Artist("Muddy Water"));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
