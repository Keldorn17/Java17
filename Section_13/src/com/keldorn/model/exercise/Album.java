package com.keldorn.model.exercise;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private final String name;
    private final String artist;
    private final SongList songs;

    public Album(String artist, String name) {
        this.artist = artist;
        this.name = name;
        songs = new SongList();
    }

    public boolean addSong(String title, double duration) {
        return songs.add(new Song(title, duration));
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        boolean saved = false;
        Song checkedSong = songs.findSong(trackNumber);
        if (checkedSong != null) {
            playList.add(checkedSong);
            saved = true;
        }
        return saved;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        boolean saved = false;
        Song checkedSong = songs.findSong(title);
        if (checkedSong != null) {
            playList.add(checkedSong);
            saved = true;
        }
        return saved;
    }

    public static class SongList {
        private final ArrayList<Song> songs;

        private SongList() {
            songs = new ArrayList<>();
        }

        private boolean add(Song song) {
            boolean result = false;
            Song findSongResult = findSong(song.getTitle());
            if (findSongResult == null) {
                this.add(song);
                result = true;
            }
            return result;
        }

        private Song findSong(String title) {
            Song result = null;
            for (var song : songs) {
                if (song.getTitle().equals(title)) {
                    result = song;
                    break;
                }
            }
            return result;
        }

        private Song findSong(int trackNumber) {
            int index = trackNumber - 1;
            return ((index <= songs.size()) && (index >= 0)) ? songs.get(index) : null;
        }
    }
}
