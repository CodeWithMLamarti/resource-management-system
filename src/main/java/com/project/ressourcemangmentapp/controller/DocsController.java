package com.project.ressourcemangmentapp.controller;

import com.project.ressourcemangmentapp.model.dto.DocsDto;
import com.project.ressourcemangmentapp.service.DocsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/docs")
@RequiredArgsConstructor
public class DocsController {
    private final DocsService docsService;
    @GetMapping("/")
    public ResponseEntity<List<DocsDto>> getAllDocs() {
        return ResponseEntity.ok(docsService.findAllDocs());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<DocsDto>> getDocsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(docsService.findDocsByUserId(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<DocsDto> createDoc(@RequestBody DocsDto doc) {
        return ResponseEntity.ok(docsService.createDoc(doc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.docsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
