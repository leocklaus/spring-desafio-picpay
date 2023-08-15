package com.lck.picpaysimplificado.domain.repository;

import com.lck.picpaysimplificado.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
