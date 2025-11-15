package entity;

import lombok.Data;

@Data
public class Student {

    public enum Sex{
        male,
        female
    }
    String stuId ;
    String stuName;
    Sex sex;
    Integer age;
    String stuPwd;

}
