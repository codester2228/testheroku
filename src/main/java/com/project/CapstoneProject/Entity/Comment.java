package com.project.CapstoneProject.Entity;

import java.math.BigInteger;
import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {

	@Id
	private BigInteger id;

   
	private Long commentId;
	private Long userId;
	private Long postId;
	private Instant timeStamp;
	private String comment;
	private String userName;
	private int count = 0;
	
	@ManyToOne
	private Post post;
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment( Long commentId, Long userId, Long postId, Instant timeStamp, String comment) {
		super();
		
		this.commentId = commentId;
		this.userId = userId;
		this.postId = postId;
		this.timeStamp = timeStamp;
		this.comment = comment;
	}
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Instant getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Instant timestamp) {
		this.timeStamp = timestamp;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
