package com.shane.dao;

import com.shane.model.AddReimbur;
import com.shane.model.Reimbursement;
import com.shane.model.UpdateReimbur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImp implements ReimbursementDao {
    private static ReimbursementDao reimbursementDao;
    private ReimbursementDaoImp(){
        try{Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static ReimbursementDao getInstance(){
        if(reimbursementDao == null)
            reimbursementDao = new ReimbursementDaoImp();
        return reimbursementDao;
    }

    //view all reimbursements
    @Override
    public List<Reimbursement> getAllReimbursement() {
        List<Reimbursement> reimbursement = new ArrayList<>();

        //Establish a connection
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){

            //Sql Statement
            String sql = "SELECT * FROM reimbur";

            PreparedStatement ps = conn.prepareStatement(sql);

            //Result from query
            ResultSet rs = ps.executeQuery();

            //Runs through all the records and adds to account
            while(rs.next()){
                reimbursement.add( new Reimbursement(rs.getInt(1), rs.getInt(2),rs.getTimestamp(3),
                        rs.getTimestamp(4),rs.getString(5),rs.getByte(6),rs.getInt(7),
                        rs.getInt(8),rs.getInt(9), rs.getInt(10))
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    //Get reimbursements by ID
    @Override
    public List<Reimbursement> getAllMyReimbur(Integer userId) {
        List<Reimbursement> reimbursement = new ArrayList<>();

        //Establish a connection
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){

            //Sql Statement
            String sql = "select * from reimbur where reimbur_author_fk =?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            //Result from query
            ResultSet rs = ps.executeQuery();

            //Runs through all the records and adds to account
            while(rs.next()){
                reimbursement.add( new Reimbursement(rs.getInt(1), rs.getInt(2),rs.getDate(3),
                        rs.getDate(4),rs.getString(5),rs.getByte(6),rs.getInt(7),
                        rs.getInt(8),rs.getInt(9), rs.getInt(10))
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    //Add reimbursements by ID
    @Override
    public void addMyReimbur(AddReimbur addReimbur) {
        try (Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)) {
            //Sql Statement
            String sql = "insert into reimbur values (default, ?, current_Timestamp, null, ?, null, ?, null, 1, ?);";
            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, addReimbur.getReimburAmount());
            ps.setString(2, addReimbur.getReimburDescription());
            ps.setInt(3,addReimbur.getReimburAuthorFk());
            ps.setInt(4, addReimbur.getReimburTypeFk());

           ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reimbursement> getOneReimbur(Integer reimburId) {
        List<Reimbursement> reimbursement = new ArrayList<>();

        //Establish a connection
        try(Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)){

            //Sql Statement
            String sql = "select * from reimbur where reimbur_id =?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimburId);

            //Result from query
            ResultSet rs = ps.executeQuery();

            //Runs through all the records and adds to account
            while(rs.next()){
                reimbursement.add( new Reimbursement(rs.getInt(1), rs.getInt(2),rs.getDate(3),
                        rs.getDate(4),rs.getString(5),rs.getByte(6),rs.getInt(7),
                        rs.getInt(8),rs.getInt(9), rs.getInt(10))
                );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return reimbursement;
    }

    //update the status of Reimbursement
    @Override
    public void updateReimburStatus(UpdateReimbur updateReimbur) {
        try (Connection conn = DriverManager.getConnection(ConnectionUtil.url, ConnectionUtil.username, ConnectionUtil.password)) {

            //Sql Statement
            String sql = "update reimbur set reimbur_resolved = current_timestamp, reimbur_resolver_fk= ?, reimbur_status_fk=? where reimbur_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);


            ps.setInt(1, updateReimbur.getReimburResolverFk());
            ps.setInt(2, updateReimbur.getReimburStatusFk());
            ps.setInt(3, updateReimbur.getReimburId());


            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
