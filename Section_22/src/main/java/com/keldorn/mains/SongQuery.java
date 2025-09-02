package main.java.com.keldorn.mains;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.*;
import main.java.com.keldorn.music.Album;
import main.java.com.keldorn.music.Artist;
import main.java.com.keldorn.music.Song;

import java.util.stream.Stream;

public class SongQuery {
    public static void main(String[] args) {
        try (EntityManagerFactory emf =
                     Persistence.createEntityManagerFactory("main.java.com.keldorn.music");
             EntityManager em = emf.createEntityManager()
        ) {
            var transaction = em.getTransaction();
            transaction.begin();
            String matchedValue = "%Storm%";
            System.out.println("JPQL Solution");
            getDataBySongJPQL(em, matchedValue)
                    .forEach(t -> System.out.printf("ArtistName = %s, AlbumName = %s, SongTitle = %s%n",
                            t.get("artistName"), t.get("albumName"), t.get("songTitle")));

            System.out.println("CriteriaBuilder Solution");
            getDataBySongBuilder(em, matchedValue)
                    .forEach(t -> System.out.printf("ArtistName = %s, AlbumName = %s, SongTitle = %s%n",
                            t.get("artistName"), t.get("albumName"), t.get("songTitle")));

            System.out.println("SQL Solution");
            getDataBySongSQL(em, matchedValue)
                    .forEach(t -> System.out.printf("ArtistName = %s, AlbumName = %s, SongTitle = %s%n",
                            t.get("artistName"), t.get("albumName"), t.get("songTitle")));

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Stream<Tuple> getDataBySongJPQL(EntityManager em, String matchedValue) {
        String jpql = "SELECT artist.artistName artistName, album.albumName albumName, song.songTitle songTitle " +
                      "FROM Artist artist JOIN albums album JOIN songs song " +
                      "WHERE song.songTitle LIKE ?1";
        var query = em.createQuery(jpql, Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultStream();
    }

    private static Stream<Tuple> getDataBySongBuilder(EntityManager em, String matchedValue) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();
        Root<Artist> artistRoot = criteriaQuery.from(Artist.class);
        Join<Artist, Album> albumJoin = artistRoot.join("albums", JoinType.INNER);
        Join<Album, Song> songJoin = albumJoin.join("songs", JoinType.INNER);

        criteriaQuery
                .multiselect(
                        artistRoot.get("artistName").alias("artistName"),
                        albumJoin.get("albumName").alias("albumName"),
                        songJoin.get("songTitle").alias("songTitle"))
                .where(builder.like(songJoin.get("songTitle"), matchedValue));
        return em.createQuery(criteriaQuery).getResultStream();
    }

    private static Stream<Tuple> getDataBySongSQL(EntityManager em, String matchedValue) {
        var query = em.createNativeQuery(
                "SELECT artist.artist_name AS artistName, album.album_name AS albumName, song.song_title AS songTitle " +
                "FROM artists artist " +
                "INNER JOIN albums album ON artist.artist_id = album.artist_id " +
                "INNER JOIN music.songs song on album.album_id = song.album_id " +
                "WHERE song.song_title LIKE ?1", Tuple.class);
        query.setParameter(1, matchedValue);
        return query.getResultStream();
    }
}
