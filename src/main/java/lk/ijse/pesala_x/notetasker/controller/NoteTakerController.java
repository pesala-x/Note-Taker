package lk.ijse.pesala_x.notetasker.controller;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteTakerController {
    //TODO: crud of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) //http://localhost:8080/NoteTaker/api/v1/notes
    public ResponseEntity<String> crateNote(@RequestBody NoteDTO note){
        //TODO: Handle with BO
        note.setNoteId(AppUtil.generateNoteId());
        System.out.println("Note Saved " + note);
        return ResponseEntity.ok("Note Saved Successfully") ;
    }

    @GetMapping(value = "allnotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NoteDTO> getAllNotes(){
        System.out.println("Get all notes");
        return null;
    }
    @GetMapping(value = "/{noteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public NoteDTO getNote(@PathVariable ("noteId") String noteId)  {
        System.out.println(noteId);
        return new NoteDTO(
                "Note99e6086e-95c2-41ce-ae8f-b9a3b101df2c",
                "Hibernate-note",
                "lecture name is udara san",
                "High",
                "2024-08-18T14:30:00"
        );
/*        //noteId=Note99e6086e-95c2-41ce-ae8f-b9a3b101df2c, noteTitle=Hibernate-note, noteDescription=lecture name is udara san, priorityLevel=High, createdDateTime=2024-08-18T14:30:00*/
    }
    @PatchMapping(value = "/{noteId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateNote(@PathVariable ("noteId") String noteId, @RequestBody NoteDTO note) {
        System.out.println(noteId);
        System.out.println(note+ " Updated");

    }
    @DeleteMapping(value ="/{noteId}" )
    public void deleteNote(@PathVariable ("noteId") String noteId) {
        System.out.println(noteId + " Deleted");
    }
}
