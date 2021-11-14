package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    public Match create(Match match){
        Connection conn=null;
        try{
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn= data.getConnection();
            PreparedStatement stm= conn.prepareStatement("INSERT INTO MATCH_TENNIS (ID_EPREUVE,ID_VAINQUEUR,ID_FINALISTE) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setLong(1,match.getEpreuve().getId());
            stm.setLong(2,match.getVainqueur().getId());
            stm.setLong(3,match.getFinaliste().getId());
            ResultSet rs=stm.executeQuery();
            if(rs.next()){
                return new Match(){{
                    setId(rs.getLong("ID"));
                }};
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
}
