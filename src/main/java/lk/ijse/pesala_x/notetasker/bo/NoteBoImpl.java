package lk.ijse.pesala_x.notetasker.bo;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;

import java.util.List;

public final class NoteBoImpl implements NoteBo{
    @Override
    public String saveData(NoteDTO noteDTO) {
        return null;
    }

    @Override
    public boolean updateNote(String noteId, NoteDTO noteDTO) {
        return false;
    }

    @Override
    public boolean deleteNote(String noteId) {
        return false;
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return null;
    }
}
