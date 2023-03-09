<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 답변</title>
<script type="text/javascript">
function check(){
		var fm= document.frm;
		if(fm.subject.value==""){
			alert("제목입력");
			fm.subject.focus();
			return;
			
		}else if (fm.contents.value ==""){
			alert("내용입력");
			fm.subject.focus();
			return;
		}else if (fm.writer.value ==""){
			alert("작성자입력");
			fm.subject.focus();
			return;
		
			
			
	
	
	
}
fm.action="<%=request.getContextPath()%>/board/boardwrtieAction.do">
fm.mehod="post";
fm.submit();
return;
}
</script>
</head>
<body>
 게시판 글 답변 
 <form name="frm">
 
 <table  border=1 style="width:500px;">
<tr>
<td>제목</td>
<td><input type="text" name="subject"></td>
</tr>
<tr>
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