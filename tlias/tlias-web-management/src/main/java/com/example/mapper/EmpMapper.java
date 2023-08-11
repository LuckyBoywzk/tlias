package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
//    /**
//     * 查询总记录数
//     * @return
//     */
//    @Select("select count(*) from emp")
//    Long count();

//    /**
//     * 分页查询，查询每页的列表信息
//     * @param start
//     * @param pageSize
//     * @return
//     */
//    @Select("select * from emp limit #{start}, #{pageSize}")
//    List<Emp> page(Integer start, Integer pageSize);


    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据id删除数据
     * @param ids
     * @return
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values " +
            "(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

}
