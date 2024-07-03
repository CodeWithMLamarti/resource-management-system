package com.project.ressourcemangmentapp.controller;

import com.project.ressourcemangmentapp.model.Break;
import com.project.ressourcemangmentapp.model.dto.BreakDto;
import com.project.ressourcemangmentapp.service.BreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breaks")
@RequiredArgsConstructor
public class BreakController {

    private final BreakService breakService;


    @PostMapping("/request")
    public BreakDto requestBreak(@RequestBody BreakDto breakRequest) {
        return breakService.requestBreak(breakRequest);
    }

    @GetMapping("/all")
    public List<Break> getAllBreakRequests() {
        return breakService.getAllBreakRequests();
    }

    @GetMapping("/user/{userId}")
    public List<Break> getBreaksByUserId(@PathVariable Long userId) {
        return breakService.getBreaksByUserId(userId);
    }

    @PutMapping("/approve/hr/{id}")
    public BreakDto approveBreakByHR(@PathVariable Long id) {
        return breakService.approveBreakByHR(id);
    }

    @PutMapping("/approve/manager/{id}")
    public BreakDto approveBreakByManager(@PathVariable Long id) {
        return breakService.approveBreakByManager(id);
    }

    @PutMapping("/reject/{id}")
    public BreakDto rejectBreak(@PathVariable Long id) {
        return breakService.rejectBreak(id);
    }
}
