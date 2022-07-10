package com.project.CapstoneProject.Entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="post")
public class Post {
	
	@Id
	private BigInteger id;
	private Long postId;
	private Long userId;
	private String postpath;
	private int likecount; 
	private Instant timestamp;
	
	
	//@OneToMany(mappedBy = "Post")
	private List<Comment> commentslist = new ArrayList<Comment>();
	
	
	public List<Comment> getCommentslist() {
		return commentslist;
	}
	public void setCommentslist(List<Comment> commentslist) {
		this.commentslist = commentslist;
	}
	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPostpath() {
		return postpath;
	}
	public void setPostpath(String postpath) {
		this.postpath = postpath;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	
	
	

}