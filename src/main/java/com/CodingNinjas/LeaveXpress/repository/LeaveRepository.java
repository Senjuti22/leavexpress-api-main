package com.CodingNinjas.LeaveXpress.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.CodingNinjas.LeaveXpress.model.LeaveModel;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveModel, Long> {
    List<LeaveModel> findByIsAccepted(boolean isAccepted);
}
