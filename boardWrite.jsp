<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>게시판 쓰기</title>
<script type = "text/javascript">

function check(){ 
	
	var fm = document.frm;
	if(fm.subject.value ==""){ 
		alert("제목 입력 ");
		fm.subject.focus();
		return;
		}else if(fm.contents.value ==""){
			alert("내용입력 ");
			fm.subject.focus();
			return;
		}else if(fm.contents.value ==""){
			alert("작성자 입력 ");
			fm.subject.focus();
			return;
}


fm.action = "<%=request.getContextPath()%>/board/boardWriteAction.do";
fm.method="post";
fm.enctype="multipart/form-data";
fm.submit();
return;
}


</script>
</head>
<body>
게시판 글쓰기
<form name="frm">
<table border=1 style="width:500px;">
<tr>
<td>제목</td>
</tr>
<td>내용</td>
<td><textarea name="contents" cols="50"  rows="5"></textarea></td>
</tr>
<tr>
<td>작성자</td>
<td><input type="text" name="writer" maxlength=5></td>
</tr>
<tr>
<td>파일첨부</td>
<td><input type="file" name="filename"></td>
</tr>

<tr><td colspan=2>
<input type="button" name="btn"  value="확인" onclick="check();">
<input type="reset" name="rst" value="리셋">
</td></tr>
</table>
</form>
</body>
</html>