package com.example.demospringswaggerdocs.repository;

import com.example.demospringswaggerdocs.domain.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {

    long countByEmail(String email);

    long countByUsername(String username);
}
