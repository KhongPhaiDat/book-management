package com.newbiebe.bookmanagement;

import com.newbiebe.bookmanagement.model.Book;
import com.newbiebe.bookmanagement.repository.BookRepository;
import com.newbiebe.bookmanagement.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        // Initialize mocks annotated with @Mock, and inject them into the @InjectMocks instance
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_book_should_return_saved_book() {
        // Given
        Book book = new Book("Effective Java", "Joshua Bloch");
        given(bookRepository.save(book)).willReturn(book);

        // When
        Book savedBook = bookService.saveBook(book);

        // Then
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Effective Java");
        assertThat(savedBook.getAuthor()).isEqualTo("Joshua Bloch");
    }
}
