package lk.ijse.pesala_x.notetasker.util;

import java.util.UUID;

public class AppUtil {

    public static String generateNoteId(){
        return "Note" + UUID.randomUUID();
    }
}
