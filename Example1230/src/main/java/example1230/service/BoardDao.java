
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
		
		
		String sql ="select * from board1230 where delyn='n' order by bidx desc";
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try {
			pstmt=conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			BoardVo bv = new BoardVo();	
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
						String sql = "INSERT INTO board1230(bidx,subject,contents,writer,ip,midx)VALUES(bidx_seq.NEXTVAL,?,?,?,?,?)";
						PreparedStatement pstmt=null;
						try {
					pstmt=	conn.prepareStatement(sql);
					pstmt.setString(1, bv.getSubject());
					pstmt.setString(2, bv.getContents());
					pstmt.setString(3, bv.getWriter());
					pstmt.setString(4, bv.getIp());
					pstmt.setInt(5, bv.getMidx());
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
		int value=0;
		String sql ="update board1230 set subject=?, contents=?,writer=? where bidx=?";
		
		PreparedStatement pstmt=null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getSubject());
			pstmt.setString(2, bv.getContents());
			pstmt.setString(3, bv.getWriter());
			pstmt.setInt(4, bv.getBidx());
			value = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return value;
	} 
	
	
		}

