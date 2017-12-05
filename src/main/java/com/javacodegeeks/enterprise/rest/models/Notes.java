package com.javacodegeeks.enterprise.rest.models;

public class Notes {
	String title;
	String content;
	int level;
	String share;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getShare() {
		return share;
	}

	public void setShare(String share) {
		this.share = share;
	}

	@Override
	public String toString() {
		return title + " " + content + " " + level + " " + share;
	}
}