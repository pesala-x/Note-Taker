package lk.ijse.pesala_x.notetasker.controller;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.service.NoteService;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    @Autowired
    private final NoteService noteService;
    //Todo: CRUD of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createNote(@RequestBody NoteDTO note) {
        //Todo: Handle with Service
        var saveData = noteService.saveNote(note);
        return ResponseEntity.ok(saveData);
    }
    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)// http://localhost:8080/NoteTaker/api/v1/notes/allnotes
    public List<NoteDTO> getAllNotes(){
        return noteService.getAllNotes();
    }
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)// http://localhost:8080/NoteTaker/api/v1/notes/NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        System.out.println(noteId);
        /*        return new NoteDTO(
                "NOTE 4f8a0a67-2ebc-41b2-9de6-4e9bcdba65bb",
                "REST services",
                "Explain the REST",
                "P1",
                "20240818"
        );*/
        return noteService.getSelectedNote(noteId);
    }
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE) // http://localhost:8080/NoteTaker/api/v1/notes/NOTE 4f8a0a68-3ccc-41b2-9de6-4e9bcdba65bb
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        System.out.println(noteId);
        System.out.println(note+ " Updated");
    }
    @DeleteMapping(value ="/{noteId}" )
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        System.out.println(noteId + " Deleted");
    }
}
