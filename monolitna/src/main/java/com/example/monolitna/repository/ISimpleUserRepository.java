package com.example.monolitna.repository;

import com.example.monolitna.entity.SimpleUser;
import com.example.monolitna.util.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISimpleUserRepository extends JpaRepository<SimpleUser, Long> {
    SimpleUser findOneById(Long id);

    List<SimpleUser> findAllByRequestStatus(RequestStatus pending);

    List<SimpleUser> findAllByDeleted(boolean b);
}
