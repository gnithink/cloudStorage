package com.nithin.projects.cloudstorage.services;

import com.nithin.projects.cloudstorage.model.Note;
import com.nithin.projects.cloudstorage.model.NoteForm;
import com.nithin.projects.cloudstorage.mapper.NotesMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NotesMapper notesMapper;

    public NotesService(NotesMapper notesMapper) {
        this.notesMapper = notesMapper;
    }

    public void upsertUserNote(Integer userId, NoteForm noteForm) {
        var note = this.notesMapper.getUserNoteById(userId, noteForm.getNoteId());

        if (note == null) {
            note = new Note(null, noteForm.getNoteTitle(), noteForm.getNoteDescription(), userId);
            this.notesMapper.addNote(note);
        } else {
            note.setNoteTitle(noteForm.getNoteTitle());
            note.setNoteDescription(noteForm.getNoteDescription());

            this.notesMapper.updateNote(note);
        }
    }

    public List<Note> getAllUserNotes(Integer userId){
        return this.notesMapper.getAllUserNotes(userId);
    }

    public void deleteUserNoteById(Integer userId, Integer noteId){
        this.notesMapper.deleteUserNoteById(userId, noteId);
    }
}
