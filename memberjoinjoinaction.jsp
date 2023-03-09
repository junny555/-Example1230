<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
  
    <%@ page import = "java.sql.*" %>
    <%
    		request.setCharacterEncoding("utf=8");
    
    		String memberId=request.getParameter("memberId");
    		String memberPw=request.getParameter("memberPw");
    		String memberName = request.getParameter("memberName");
    		String memberPhone = request.getParameter("memberPhone");
    		String memberEmail = request.getParameter("memberEmail");
    		String memberGender = request.getParameter("memberGender");
    		String memberAddr = request.getParameter("memberAddr");
    		String[] memberBirthArray = request.getParameterValues("memberBirth");
    		String memberBirth= memberBirthArray[0]+memberBirthArray[1]+memberBirthArray[2];
    		
    				
    		out.println("Id :"+memberId+"</br>");	
    		out.println("Pw :"+memberPw+"</br>");	
    		out.println("Name :"+memberName+"</br>");	
    		out.println("Phone :"+memberPhone+"</br>");	
    		out.println("Email :"+memberEmail+"</br>");	
    		out.println("Gender :"+memberGender+"</br>");	
    		out.println("Addr :"+memberAddr+"</br>");	
    		out.println("Birth :"+memberBirth+"</br>");
    	
    		
    		String url="jdbc:oracle:thin:@127.0.0.1:1521:xe";
    		String user="system";
    		String password ="1234";
    		
    		Class.forName("oracle.jdbc.driver,OracleDriver"); //동적로딩- 메모리에 올림 
    		
    		Connection conn= DriverManager.getConnection(url,user,password); //db와 접속시킴
    
    
    		String sql = "INSERT INTO member1230(midx,memberId,memberPw,memberName,memberEmail,memberGender,memberAddr,memberBirth,ip)"
    
    		+"VALUES(midx_seq.nextval,'"+memberId+"','"+memberPw+"','"+memberName+"','"+memberEmail+"','"+memberGender+"','"+memberAddr+"','"+memberBirth+"',null)";
    		//구분 쿼리 클래스 
    		Statement stmt = conn.createStatement();
    		stmt.execute(sql);
    		stmt.close();
    		conn.close();
    		
    %>
    
    
    
    
    
    
    
    





<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>