package lk.ijse.pesala_x.notetasker.CustomOBJ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NoteErrorResponse implements NoteResponse{
    private int ErrorCode;
    private String ErrorMessage;
}
