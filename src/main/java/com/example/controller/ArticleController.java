package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.domain.Comment;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;
import com.example.repository.CommentRepository;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}
	
	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	@RequestMapping("")
	public String index(Model model) {
		List<Article> articleList = articleRepository.findAll();
		
//		for(Article article : articleList) {
//			List<Comment> commentList = commentRepository.findByArticle(article.getId());
//			article.setCommentList(commentList);
//		}
				
		model.addAttribute("articleList", articleList);
		return "article_index";
	}
	
	@RequestMapping("/insert")
	public String insertArticle(@Validated ArticleForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return index(model);
		}
		Article article = new Article();
		BeanUtils.copyProperties(form, article);
		articleRepository.insert(article);
		return "redirect:/article";
	}
	
	@RequestMapping("/delete")
	public String deleteArticle(Integer id) {
		articleRepository.deleteById(id);
		commentRepository.deleteByArticleId(id);
		return "redirect:/article";
	}
	
	@RequestMapping("/insert-comment")
	public String insertComment(@Validated CommentForm form, BindingResult result, Integer articleId, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("articleId", articleId);
			return index(model);
		}
		Comment comment = new Comment();
		BeanUtils.copyProperties(form, comment);
		comment.setArticleId(articleId);
		commentRepository.insert(comment);
		return "redirect:/article";
	}
}
