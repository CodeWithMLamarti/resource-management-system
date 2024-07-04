package com.project.ressourcemangmentapp.service;

import com.project.ressourcemangmentapp.model.Docs;
import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.DocsDto;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import com.project.ressourcemangmentapp.repository.DocsRepository;
import com.project.ressourcemangmentapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class DocsService {
    private final DocsRepository docsRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<DocsDto> findAllDocs() {

        return docsRepository.findAll().stream()
                .map(element -> {
                    return new DocsDto(element.getId(), element.getDocType(), element.getRequestedAt(),modelMapper.map(element.getUser(),UserDto.class));
                }).toList();
    }

    public List<DocsDto> findDocsByUserId(Long id) {
        return this.findAllDocs().stream()
                .filter(element -> Objects.equals(element.getUser().getId(), id))
                .toList();
    }

    public DocsDto createDoc(DocsDto doc) {
        User user = userRepository.findById(doc.getUser().getId()).get();
        Docs newDoc = new Docs(
                doc.getDocType(),
                LocalDate.now(),
                user
        );
        docsRepository.save(newDoc);
        return doc;
    }

    public void delete(Long id) {
        Docs docs = docsRepository.findById(id).get();
        this.docsRepository.delete(docs);
    }
}
