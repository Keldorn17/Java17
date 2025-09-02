package main.java.com.keldorn.music;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albumId;

    @Column(name = "album_name")
    private String albumName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "album_id")
    private List<Song> songs = new ArrayList<>();

    public Album() {
    }

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public Album(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
//        songs.sort(Comparator.naturalOrder());
        Collections.sort(songs);
//        songs.sort(Comparator.comparing(Song::getTrackNumber)); // If Songs didn't implement Comparator or Comparable
        StringBuilder toStringBuilder = new StringBuilder();
        songs.forEach(song -> toStringBuilder.append("\n").append(song));
        return "Album{" +
               "albumId=" + albumId +
               ", albumName='" + albumName + '\'' +
               ", songs='" + toStringBuilder + '\'' +
               '}';
    }

    @Override
    public int compareTo(Album o) {
        return this.albumName.compareTo(o.getAlbumName());
    }
}
