package com.newbiebe.bookmanagement.repository;

import com.newbiebe.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}