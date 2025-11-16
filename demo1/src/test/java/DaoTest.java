import dao.StuDao;
import entity.Student;
import org.junit.jupiter.api.Test;



public class DaoTest {
    Student stu =  new Student();
    StuDao stuDao = new StuDao();

    @Test
    public void insert(){
        stu.setStuId("20221120088");
        stu.setStuName("Sam");
        stu.setAge(18);
        stu.setSex(Student.Sex.male);
        stu.setStuPwd("123456");
        int res = stuDao.AddStuInf(stu);
        if(res>0){
            System.out.println("新增"+res+"行");
        }else{
            System.out.println("新增失败");
        }
    }

    @Test
    public void update(){
        stu.setStuId("20221120088");
        stu.setStuName("Sam");
        stu.setAge(25);
        stu.setStuPwd("123456");
        stu.setSex(Student.Sex.female);
        int res =  stuDao.UpdateStuInf(stu);
        if(res>0){
            System.out.println("更新"+res+"行");
        }else{
            System.out.println("更新失败");
        }
    }

    @Test
    public void delete(){
        int result = stuDao.DeleteStuInf("20221120088");
        if(result>0){
            System.out.println("成功删除"+result+"行");
        }else{
            System.out.println("删除失败");
        }
    }

    @Test
    public void query(){
        System.out.println(stuDao.QueryStuInfById("20221120088"));
    }

    @Test
    public void queryAll(){
        System.out.println(stuDao.QueryAllStuInf());
    }
}
