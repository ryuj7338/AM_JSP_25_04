package com.KoreaIT.java.AM_jsp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Article;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class ArticleDao {

	private Connection conn;

	public ArticleDao(Connection conn) {
		this.conn = conn;
	}

	public int getTotalCnt() {

		SecSql sql = SecSql.from("SELECT COUNT(*)");
		sql.append("FROM article;");

		return DBUtil.selectRowIntValue(conn, sql);
	}

	public List<Article> getForPrintArticles(int limitFrom, int itemsInAPage) {

		SecSql sql = SecSql.from("SELECT A.*, M.name");
		sql.append("FROM article AS A");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON A.memberId = M.id");
		sql.append("ORDER BY A.id DESC");
		sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);

		List<Map<String,Object>> articleRows = DBUtil.selectRows(conn, sql);

		List<Article> articles = new ArrayList<>();

		for (Map<String, Object> articleMap : articleRows) {
			articles.add(new Article(articleMap));
		}

		return articles;
	}

}
