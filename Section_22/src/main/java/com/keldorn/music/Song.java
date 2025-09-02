package main.java.com.keldorn.music;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Song implements Comparable<Song>{
    @Id
    @Column(name = "song_id")
    private int songId;

    @Column(name = "track_number")
    private int trackNumber;

    @Column(name = "song_title")
    private String songTitle;

    public Song() {
    }

    public Song(String songTitle) {
        this.songTitle = songTitle;
    }

    public Song(int trackNumber, String songTitle) {
        this.trackNumber = trackNumber;
        this.songTitle = songTitle;
    }

    public Song(int songId, int trackNumber, String songTitle) {
        this.songId = songId;
        this.trackNumber = trackNumber;
        this.songTitle = songTitle;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public String getSongTitle() {
        return songTitle;
    }

    @Override
    public String toString() {
        return "Song{" +
               "songId=" + songId +
               ", trackNumber=" + trackNumber +
               ", songTitle=" + songTitle +
               '}';
    }

    @Override
    public int compareTo(Song o) {
        return trackNumber - o.getTrackNumber();
    }
}
