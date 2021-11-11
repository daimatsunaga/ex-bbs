package com.example.form;

import javax.validation.constraints.NotBlank;

/**
 * Articleのフォームクラス
 * @author matsunagadai
 *
 */
public class ArticleForm {

	//記事の投稿者名
	@NotBlank(message="投稿者名が空欄です")
	private String name;
	@NotBlank(message="投稿内容が空欄です")
	//記事内容
	private String content;
	
	//ゲッターセッター
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
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}
}
