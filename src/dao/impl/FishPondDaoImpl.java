package dao.impl;

import dao.FishPondDao;
import domain.FishPond;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;



public class FishPondDaoImpl implements FishPondDao {

    private final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getCapacity(int userId) {
        try {
            String sql = "select * from fishpond where userId = ?";
            FishPond fishPond = template.queryForObject(sql,new BeanPropertyRowMapper<FishPond>(FishPond.class),userId);
            if (fishPond != null){
                return fishPond.getCapacity();
            }
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }
}
