package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Comment;

@Repository
public class CommentRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Comment> COMMENT_ROW_MAPPER
	 = new BeanPropertyRowMapper<>(Comment.class);
	
	public List<Comment> findByArticle(Integer articleId) {
		String sql = "SELECT * FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		List<Comment> commentList = template.query(sql, param, COMMENT_ROW_MAPPER);
		
		return commentList;
	} 
	
	public void insert(Comment comment) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
		System.out.println(comment);
		String sql = "INSERT INTO comments(name, content, article_id) VALUES(:name, :content, :articleId);";
		template.update(sql, param);
	}
	
	public void deleteByArticleId(Integer articleId) {
		String sql = "DELETE FROM comments WHERE article_id = :articleId;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("articleId", articleId);
		template.update(sql, param);
	} 
	
}
