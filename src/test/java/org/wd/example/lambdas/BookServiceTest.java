package org.wd.example.lambdas;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.wd.example.lambdas.Book.Topic;

/**
 * Tests for {@link BookService}
 * 
 * @author wd_user
 *
 */
public class BookServiceTest {

	private BookService bookService = new BookService();

	public BookServiceTest() {
	}

	@Test
	public void getBooksWithTopicTest() {
		List<Book> fictionBooks = bookService.getBooksByTopic(Topic.FICTION);
		assertThat(fictionBooks, not(empty()));
		boolean onlyFictions = fictionBooks.stream().allMatch(
				b -> b.getTopic() == Topic.FICTION);
		assertThat(onlyFictions, equalTo(true));
	}

	@Test
	public void getTitlesTest() {
		List<String> titles = bookService.getTitles();
		assertThat(
				titles,
				hasItems(BookRepository.COMPILERS.getTitle(),
						BookRepository.NAILS.getTitle()));
	}

	@Test
	public void getSortedTest() {
		List<Book> books = bookService.getSorted();
		for (int i = 0; i < books.size(); i++) {
			Book refBook = books.get(i);
			for (int j = i + 1; j < books.size(); j++) {
				assertThat(refBook.getTitle(), lessThanOrEqualTo(books.get(j)
						.getTitle()));
			}
		}
	}

	@Test
	public void getAuthorsTest() {
		List<String> authors = bookService.getAuthors();
		assertThat(
				authors,
				hasItems(BookRepository.COMPILERS.getAuthors()
						.toArray(
								new String[BookRepository.COMPILERS
										.getAuthors().size()])));
	}

	@Test
	public void getSortedLimitedTest() {
		List<Book> booksAll = bookService.getSorted();
		int limit = new Random().nextInt(booksAll.size());
		List<Book> booksLimited = bookService.getSortedLimited(limit);
		assertThat(booksLimited, hasSize(limit));
		for (int i = 0; i < limit; i++) {
			assertThat(booksLimited.get(i), equalTo(booksAll.get(i)));
		}
	}

	@Test
	public void getSortedRestTest() {
		List<Book> booksAll = bookService.getSorted();
		int skiped = new Random().nextInt(booksAll.size());
		List<Book> booksRest = bookService.getSortedRest(skiped);
		assertThat(booksRest, hasSize(booksAll.size() - skiped));
		for (int i = 0; i < skiped; i++) {
			assertThat(booksRest.get(i), equalTo(booksAll.get(skiped + i)));
		}
	}

	@Test
	public void getOldestTest() {
		List<Book> booksAll = bookService.getSorted();
		Book bookOldest = bookService.getOldest();
		boolean oldest = booksAll.parallelStream().allMatch(
				b -> !b.getPubDate().isBefore(bookOldest.getPubDate()));
		assertThat(oldest, equalTo(true));
	}

	@Test
	public void getAsMapByTopicTest() {
		Map<Book.Topic, List<Book>> mapByTopic = bookService.getAsMapByTopic();
		assertThat(mapByTopic.get(BookRepository.NAILS.getTopic()),
				hasItem(BookRepository.NAILS));
	}
}
