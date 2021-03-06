package com.isuru.crawler.refactor;

import com.isuru.bean.Comment;
import com.isuru.bean.NewsArticle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "newsArticle")
public class OldNewsArticle2 {
	private String author;
	private String date;
	private String title;
	private String body;
	private List<OldComment2> comments;
	private String url;

	public OldNewsArticle2() {
		comments = new ArrayList<>();
	}

	public OldNewsArticle2(String title, String body, List<OldComment2> comments) {
		this.title = title;
		this.body = body;
		this.comments = comments;
	}

	public NewsArticle getNewerVersion(long id) {
		List<Comment> newComments = new ArrayList<>();
		int i = 0;
		for (OldComment2 comment : comments) {
			newComments.add(comment.getNewerVersion(i++));
		}

		NewsArticle newsArticle = new NewsArticle(id, title, body.trim(), newComments);
		newsArticle.setAuthor(author);
		newsArticle.setDate(date);
		newsArticle.setUrl(url);

		return newsArticle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}


	@XmlElementWrapper(name="comments")
	@XmlElement(name="comment")
	public List<OldComment2> getComments() {
		return comments;
	}

	public void setComments(List<OldComment2> comments) {
		this.comments = comments;
	}

	public void addComment(OldComment2 comment) {
		comments.add(comment);
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String toTxt() {
		StringBuilder sb = new StringBuilder();
		sb.append(title).append("\n")
			.append(body).append("\n");
		for (OldComment2 c: comments) {
			sb.append("\n\n").append(c.toTxt());
		}
		return sb.toString();
	}

	public String toTxtComments() {
		StringBuilder sb = new StringBuilder();
		sb.append(title).append("\n");
		for (OldComment2 c: comments) {
			sb.append("\n\n").append(c.toTxt());
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(author).append("\n").append(date).append("\n").append(title).append("\n");
		if (body.length() >= 100)
			sb.append(body.substring(0, 100)).append(".....");
		else
			sb.append(body);
		sb.append("\n# commets: ").append(comments.size());
		return sb.toString();
	}
}
