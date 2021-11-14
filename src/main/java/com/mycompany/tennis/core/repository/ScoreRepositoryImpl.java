package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    public Score create(Score score){
        Connection conn=null;
        Score nwScore=null;
        try {
            DataSource data= DataSourceProvider.getDatesourceProvider();
            conn=data.getConnection();
            PreparedStatement stm= conn.prepareStatement("INSERT INTO SCORE_VAINQUEUR (ID_MATCH,SET_1,SET_2,SET_3,SET_4,SET_5) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stm.setLong(1,score.getMatch().getId());
            stm.setByte(2,score.getSet1());
            stm.setByte(3,score.getSet2());

            if (score.getSet3() != null) {
                stm.setByte(4, score.getSet3());
            } else {
                stm.setNull(4, Types.TINYINT);
            }

            if (score.getSet3() != null) {
                stm.setByte(5,score.getSet4());
            } else {
                stm.setNull(5, Types.TINYINT);
            }

            if (score.getSet3() != null) {
                stm.setByte(6,score.getSet5());
            } else {
                stm.setNull(6, Types.TINYINT);
            }




            stm.executeUpdate();

            ResultSet rs= stm.getGeneratedKeys();

            if (rs.next()){
                nwScore=new Score(){{
                    setId(rs.getLong(1));
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
        return nwScore;
    }
}
