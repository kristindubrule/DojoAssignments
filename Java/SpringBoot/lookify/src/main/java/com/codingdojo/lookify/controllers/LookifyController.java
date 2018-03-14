package com.codingdojo.lookify.controllers;

import com.codingdojo.lookify.models.Song;
import com.codingdojo.lookify.services.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.codingdojo.lookify.models.Song;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class LookifyController {
    private final SongService songService;

    public LookifyController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, @ModelAttribute("errors") String errors) {
        model.addAttribute("songs",songService.allSongs());
        return "dashboard";
    }

    @RequestMapping("/songs/new")
    public String newSong(@ModelAttribute("song") Song song) {
        return "new";
    }

    @PostMapping("/songs/new")
    public String addSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {
        if (result.hasErrors()) {
            return "new";
        } else {
            songService.createSong(song);
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/songs/{id}")
    public String showSong(@PathVariable(name="id") Long id, RedirectAttributes redirectAttributes, Model model) {
        Optional<Song> song = songService.findSongById(id);
        if (song.isPresent()) {
            model.addAttribute("song",song.get());
        } else {
            redirectAttributes.addFlashAttribute("errors","Song not found.");
            return "redirect:/dashboard";
        }
        return "show";
    }

    @RequestMapping("/songs/delete/{id}")
    public String deleteSong(@PathVariable(name="id") Long id, RedirectAttributes redirectAttributes, Model model) {
        if (!songService.deleteSong(id)) {
            redirectAttributes.addFlashAttribute("errors","Song not found");
        }
        return "redirect:/dashboard";
    }

    @RequestMapping("/search")
    public String searchArtist(@RequestParam(value="search") String search, RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("songs",songService.songsByArtist(search));
        model.addAttribute("search",search);
        return "artist_songs";
    }

    @RequestMapping("/search/topTen")
    public String top10(Model model) {
        model.addAttribute("songs",songService.findTop10());
        return "top10";
    }
}
