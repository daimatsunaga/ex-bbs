package com.example.form;
/**
 * 記事に対するコメントのフォームクラス
 * @author matsunagadai
 *
 */
public class CommentForm {
	
	//紐づく記事のId
	private String articleId;
	//コメントした人の名前
	private String name;
	//コメント内容
	private String content;
	
	//ゲッターセッター
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
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
	
	//toStringのオーバーライド
	@Override
	public String toString() {
		return "CommentForm [articleId=" + articleId + ", name=" + name + ", content=" + content + "]";
	}
	
	
}
