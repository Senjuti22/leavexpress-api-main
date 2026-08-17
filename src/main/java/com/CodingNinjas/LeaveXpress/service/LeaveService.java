package com.CodingNinjas.LeaveXpress.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CodingNinjas.LeaveXpress.dto.LeaveDto;
import com.CodingNinjas.LeaveXpress.exception.LeaveNotFoundException;
import com.CodingNinjas.LeaveXpress.model.LeaveModel;
import com.CodingNinjas.LeaveXpress.repository.LeaveRepository;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    public LeaveModel getLeaveById(Long id) {
        return leaveRepository.findById(id)
            .orElseThrow(() -> new LeaveNotFoundException("Leave not found with ID: " + id));
    }

    public List<LeaveModel> getAllLeaves() {
        return leaveRepository.findAll();
    }

    public List<LeaveModel> getLeavesByAccepted(boolean accepted) {
        return leaveRepository.findByIsAccepted(accepted);
    }

    public LeaveModel applyLeave(LeaveDto dto) {
        LeaveModel leave = new LeaveModel();
        leave.setType(dto.getType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setDescription(dto.getDescription());
        leave.setAccepted(false);
        return leaveRepository.save(leave);
    }

    public LeaveModel updateLeave(Long id, LeaveDto dto) {
        LeaveModel leave = getLeaveById(id);
        leave.setType(dto.getType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setDescription(dto.getDescription());
        return leaveRepository.save(leave);
    }

    public void deleteLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leaveRepository.delete(leave);
    }

    public LeaveModel acceptLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leave.setAccepted(true);
        return leaveRepository.save(leave);
    }

    public LeaveModel rejectLeave(Long id) {
        LeaveModel leave = getLeaveById(id);
        leave.setAccepted(false);
        return leaveRepository.save(leave);
    }

    public boolean getLeaveStatus(Long id) {
        LeaveModel leave = getLeaveById(id);
        return leave.isAccepted();
    }
}
