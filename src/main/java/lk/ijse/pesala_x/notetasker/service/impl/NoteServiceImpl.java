package lk.ijse.pesala_x.notetasker.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.pesala_x.notetasker.CustomOBJ.NoteErrorResponse;
import lk.ijse.pesala_x.notetasker.CustomOBJ.NoteResponse;
import lk.ijse.pesala_x.notetasker.dao.NoteDao;
import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import lk.ijse.pesala_x.notetasker.exception.DataPersistFailedException;
import lk.ijse.pesala_x.notetasker.exception.NoteNotFound;
import lk.ijse.pesala_x.notetasker.service.NoteService;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import lk.ijse.pesala_x.notetasker.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteDao noteDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        var noteEntity = mapping.convertToEntity(noteDTO);
        var savedNoted = noteDao.save(noteEntity);
        if(savedNoted == null){
            throw new DataPersistFailedException("Cannot save note");
        }
    }

    @Override
    public void updateNote(String noteId, NoteDTO incommingNoteDTO) {
        Optional<NoteEntity> tempNoteEntity = noteDao.findById(noteId);
        if (!tempNoteEntity.isPresent()){
            throw new NoteNotFound("Note not found");
        }else {
            tempNoteEntity.get().setNoteDesc(incommingNoteDTO.getNoteDesc());
            tempNoteEntity.get().setNoteTitle(incommingNoteDTO.getNoteTitle());
            tempNoteEntity.get().setCreateDate(incommingNoteDTO.getCreateDate());
            tempNoteEntity.get().setPriorityLevel(incommingNoteDTO.getPriorityLevel());
        }

    }

    @Override
    public void deleteNote(String noteId) {
//        noteDao.deleteById(noteId);
//        for get clear idea for the request
        Optional<NoteEntity> findId = noteDao.findById(noteId);
        if(!findId.isPresent()){
            throw new NoteNotFound("Note not found");
        }else {
            noteDao.deleteById(noteId);
        }

    }

    @Override
    public NoteResponse getSelectedNote(String noteId) {
        if(noteDao.existsById(noteId)){
            return mapping.convertToDTO(noteDao.getReferenceById(noteId));
        }else {
            return new NoteErrorResponse(0,"Note not found");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        /* here Simplyfyied methods
        List<NoteEntity> getAllNotes = noteDao.findAll();
        List<NoteDTO> noteDTOS = mapping.convertToDTO(getAllNotes);
        return noteDTOS;
        */
        return mapping.convertToDTO(noteDao.findAll());
    }

    /*List<NoteDTO> saveNoteTmp = new ArrayList<>();
    public NoteServiceImpl() {
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of SE","SE Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb","Priciples of CS","CS Desc","P2","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a069-2ebc-41b2-9de6-4e9bcdba65bb","Priciples of NW","NW Desc","P1","20240825"));
        saveNoteTmp.add(new NoteDTO("NOTE 4f8a0a70-2ebc-41b2-9de6-4e9ddbbba65b","Priciples of UI","UI Desc","P2","20240825"));
        System.out.println(saveNoteTmp);
    }
    @Override
    public String saveNote(NoteDTO noteDTO) {
        noteDTO.setNoteId(AppUtil.generateNoteId());
        saveNoteTmp.add(noteDTO);
        return "Saved successfully in Service layer";
    }

    @Override
    public void updateNote(String noteId, NoteDTO incomeNoteDTO) {
        ListIterator<NoteDTO> updatedList = saveNoteTmp.listIterator();
        while (updatedList.hasNext()) {
            NoteDTO noteDTO = updatedList.next();
            if (noteId.equals(noteDTO.getNoteId())) {
                incomeNoteDTO.setNoteId(noteDTO.getNoteId());
                updatedList.set(incomeNoteDTO);
                break;
            }
        }
    }

    @Override
    public void deleteNote(String noteId) {
        ListIterator<NoteDTO> tmpList = saveNoteTmp.listIterator();
        while (tmpList.hasNext()) {
            NoteDTO noteDTO = tmpList.next();
            if (noteId.equals(noteDTO.getNoteId())) {
                tmpList.remove();
            }
        }
    }

    @Override
    public NoteDTO getSelectedNote(String noteId) {
        for (NoteDTO noteDTO : saveNoteTmp) {
            if (noteDTO.getNoteId().equals(noteId)) {
                return noteDTO;
            }
        }
        return null;
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        return saveNoteTmp;
    }*/

}
