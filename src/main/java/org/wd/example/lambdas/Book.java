package org.wd.example.lambdas;

import java.time.Year;
import java.util.List;

/**
 * Modell Class for testing Atreams
 * 
 * @author wd_user
 *
 */
public class Book {

	private String title;
	private List<String> authors;
	private int[] pageCounts;
	private Topic topic;
	private Year pubDate;
	private double height;

	public Book() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public int[] getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(int[] pageCounts) {
		this.pageCounts = pageCounts;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Year getPubDate() {
		return pubDate;
	}

	public void setPubDate(Year pubDate) {
		this.pubDate = pubDate;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public static enum Topic {
		MEDICINE, COMPUTING, FICTION;
	}

	public static class BookBuilder {

		private String title;
		private List<String> authors;
		private int[] pageCounts;
		private Topic topic;
		private Year pubDate;
		private double height;

		public BookBuilder() {
		}

		public BookBuilder withTitle(String bookTitle) {
			this.title = bookTitle;
			return this;
		}

		public BookBuilder withAuthors(List<String> bookAuthors) {
			this.authors = bookAuthors;
			return this;
		}

		public BookBuilder withPageCounts(int[] bookPageCounts) {
			this.pageCounts = bookPageCounts;
			return this;
		}

		public BookBuilder withTopic(Topic bookTopic) {
			this.topic = bookTopic;
			return this;
		}

		public BookBuilder withPubDate(Year bookPubDate) {
			this.pubDate = bookPubDate;
			return this;
		}

		public BookBuilder withHeight(double bookHeight) {
			this.height = bookHeight;
			return this;
		}

		public Book build() {
			Book book = new Book();
			book.setAuthors(authors);
			book.setHeight(height);
			book.setPageCounts(pageCounts);
			book.setPubDate(pubDate);
			book.setTitle(title);
			book.setTopic(topic);
			return book;
		}
	}
}
