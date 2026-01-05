package com.ysyedprojects.personalMES.repositories;

import com.ysyedprojects.personalMES.entities.MethodStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodStepRepository extends JpaRepository<MethodStep,Long> {
}
