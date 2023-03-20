
package example1230.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.ValueExp;

import org.apache.catalina.startup.SetAllPropertiesRule;

import example1230.dbconn.Dbconn;
import example1230.domain.BoardVo;

public class BoardDao {

	
	Connection conn;
	
	public BoardDao() {
		Dbconn dbconn = new Dbconn();
	conn=	dbconn.getConnection();
		
	}
	
	public ArrayList<BoardVo>boardSelectAll(){
		ArrayList<BoardVo> blist = new ArrayList<BoardVo>();
		
		
		//String sql ="select * from board1230 where delyn='n' order by originbidx desc,depth asc";
		
	String sql=	"SELECT * FROM("
		
			+ " SELECT ROWNUM AS rnum, A.* FROM ("
			+ "	SELECT * FROM board1230 WHERE delyn='n'ORDER BY originbidx desc,depth asc)A"
			+ "	)B WHERE B.rnum BETWEEN ? AND ?";
		
		
		
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 15);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			BoardVo bv = new BoardVo();	
			
			//확실 ㄴㄴ
		//	bv.setOriginbidx(rs.getInt("orginbidx"));
		//	bv.setDepth(rs.getInt("depth"));
			bv.setLevel_(rs.getInt("level_"));
			bv.setBidx(rs.getInt("bidx"));
			bv.setSubject(rs.getString("subject"));
			bv.setContents(rs.getString("contents"));
			bv.setWriter(rs.getString("writer"));
			blist.add(bv);
			}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
					
				}	catch (Exception e ) {
					e.printStackTrace();
				}
			}
		return blist;
	}

					public int boardInsert(BoardVo bv) {
						int value=0;
						String sql = "INSERT INTO board1230(bidx,originbidx,depth,level_,subject,contents,writer,ip,midx,pwd)VALUES(bidx_seq.NEXTVAL,bidx_seq.NEXTVAL,0,0,?,?,?,?,?,?)";
						PreparedStatement pstmt=null;
						try {
					pstmt=	conn.prepareStatement(sql);
					pstmt.setString(1, bv.getSubject());
					pstmt.setString(2, bv.getContents());
					pstmt.setString(3, bv.getWriter());
					pstmt.setString(4, bv.getIp());
					pstmt.setInt(5, bv.getMidx());
					pstmt.setString(6, bv.getPwd());
					value = pstmt.executeUpdate();
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					
				}try {
					pstmt.close();
					conn.close();
					
					
				}catch (Exception e) {
					e.printStackTrace();
				}
					
				

				return value;
					}
		
	public BoardVo boardSelectOne(int bidx) {
		BoardVo bv=null;
		String sql="select * from board1230 where bidx=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			pstmt=conn.prepareStatement(sql);
		
			pstmt.setInt(1, bidx);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				bv =new BoardVo();
				bv.setSubject(rs.getString("subject"));
				bv.setContents(rs.getString("contents"));
				bv.setWriter(rs.getString("writer"));
				bv.setViewcnt(rs.getString("viewcnt"));
				bv.setBidx(rs.getInt("bidx"));
				bv.setOriginbidx(rs.getInt("originbidx"));
				bv.setDepth(rs.getInt("depth"));
				bv.setLevel_(rs.getInt("level_"));;
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bv;
		
	}
	
	public int boardViewCnt(int bidx) {
		int value=0;
		
		String sql="update board1230 set viewcnt = NVL(viewcnt, 0)+1 where bidx=?";
		PreparedStatement pstmt = null;
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bidx);
			value = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
				
		}finally {
			try {
				pstmt.close();
			} catch ( Exception e) {
			e.printStackTrace();
			}
		}
		return value;
	}
	
	
	public int boardModify(BoardVo bv) {
		System.out.println("비번?"+bv.getPwd());
		int value=0;
		String sql ="update board1230 set subject=?, contents=?,writer=? where bidx=? and pwd=?";
		
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getSubject());
			pstmt.setString(2, bv.getContents());
			pstmt.setString(3, bv.getWriter());
			pstmt.setInt(4, bv.getBidx());
			pstmt.setString(5, bv.getPwd());
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
	
				e.printStackTrace();
			}
			
		}
		
		return value;
	} 
	public int boardDelete(BoardVo bv) {
	//	System.out.println("비밀번호"+bv.getPwd());
	//	System.out.println("비밀번호"+bv.getBidx());
		int value=0;
		
	String sql ="update board1230 set delyn='Y' where bidx=? and pwd=?";
	//	System.out.println("완료");
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setInt(1, bv.getBidx());
			pstmt.setString(2, bv.getPwd());
			value = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
			return value;
	
	}
	public int boardReply(BoardVo bv) {
		int value=0;
		int value2=0;

		String sql="update board1230 set depth=depth+1 where originbidx = ? and depth>?";
		String sql2="insert into board1230(bidx,originbidx,depth,level_,subject,contents,writer,ip,midx,pwd)"+"values(bidx_seq.nextval,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=null;
		try {
			conn.setAutoCommit(false);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth());
			value = pstmt.executeUpdate();
	
			pstmt= conn.prepareStatement(sql2);
			pstmt.setInt(1, bv.getOriginbidx());
			pstmt.setInt(2, bv.getDepth()+1);
			pstmt.setInt(3, bv.getLevel_()+1);
			pstmt.setString(4,bv.getSubject());
			
			pstmt.setString(5, bv.getContents());
			pstmt.setString(6, bv.getWriter());
			pstmt.setString(7, bv.getIp());
			pstmt.setInt(8, bv.getMidx());
			pstmt.setString(9, bv.getPwd());
			
			value2 = pstmt.executeUpdate();
			conn.commit();
			
			
			
			
		} catch (SQLException e) {
	try {
		conn.rollback();
		
	}catch (SQLException e1) {
		e1.printStackTrace();
	}
	
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e ) {
				e.printStackTrace();
			}
		}
		
		
		return value2;
		
	}
	}
		

