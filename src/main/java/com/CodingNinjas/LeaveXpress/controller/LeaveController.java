package com.CodingNinjas.LeaveXpress.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.service.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    // Accessible to both EMPLOYEE and MANAGER
    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveModel> getLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeaveById(id));
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<LeaveModel>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping("/accepted")
    public ResponseEntity<List<LeaveModel>> getAcceptedLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesByAccepted(true));
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping("/rejected")
    public ResponseEntity<List<LeaveModel>> getRejectedLeaves() {
        return ResponseEntity.ok(leaveService.getLeavesByAccepted(false));
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @GetMapping("/status/{id}")
    public ResponseEntity<Boolean> getLeaveStatus(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.getLeaveStatus(id));
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<LeaveModel> updateLeave(@PathVariable Long id, @RequestBody LeaveDto leaveDto) {
        return ResponseEntity.ok(leaveService.updateLeave(id, leaveDto));
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER')")
    @PostMapping("/apply")
    public ResponseEntity<LeaveModel> applyLeave(@RequestBody LeaveDto leaveDto) {
        return ResponseEntity.ok(leaveService.applyLeave(leaveDto));
    }

    // MANAGER-only endpoints
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/accept/{id}")
    public ResponseEntity<LeaveModel> acceptLeave(@PathVariable Long id) {
        return new ResponseEntity<>(leaveService.acceptLeave(id), HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/reject/{id}")
    public ResponseEntity<LeaveModel> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }
}

