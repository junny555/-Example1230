package example1230.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.IssuerSerialNumRequest;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.JoinRowSet;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.apache.catalina.tribes.ChannelSender;
import org.apache.coyote.Request;

import example1230.domain.BoardVo;
import example1230.service.BoardDao;
import oracle.sql.BlobDBAccess;


@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	String str;
	public BoardController(String path) {
		this.str=path;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		if(str.equals("/board/boardList.do")) {
			System.out.println("boardList.do 들어옴");
			
			
			BoardDao bd=new BoardDao();
			ArrayList<BoardVo> blist = bd.boardSelectAll();
			
			request.setAttribute("blist", blist);
			
			RequestDispatcher rd =request.getRequestDispatcher("/board/boardList.jsp");
			rd.forward(request, response);
		}
		
		else if (str.equals("/board/boardWrite.do")) {
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
			rd.forward(request, response);
		}else if (str.equals("/board/boardWriteAction.do")) {
			String subject = request.getParameter("subject");
			String contents = request.getParameter("contents");
			String writer = request.getParameter("writer");
			String ip= request.getLocalAddr();
			int midx= 10;
			
	
		//	String ip= request.getRemoteAddr();
		
			//처리하는 부분 
			//List
			BoardVo bv = new BoardVo();
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			bv.setIp(ip);
			bv.setMidx(midx);
 			
			BoardDao bd = new BoardDao();
			
			int value = bd.boardInsert(bv);
			//이동하는 부분 
		if(value==1) {
			response.sendRedirect(request.getContextPath()+"/board/boardList.do");
		}else {
			response.sendRedirect(request.getContextPath()+"/board/boardWrite.do");
		}
		
		}
		
	//contents
		else if(str.equals("/board/boardContents.do")) {
			
		String bidx=	request.getParameter("bidx");
		System.out.println("bidx"+bidx);
		int bidxInt  =Integer.parseInt(bidx);
		
		BoardDao bd= new BoardDao();
		int value = bd.boardViewCnt(bidxInt);
		BoardVo bv=	bd.boardSelectOne(bidxInt); 
	
		request.setAttribute("bv", bv);
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardContents.jsp");
		rd.forward(request, response);	
		}
		
		//Modify
		else if(str.equals("/board/boardModify.do")) {
			String bidx=	request.getParameter("bidx");
			System.out.println("bidx"+bidx);
			int bidxInt  =Integer.parseInt(bidx);
			
			BoardDao bd= new BoardDao();
			BoardVo bv =	bd.boardSelectOne(bidxInt); 
			
			request.setAttribute("bv", bv);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
			rd.forward(request, response);	
			}
			else if (str.equals("/board/boardModifyAction.do")) {
				System.out.println("boardModifyAction.do 들어옴");
			String bidx=request.getParameter("bidx");	
			int bidxint = Integer.parseInt(bidx);
			String subject=request.getParameter("subject");			
			String contents=request.getParameter("contents");			
			String writer=request.getParameter("writer");			
			
			//update 
			
			BoardVo bv=new BoardVo();
			bv.setBidx(bidxint);
			bv.setSubject(subject);
			bv.setContents(contents);
			bv.setWriter(writer);
			
			BoardDao bd= new BoardDao();
			int value= bd.boardModify(bv);
			
			if(value == 1) {
			
				response.sendRedirect(request.getContextPath()+"/board/boardContents.do?bidx="+bidx);
			}else {
				response.sendRedirect(request.getContextPath()+"/board/boardModify.do?bidx="+bidx);
			}
		}	
			
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
		
		
		
	}

	
	
	
	
	
	
	
	
	


  