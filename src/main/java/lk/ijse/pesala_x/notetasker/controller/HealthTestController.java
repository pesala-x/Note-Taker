package lk.ijse.pesala_x.notetasker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/healthTest")
public class HealthTestController {
    @GetMapping
    public String healthCheck(){
        return "Note Taker is Running successfully...";
    }
}
