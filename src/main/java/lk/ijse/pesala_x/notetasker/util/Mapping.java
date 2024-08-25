package lk.ijse.pesala_x.notetasker.util;

import lk.ijse.pesala_x.notetasker.dto.NoteDTO;
import lk.ijse.pesala_x.notetasker.entity.NoteEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    //Mapping
    @Autowired
    private ModelMapper modelMapper;

    //matters of noteEntity and DTO
    public NoteDTO convertToDTO (NoteEntity note){
        return modelMapper.map(note, NoteDTO.class);
    }
    public NoteEntity convertToEntity(NoteDTO dto) {
        return modelMapper.map(dto, NoteEntity.class);
    }
    public List<NoteDTO> convertToDTO(List<NoteEntity> notes) {
        return modelMapper.map(notes, List.class);
    }

}
