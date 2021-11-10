package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

@Repository
public class ArticleRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
//	private static final RowMapper<Article> ARTICLE_ROW_MAPPER
//	 = new BeanPropertyRowMapper<>(Article.class);
	
	private static final ResultSetExtractor<List<Article>> ARTICLE_COMMENT_RESULTSET
			=(rs) -> {
				//返すための空のList<Article>を宣言
				List<Article> ArticleList = new ArrayList<>();
				
				//commentを格納するList<Comment>を宣言
				List<Comment> commentList = null;
				
				//テーブルが複数行存在する可能性があるので、前のレコードのidを保持
				int beforId = 0;
				
				//rsに格納された複数のデータ
				while(rs.next()) {
					//現在参照しているArticleのidを格納するための変数を宣言
					int nowId = rs.getInt("id");
					
					//現在参照しているレコードのidと前のレコードのidが違う場合には新たにオブジェクトを作成
					if(beforId != nowId) {
						Article article = new Article();
						article.setId(nowId);
						article.setName(rs.getString("name"));
						article.setContent(rs.getString("content"));
						commentList = new ArrayList<Comment>();
						article.setCommentList(commentList);
						ArticleList.add(article);
					}
					
					if(rs.getInt("com_id") != 0) {
						Comment comment = new Comment();
						comment.setId(rs.getInt("com_id"));
						comment.setName(rs.getString("com_name"));
						comment.setContent(rs.getString("com_content"));
						//commentをオブジェクト内にセット
						commentList.add(comment);
					}
					//現在参照しているレコードのidを前参照していたidにセットする。
					beforId = nowId;
				}
				return ArticleList;
			};
	
//	public List<Article> findAll() {
//		String sql = "SELECT * FROM articles ORDER BY id DESC;";
//		List<Article> articleList = template.query(sql, ARTICLE_ROW_MAPPER);
//		return articleList;
//	}
	
	public void insert(Article article) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(article);
		String sql = "INSERT INTO articles(name, content) VALUES(:name, :content);";
		template.update(sql, param);
	}
	
	public void deleteById(Integer id) {
		String sql = "DELETE FROM articles WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	public List<Article> findAll() {
		String sql = "SELECT a.id, a.name, a.content, c.id AS com_id, "
				+ "c.name AS com_name, c.content AS com_content, c.article_id "
				+ "FROM articles AS a LEFT OUTER JOIN comments AS c ON a.id = c.article_id;";
		
		
		List<Article> articleList = template.query(sql, ARTICLE_COMMENT_RESULTSET);
		
		return articleList;
	}
}
