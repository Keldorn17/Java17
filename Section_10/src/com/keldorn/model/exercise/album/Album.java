package com.keldorn.model.exercise.album;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private final String name;
    private final String artist;
    private final ArrayList<Song> songs;

    public Album(String artist, String name) {
        this.artist = artist;
        this.name = name;
        songs = new ArrayList<>();
    }

    public boolean addSong(String title, double duration) {
        boolean saved = false;
        if (findSong(title) == null) {
            saved = true;
            songs.add(new Song(title, duration));
        }
        return saved;
    }

    private Song findSong(String title) {
        Song result = null;
        for (var song : songs) {
            if (song.getTitle().equalsIgnoreCase(title)) {
                result = song;
            }
        }
        return result;
    }

    public boolean addToPlayList(int trackNumber, LinkedList<Song> playList) {
        boolean saved = false;
        int index = trackNumber - 1;
        if ((index >= 0) && (index <= songs.size())) {
            playList.add(songs.get(index));
            saved = true;
        }
        return saved;
    }

    public boolean addToPlayList(String title, LinkedList<Song> playList) {
        boolean saved = false;
        Song checkedSong = findSong(title);
        if (checkedSong != null) {
            playList.add(checkedSong);
            saved = true;
        }
        return saved;
    }
}
