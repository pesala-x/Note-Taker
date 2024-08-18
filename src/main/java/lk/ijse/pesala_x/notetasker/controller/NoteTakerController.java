package lk.ijse.pesala_x.notetasker.controller;

import lk.ijse.pesala_x.notetasker.dto.Note;
import lk.ijse.pesala_x.notetasker.util.AppUtil;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteTakerController {
    //TODO: crud of the note
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE) //http://localhost:8080/NoteTaker/api/v1/notes
    public ResponseEntity<String> crateNote(@RequestBody Note note){
        //TODO: Handle with BO
        note.setNoteId(AppUtil.generateNoteId());
        System.out.println("Note Saved " + note);
        return ResponseEntity.ok("Note Saved Successfully") ;
    }
}
