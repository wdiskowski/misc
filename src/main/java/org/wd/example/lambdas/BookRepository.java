package org.wd.example.lambdas;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

import org.wd.example.lambdas.Book.Topic;

/**
 * Repository for Books
 * 
 * @author wd_user
 *
 */

public class BookRepository {

	/** CHECKSTYLE:OFF **/
	public static final Book NAILS = new Book.BookBuilder()
			.withTitle("Fundamentals of Cinese FingernailsImage")
			.withAuthors(Arrays.asList("Li", "Fu", "Li"))
			.withPageCounts(new int[] { 256 }).withPubDate(Year.of(2014))
			.withHeight(25.2).withTopic(Topic.MEDICINE).build();

	public static final Book COMPILERS = new Book.BookBuilder()
			.withTitle("Compilers: Principles, Techniques and Tools")
			.withAuthors(Arrays.asList("Aho", "Lahm", "Sethi", "Ullman"))
			.withPageCounts(new int[] { 1009 }).withPubDate(Year.of(2006))
			.withHeight(23.62).withTopic(Topic.COMPUTING).build();

	public static final Book VOSS = new Book.BookBuilder().withTitle("Voss")
			.withAuthors(Arrays.asList("Patrick White"))
			.withPageCounts(new int[] { 478 }).withPubDate(Year.of(1957))
			.withHeight(19.8).withTopic(Topic.FICTION).build();

	public static final Book LORD = new Book.BookBuilder()
			.withTitle("Lord of the Rings")
			.withAuthors(Arrays.asList("Tolkien"))
			.withPageCounts(new int[] { 531, 416, 624 })
			.withPubDate(Year.of(1955)).withHeight(23.0)
			.withTopic(Topic.FICTION).build();

	/** CHECKSTYLE:ON **/
	public BookRepository() {
	}

	/**
	 * Complete Library
	 * @return List of {@link Book}
	 */
	public List<Book> getAll() {
		return Arrays.asList(NAILS, COMPILERS, VOSS, LORD);
	}
}
