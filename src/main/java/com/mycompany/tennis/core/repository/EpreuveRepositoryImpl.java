package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Epreuve;

import javax.sql.DataSource;
import java.sql.*;

public class EpreuveRepositoryImpl {

    public Epreuve create(Epreuve epreuve){
        Connection conn=null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm= conn.prepareStatement("INSERT INTO EPREUVE (ANNEE,TYPE_EPREUVE,ID_TOURNOI) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setShort(1,epreuve.getAnnee());
            stm.setString(2,epreuve.getTypeEpreuve().toString());
            stm.setLong(3,epreuve.getTournoi().getId());
            stm.executeUpdate();
            ResultSet rs=stm.getGeneratedKeys();
            if (rs.next()){
                return new Epreuve(){{
                    setId(rs.getLong("ID"));
                }};
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (conn!=null){
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
