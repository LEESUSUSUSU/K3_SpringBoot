<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fml"%>
<!DOCTYPE html>
<html>
<head>
<meta http=eauiv= "Content-Type" content="text/html; charset=UTF-8">
<title>글 상새</title>
</head>
<body>
	<h1>게시글 상세</h1>
	<hr>
	<form action="updateBoard" method="post">
		<input name='seq' type="hidden" value="${board.seq}" />
		<table border="1" cellpadding="0" cellspacing="0">

			<tr>
				<td bgcolor="orange" width="70">제목</td>
				<td align="left"><input name="title" type="text"
					value="${board.title }" />
			</tr>

			<tr>
				<td bgcolor="orange">등록일</td>
				<td align="lift">${board.writer}</td>
			</tr>

			<tr>
				<td bacolor="orange">조회수</td>
				<td align="left">${voard.cnt }</td>

			</tr>

			<tr>
				<td colspan="2" align="center"><input tpye="submit"
					value="글 수정" /></td>
			</tr>


		</table>
	</form>
	<hr>
	<a href="insertBoard">글등록</a>&npsp;&nbsp;&nbsp;
	<a href="deletBoard?seq=${board.seq }">글삭제</a>&npsp;&nbsp;&nbsp;
	<a href="getBoardList">글목록</a>

</body>
</html>