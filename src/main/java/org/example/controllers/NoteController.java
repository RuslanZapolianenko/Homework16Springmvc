package org.example.controllers;

import org.example.entity.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/list")
    public String noteList(Model model) {
        model.addAttribute("notes", noteService.listAll());
        return "noteList";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editNotePage(@RequestParam long id, Model model) {
        model.addAttribute("note", noteService.getById(id));
        return "editNote";
    }

    @PostMapping("/edit")
    public String saveEditedNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }
}
