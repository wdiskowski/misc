package org.wd.example.lambdas;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Collections;
import java.util.List;
import org.wd.example.lambdas.Book.Topic;
import org.junit.Test;

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
}
