package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    public Score create(Score score){
        Connection conn=null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm= conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET1,SET2,SET3,SET4,SET5) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setLong(1,score.getMatch().getId());
            stm.setByte(2,score.getSet1());
            stm.setByte(3,score.getSet2());
            stm.setByte(4,score.getSet3());
            stm.setByte(5,score.getSet4());
            stm.setByte(6,score.getSet5());

            stm.executeUpdate();

            ResultSet rs= stm.getGeneratedKeys();

            if (rs.next()){
                return new Score(){{
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
