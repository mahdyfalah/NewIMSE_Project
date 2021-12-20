package com.example.newimse_project.Repository;
import com.example.newimse_project.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {
}
