package com.codingdojo.lookify.services;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.repositories.SongRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void createSong(Song song) {
        songRepository.save(song);
    }


    public ArrayList<Song> allSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> findSongById(Long id) {
        return songRepository.findById(id);
    }

    public boolean deleteSong(Long id) {
        try {
            songRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public ArrayList<Song> songsByArtist(String search) {
        return songRepository.findByArtistContaining(search);
    }

    public ArrayList<Song> findTop10() {
        return songRepository.findFirst10ByOrderByRatingDesc();
    }
}
