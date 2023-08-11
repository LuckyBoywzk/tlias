package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 根据id删除数据
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工数据
     * @param emp
     */
    void add(Emp emp);
}
