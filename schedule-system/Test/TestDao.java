import com.atguigu.schedule.dao.SysScheduleDao;
import com.atguigu.schedule.dao.impl.SysScheduleDaoImpl;
import com.atguigu.schedule.pojo.SysSchedule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class TestDao{

    private static SysScheduleDao sysScheduleDao;

    @BeforeAll
    public static void initSysScheduleDao(){
        sysScheduleDao=new SysScheduleDaoImpl();
    }

    @Test
    public void testAddSchedule(){
        int rows = sysScheduleDao.addSchedule(new SysSchedule(null, 2, "学习数据库", 1));

        System.out.println(rows);
    }

    @Test
    public void testFindAllSchedule(){
        List<SysSchedule> allSchedules = sysScheduleDao.findAll();
        for (SysSchedule schedule:allSchedules){
            System.out.println(schedule);
        }
    }

}