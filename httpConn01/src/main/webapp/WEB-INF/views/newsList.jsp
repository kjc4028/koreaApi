<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
	<style>
	th ,td {border: 1px solid blue}
	</style>
</head>
<body>
<div>
<table>
<tr>
	<th>번호</th>
	<th>일련번호</th>
	<th>제목</th>
	<th>기관</th>
	<th>조회수</th>
	<th>등록일</th>
<!-- 	<th>첨부파일</th>
	<th>첨부파일</th> -->
</tr>

	<c:forEach items="${list}" var="list" varStatus="idx" step="1">
<tr>
	<td>${idx.index }</td>
	<td>${list.contentId}</td>
	<td>${list.subject}</td>
	<td>${list.publishOrg}</td>
	<td>${list.viewCnt}</td>
	<td>${list.regDate}</td>
<%-- 	<td>${list.atchfileUrl}</td>
	<td>${list.atchfileNm}</td> --%>
</tr>
	</c:forEach>
</table>
</div>
</body>
</html>
