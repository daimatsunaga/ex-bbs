package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("")
	public String index() {
		List<Article> articleList = articleRepository.findAll();
		session.setAttribute("articleList", articleList);
		return "article_index";
	}
	
	@RequestMapping("/insert")
	public String insert(ArticleForm form) {
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer id) {
		System.out.println(id);
		articleRepository.delete(id);
		return "redirect:/article";
	}
}
