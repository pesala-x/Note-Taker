package lk.ijse.pesala_x.notetasker.bo;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;

import java.util.List;

public sealed interface NoteBo permits NoteBoImpl {
    String saveData(NoteDTO noteDTO);
    boolean updateNote(String noteId,NoteDTO noteDTO);
    boolean deleteNote(String noteId);
    NoteDTO getSelectedNote(String noteId);
    List<NoteDTO> getAllNotes();
}
