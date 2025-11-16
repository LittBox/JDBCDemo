package dao;

import entity.Student;

import java.util.List;

public interface IStuDao {
    int AddStuInf(Student stu);
    int DeleteStuInf(String id);
    int UpdateStuInf(Student stu);
    List<Student> QueryAllStuInf();
    Student QueryStuInfById(String id);
}
