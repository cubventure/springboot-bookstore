package com.shelf.bookshelf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.shelf.bookshelf.domain.Book;
import com.shelf.bookshelf.domain.BookRepository;
import com.shelf.bookshelf.domain.Category;
import com.shelf.bookshelf.domain.CategoryRepository;
import com.shelf.bookshelf.domain.User;
import com.shelf.bookshelf.domain.UserRepository;

@SpringBootApplication
public class BookshelfApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookshelfApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			// Create new Repositories
			categoryRepository.save(new Category("Classics"));
			categoryRepository.save(new Category("Fantasy"));
			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Drama"));
			categoryRepository.save(new Category("Adventure"));
			
			// Create new books
			Book b1 = new Book("George Orwell", "Animal Farm", "978-0141182704", (short) 1945, 2.00, categoryRepository.findByName("Classics").get(0));
			Book b2 = new Book("George R.R. Martin", "Game of Thrones", "3284329-2342", (short) 2003, 10.99, categoryRepository.findByName("Fantasy").get(0));
			
			bookRepository.save(b1);
			bookRepository.save(b2);
			
			bookRepository.save(new Book("Bram Stoker", "Dracula", "234-234323242", (short) 1894, 5.55, categoryRepository.findByName("Horror").get(0)));
			
			
			//Create users: user/user admin/admin
			User user1 = new User("user", "$2a$04$Xwm3t/hwksvRSPm7N5Hy8enckIdfhOeEwU4xG50aCQGE/Hu5GDxOm", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$04$YA7KIj/qCE0ME4e4XT0EROJe3wuRAGqc906go85SZ9v6nwjZynmEK", "admin@user.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
