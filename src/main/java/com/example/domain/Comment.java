package com.example.domain;
/**
 * コメントに関するドメインクラス
 * @author matsunagadai
 *
 */
public class Comment {
	
	//Id
	private Integer id;
	//コメントした人の名前
	private String name;
	//コメントの内容
	private String content;
	//articleId
	private Integer articleId;
	
	//ゲッターセッター
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	
	//toStringのオーバーライド
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}
}