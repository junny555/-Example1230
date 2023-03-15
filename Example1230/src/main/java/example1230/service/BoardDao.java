
package example1230.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		
	
		}

