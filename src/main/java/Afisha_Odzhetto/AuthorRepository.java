package Afisha_Odzhetto;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
