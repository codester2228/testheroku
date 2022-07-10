package com.project.CapstoneProject.Entity;

public class DoubleId {

	public DoubleId() {
		// TODO Auto-generated constructor stub
	}
	private Long one;
	public DoubleId(Long one, Long two) {
		super();
		this.one = one;
		this.two = two;
	}
	public Long getOne() {
		return one;
	}
	public void setOne(Long one) {
		this.one = one;
	}
	public Long getTwo() {
		return two;
	}
	public void setTwo(Long two) {
		this.two = two;
	}
	private Long two;

}
