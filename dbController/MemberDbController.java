package com.dbController;

import com.db.DBConnection;
import com.model.Member;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class MemberDbController {

    public static int AddMember(Member member)throws ClassNotFoundException,SQLException {
        String SQL="INSERT INTO members VALUES(?,?,?,?,?,?,?,?,?)";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(SQL);
        stm.setObject(1, member.getMemberId());
        stm.setObject(2, member.getName());
        stm.setObject(3, member.getDoa());
        stm.setObject(4, member.getGender());
        stm.setObject(5, member.getEmail());
        stm.setObject(6, member.getPhoneNo());
        stm.setObject(7, member.getType());
        stm.setObject(8, member.getGrade());
        stm.setObject(9, member.getAddress());

        return  stm.executeUpdate();
    }

    public static int DeleteMember(Integer memberId) throws ClassNotFoundException, SQLException {

        String sql = "DELETE FROM members WHERE memberId ='"+memberId+"'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);

        return  stm.executeUpdate();
    }

    public static Member searchMember(Integer memberId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM members WHERE memberId = ? ";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, memberId);
        ResultSet rst=stm.executeQuery();

        if(rst.next()){
            Member member = new Member(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9));
            return member;
        }
        return null;
    }

    public static int updateMember(Member member) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE members SET memberId= ? ,name= ? ,doa= ? ,Gender= ? ,email= ? ,phoneNo= ? ,type=? ,grade=? ,address=? WHERE memberId= '" +member.getMemberId()+ "'";
        Connection conn = DBConnection.getDBConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        stm.setObject(1, member.getMemberId());
        stm.setObject(2, member.getName());
        stm.setObject(3, member.getDoa());
        stm.setObject(4, member.getGender());
        stm.setObject(5, member.getEmail());
        stm.setObject(6, member.getPhoneNo());
        stm.setObject(7, member.getType());
        stm.setObject(8, member.getGrade());
        stm.setObject(9, member.getAddress());

        return  stm.executeUpdate();
    }

    public static ArrayList<Member> getAllMembers() throws ClassNotFoundException, SQLException{
        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("Select * From members");
        ArrayList <Member> userList = new ArrayList<>();
        while(rst.next()){
            Member member;
            member = new Member(rst.getInt(1),rst.getString(2),rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9));
            userList.add(member);
        }
        return userList;
    }

    public static boolean checkMemberID(int memberId) throws SQLException, ClassNotFoundException {
        boolean memberIdExists = true;

        Connection conn=DBConnection.getDBConnection().getConnection();
        Statement stm = conn.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM members WHERE memberId='"+memberId+"'");

        int id;
        if (rst.next()){
            id = rst.getInt("memberId");
            if(id == memberId){
                memberIdExists = false;
            }
        }
        return memberIdExists;
    }

} //End of the Class
