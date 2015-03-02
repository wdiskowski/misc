package org.wd.example.lambdas;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

	public List<String> getTitles() {
		return bookRepository.getAll().parallelStream().map(Book::getTitle)
				.collect(Collectors.toList());
	}

	public List<Book> getSorted() {
		return bookRepository.getAll().parallelStream()
				.sorted(Comparator.comparing(Book::getTitle))
				.collect(Collectors.toList());
	}

	public List<String> getAuthors() {
		return bookRepository.getAll().parallelStream()
				.flatMap(b -> b.getAuthors().stream()).distinct()
				.collect(Collectors.toList());
	}

	public List<Book> getSortedLimited(int limit) {
		return bookRepository.getAll().parallelStream()
				.sorted(Comparator.comparing(Book::getTitle)).limit(limit)
				.collect(Collectors.toList());
	}

	public List<Book> getSortedRest(int skiped) {
		return bookRepository.getAll().parallelStream()
				.sorted(Comparator.comparing(Book::getTitle)).skip(skiped)
				.collect(Collectors.toList());
	}

	public Book getOldest() {
		return bookRepository.getAll().parallelStream()
				.min(Comparator.comparing(Book::getPubDate)).get();
	}

	public Map<Topic, List<Book>> getAsMapByTopic() {
		return bookRepository.getAll().parallelStream()
				.collect(Collectors.groupingBy(Book::getTopic));
	}

}
