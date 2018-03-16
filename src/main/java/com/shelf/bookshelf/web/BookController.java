package com.shelf.bookshelf.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shelf.bookshelf.domain.Book;
import com.shelf.bookshelf.domain.BookRepository;
import com.shelf.bookshelf.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	BookRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	// Login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// Show all books
	@RequestMapping("/books")
	public String books(Model model) {
		model.addAttribute("books", repository.findAll());
		return "books";
	}
	
	// RESTful service to get all students
	@RequestMapping(value="/rest/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> booksRest() {
		return (List<Book>) repository.findAll();
	}
	
	// RESTful service to get student by id
	@RequestMapping(value="/rest/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookid) {
		return repository.findOne(bookid);
	}
	
	// Delete a book
	@RequestMapping("/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookid, Model model) {
		repository.delete(bookid);
		return "redirect:../books";
	}
	
	// Edit a book
	@RequestMapping("/edit/{id}")
	public String editBook(@PathVariable("id") Long bookid, Model model) {
		model.addAttribute("book", repository.findOne(bookid));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editbook";
	}
	
	// Add a book
	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "addbook";
	}
	
	// Saves the form data from adding a book
	@RequestMapping("/save")
	public String saveBook(Book book) {
		repository.save(book);
		return "redirect:books";
	}

}
