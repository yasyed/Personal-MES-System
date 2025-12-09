package com.ysyedprojects.personal_MES.repositories;

import com.ysyedprojects.personal_MES.entities.MethodStep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodStepRepository extends JpaRepository<MethodStep,Long> {
}
