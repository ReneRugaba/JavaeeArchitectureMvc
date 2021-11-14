package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Joueur;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    public Joueur create(Joueur jouer){
        Connection conn = null;
        try {

            DataSource data = DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();

            long id = 43L;
            PreparedStatement stm=conn.prepareStatement("INSERT INTO JOUEUR (NOM,PRENOM,SEXE) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setString(1,jouer.getNom());
            stm.setString(2,jouer.getPrenom());
            stm.setString(3,jouer.getSexe().toString());

            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            System.out.println("success");
            if(rs.next()){
                return new Joueur(){{
                    setId((long) rs.getLong(1));
                }};
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void update(Joueur jouer){
        Connection conn = null;
        try {

            DataSource data = DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();

            PreparedStatement stm=conn.prepareStatement("UPDATE JOUEUR SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");
            stm.setString(1,jouer.getNom());
            stm.setString(2,jouer.getPrenom());
            stm.setString(3,jouer.getSexe().toString());
            stm.setLong(4,jouer.getId());

            stm.executeUpdate();

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Long id){
        Connection conn = null;
        try {

            DataSource data = DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();

            PreparedStatement stm=conn.prepareStatement("DELETE FROM JOUEUR WHERE ID=?");
            stm.setLong(1,id);

            stm.executeUpdate();

            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur getById(Long id){
        Connection conn = null;
        try {

            DataSource data = DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();

            PreparedStatement stm=conn.prepareStatement("SELECT * FROM JOUEUR  WHERE ID=?");

            stm.setLong(1,id);

            ResultSet res=stm.executeQuery();
            if(res.next()){
                return new Joueur(){{
                    setId(res.getLong("ID"));
                    setNom(res.getString("NOM"));
                    setPrenom(res.getString("PRENOM"));
                    setSexe(res.getString("SEXE").charAt(0));
                }};
            }

            System.out.println("Find a Joueur");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Joueur> list(){
        Connection conn=null;
        List<Joueur> listJoueur=new ArrayList<>();
        try{
            DataSource data = DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM JOUEUR");
            ResultSet res=stm.executeQuery();

            while (res.next()){
                Joueur joueur=new Joueur(){{
                    setId(res.getLong("ID"));
                    setNom(res.getString("NOM"));
                    setPrenom(res.getString("PRENOM"));
                    setSexe(res.getString("SEXE").charAt(0));
                }};
                listJoueur.add(joueur);
            }
        }catch (SQLException e){

        }
        return listJoueur;
    }
}
