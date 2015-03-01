package org.wd.example.lambdas;

import java.util.List;
import java.util.stream.Collectors;

import org.wd.example.lambdas.Book.Topic;

/**
 * Service for Book Modell
 * 
 * @author wd_user
 *
 */
public class BookService {

	private BookRepository bookRepository = new BookRepository();

	public BookService() {
	}

	public List<Book> getBooksByTopic(Topic topic) {
		return bookRepository.getAll().parallelStream()
				.filter(b -> b.getTopic() == topic)
				.collect(Collectors.toList());
	}

}
