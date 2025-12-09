package com.ysyedprojects.personal_MES.repositories;

import com.ysyedprojects.personal_MES.entities.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends JpaRepository <Method, Long> {

}
