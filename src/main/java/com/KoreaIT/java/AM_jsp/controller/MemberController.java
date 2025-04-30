package com.KoreaIT.java.AM_jsp.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import com.KoreaIT.java.AM_jsp.MemberService.MemberService;
import com.KoreaIT.java.AM_jsp.util.DBUtil;
import com.KoreaIT.java.AM_jsp.util.SecSql;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberController {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection conn;

	private MemberService memberService;

	public MemberController(HttpServletRequest request, HttpServletResponse response, Connection conn) {
		this.conn = conn;
		this.request = request;
		this.response = response;
	}

	public void doJoin() throws ServletException, IOException {

		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		String loginPwConfirm = request.getParameter("loginPwConfirm");
		String name = request.getParameter("name");

		SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);

		boolean isJoinableId = DBUtil.selectRowIntValue(conn, sql) == 0;

		if (isJoinableId == false) {
			response.getWriter().append(String
					.format("<script>alert('%s는 이미 사용중입니다.'); location.replace('../member/join');</script>", loginId));
			return;
		}

		sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate = NOW(),");
		sql.append("loginId = ?,", loginId);
		sql.append("loginPw = ?,", loginPw);
		sql.append("`name` = ?;", name);

		int id = DBUtil.insert(conn, sql);

		response.getWriter().append(
				String.format("<script>alert('%s님 회원가입 되었습니다.'); location.replace('../article/list');</script>", name));

	}

	public void doLogin() throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");

		SecSql sql = SecSql.from("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?;", loginId);

		Map<String, Object> memberRow = DBUtil.selectRow(conn, sql);

		System.out.println(memberRow);

		if (memberRow.isEmpty()) {
			response.getWriter().append(String.format(
					"<script>alert('%s는 존재하지 않는 아이디입니다.'); location.replace('../member/login');</script>", loginId));
			return;
		}

		if (memberRow.get("loginPw").equals(loginPw) == false) {
			response.getWriter().append(String.format(
					"<script>alert('비밀번호가 일치하지 않습니다.'); location.replace('../member/login');</script>", loginId));
			return;
		}

		HttpSession session = request.getSession();
		session.setAttribute("loginedMember", memberRow);
		session.setAttribute("loginedMemberId", memberRow.get("id"));
		session.setAttribute("loginedMemberLoginId", memberRow.get("loginId"));

		response.getWriter().append(String.format(
				"<script>alert('%s님 로그인!'); location.replace('../s/home/main');</script>", memberRow.get("name")));
	}

	public void doLogout() throws ServletException, IOException {

		HttpSession session = request.getSession();

		session.removeAttribute("loginedMember");
		session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");

		response.getWriter()
				.append(String.format("<script>alert('로그아웃 되었습니다.'); location.replace('../article/list');</script>"));

	}
}
