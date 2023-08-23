package uz.xb.projectwithtwodb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.xb.projectwithtwodb.entity.mysql.ABS;
import uz.xb.projectwithtwodb.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class Controller {

    private final UserService userService;

    @GetMapping
    public List<ABS> get() {
       return userService.fromPtoS();
    }

    @GetMapping("/a")
    public List<ABS> getA() {
        return userService.fromStoP();
    }
}
