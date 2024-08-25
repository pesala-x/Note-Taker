package lk.ijse.pesala_x.notetasker.service;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    String saveNote(NoteDTO noteDTO);
    void updateNote(String noteId,NoteDTO noteDTO);
    void deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
