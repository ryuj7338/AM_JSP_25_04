package com.KoreaIT.java.AM_jsp.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.dto.Article;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

public class MemberDao {

	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	

}
