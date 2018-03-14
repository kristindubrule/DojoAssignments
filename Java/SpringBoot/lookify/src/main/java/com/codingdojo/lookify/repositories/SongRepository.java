package com.codingdojo.lookify.repositories;

import com.codingdojo.lookify.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SongRepository extends CrudRepository<Song,Long> {
    ArrayList<Song> findAll();
    ArrayList<Song> findByArtistContaining(String search);
    ArrayList<Song> findFirst10ByOrderByRatingDesc();
}
