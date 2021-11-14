package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Tournoi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TournoiRepositoryImpl {

    public Tournoi create(Tournoi tournoi){
        Connection conn = null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO TOURNOI (NOM,CODE) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,tournoi.getNom());
            stm.setString(2,tournoi.getCode());
            stm.executeUpdate();

            ResultSet rs=stm.getGeneratedKeys();
            System.out.println("Tournoi créé");
            if(rs.next()){
                return new Tournoi(){{setId(rs.getLong(1));}};
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

    public Tournoi update(Tournoi tournoi){
        Connection conn = null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm = conn.prepareStatement("UPDATE TOURNOI SET NOM=?,CODE=? WHERE ID=?", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,tournoi.getNom());
            stm.setString(2,tournoi.getCode());
            stm.setLong(3,tournoi.getId());
            stm.executeUpdate();

            ResultSet rs=stm.getGeneratedKeys();
            System.out.println("Tournoi modifié");
            if(rs.next()){
                return new Tournoi(){{setId(rs.getLong(1));}};
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }

        return null;
    }

    public Tournoi getById(Long id){
        Connection conn=null;
        try{
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM TOURNOI WHERE ID=?");
            stm.setLong(1,id);
            ResultSet rs=stm.executeQuery();
            if(rs.next()){
                return new Tournoi(){{
                    setId(rs.getLong("ID"));
                    setNom(rs.getString("NOM"));
                    setCode(rs.getString("CODE"));
                }};
            }
        }catch (SQLException e){

        }
        return null;
    }

    public void delete(Long id){
        Connection conn = null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm = conn.prepareStatement("DELETE FROM TOURNOI WHERE ID=?");

            stm.setLong(1,id);
            stm.executeUpdate();


            System.out.println("Tournoi supprimé");


        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }
        }
    }

    public List<Tournoi> list(){
        Connection conn=null;
        try{
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM TOURNOI");
            ResultSet rs=stm.executeQuery();

                List<Tournoi> listTournoi=new ArrayList<>();

                while (rs.next()){
                    Tournoi tournoi=new Tournoi(){{
                        setId(rs.getLong("ID"));
                        setNom(rs.getString("NOM"));
                        setCode(rs.getString("CODE"));
                    }};
                    listTournoi.add(tournoi);
                }
                return listTournoi;

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {
                    conn.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
