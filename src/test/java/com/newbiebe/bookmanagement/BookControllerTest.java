package com.newbiebe.bookmanagement;

import com.newbiebe.bookmanagement.model.Book;
import com.newbiebe.bookmanagement.controller.BookController;
import com.newbiebe.bookmanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void createBook_should_respond_OK_and_return_JSON_with_right_format() throws Exception {
        Book book = new Book("Effective Java", "Joshua Bloch");
        book.setId(1L); // Assuming the ID is set after saving

//        given(bookService.saveBook(new Book("Effective Java", "Joshua Bloch"))).willReturn(book);
        given(bookService.saveBook(any(Book.class))).willReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Effective Java\",\"author\":\"Joshua Bloch\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Effective Java")))
                .andExpect(jsonPath("$.author", is("Joshua Bloch")));
    }
}

