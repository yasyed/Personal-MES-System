package com.ysyedprojects.personalMES.repositories;

import com.ysyedprojects.personalMES.entities.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodRepository extends JpaRepository <Method, Long> {

}
