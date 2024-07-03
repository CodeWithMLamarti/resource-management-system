package com.project.ressourcemangmentapp.service;

import com.project.ressourcemangmentapp.model.Break;
import com.project.ressourcemangmentapp.model.User;
import com.project.ressourcemangmentapp.model.dto.BreakDto;
import com.project.ressourcemangmentapp.model.dto.UserDto;
import com.project.ressourcemangmentapp.repository.BreakRepository;
import com.project.ressourcemangmentapp.repository.UserRepository;
import com.project.ressourcemangmentapp.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BreakService {
    private final BreakRepository breakRepository;
    private final UserRepository userRepository;

    public BreakDto requestBreak(BreakDto breakRequest) {
        User user = userRepository.findById(breakRequest.getUser().getId()).get();
        int requestedDays = breakRequest.getBreakDuration();

        if (user.getBalance() >= requestedDays) {
            //String password = userRepository.findById(user.getId()).get().getPassword();
            //user.setPassword(password);
            user.setBalance(user.getBalance() - requestedDays);
            userRepository.save(user);
            Break newBreak = new Break(
                    breakRequest.getStartDate(),
                    breakRequest.getEndDate(),
                    breakRequest.getReason(),
                    breakRequest.getStatus(),
                    breakRequest.getBreakType(),
                    user,
                    breakRequest.isHrApproved(),
                    breakRequest.isManagerApproved()
            );
            breakRepository.save(newBreak);
            return breakRequest;
        } else {
            throw new RuntimeException("The requested break duration exceeds the available balance.");
        }
    }

    public List<Break> getAllBreakRequests() {
        return breakRepository.findAll();
    }
    public List<Break> getBreaksByUserId(Long userId) {
        List<Break> breaks = getAllBreakRequests();
        List<Break> filteredList = breaks.stream()
                .filter(abreak -> Objects.equals(abreak.getUser().getId(), userId))
                .toList();
        return filteredList;
    }

    public BreakDto approveBreakByHR(Long breakId) {
        Break breakRequest = breakRepository.findById(breakId).orElseThrow(() -> new RuntimeException("Break not found"));
        if (canApproveBreak(breakRequest)) {
            breakRequest.setHrApproved(true);
            updateStatusIfApproved(breakRequest);
        }
        User user = userRepository.findById(breakRequest.getUser().getId()).get();
        breakRequest.setUser(user);
        breakRepository.save(breakRequest);
        UserDto newUser = userRepository.getUserById(breakRequest.getUser().getId()).get();
        BreakDto newBreak = new BreakDto(
                breakRequest.getId(),
                breakRequest.getStartDate(),
                breakRequest.getEndDate(),
                breakRequest.getReason(),
                breakRequest.getStatus(),
                breakRequest.getBreakType(),
                newUser,
                breakRequest.isHrApproved(),
                breakRequest.isManagerApproved()
        );
        return newBreak;
    }

    public BreakDto approveBreakByManager(Long breakId) {
        Break breakRequest = breakRepository.findById(breakId).orElseThrow(() -> new RuntimeException("Break not found"));
        if (canApproveBreak(breakRequest)) {
            breakRequest.setManagerApproved(true);
            updateStatusIfApproved(breakRequest);
        }
        User user = userRepository.findById(breakRequest.getUser().getId()).get();
        breakRequest.setUser(user);
        breakRepository.save(breakRequest);
        UserDto newUser = userRepository.getUserById(breakRequest.getUser().getId()).get();
        BreakDto newBreak = new BreakDto(
                breakRequest.getId(),
                breakRequest.getStartDate(),
                breakRequest.getEndDate(),
                breakRequest.getReason(),
                breakRequest.getStatus(),
                breakRequest.getBreakType(),
                newUser,
                breakRequest.isHrApproved(),
                breakRequest.isManagerApproved()
        );
        return newBreak;
    }

    public BreakDto rejectBreak(Long breakId) {
        Break breakRequest = breakRepository.findById(breakId).orElseThrow(() -> new RuntimeException("Break not found"));
        breakRequest.setStatus(Status.DENIED);
        User user = userRepository.findById(breakRequest.getUser().getId()).get();
        breakRequest.setUser(user);
        int requestedDays = breakRequest.getBreakDuration();
        user.setBalance(user.getBalance() + requestedDays);
        breakRepository.save(breakRequest);
        UserDto newUser = userRepository.getUserById(breakRequest.getUser().getId()).get();
        BreakDto newBreak = new BreakDto(
                breakRequest.getId(),
                breakRequest.getStartDate(),
                breakRequest.getEndDate(),
                breakRequest.getReason(),
                breakRequest.getStatus(),
                breakRequest.getBreakType(),
                newUser,
                breakRequest.isHrApproved(),
                breakRequest.isManagerApproved()
        );
        return newBreak;
    }

    private void updateStatusIfApproved(Break breakRequest) {
        if (breakRequest.isHrApproved() && breakRequest.isManagerApproved()) {
            breakRequest.setStatus(Status.APPROVED);
        }
    }

    private boolean canApproveBreak(Break breakRequest) {
        return breakRequest.getUser().getBalance() >= breakRequest.getBreakDuration();
    }
}
