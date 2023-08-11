package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
/*         查询总记录数
        Long count = empMapper.count();
         查询每页记录
        List<Emp> list = empMapper.page((page - 1) * pageSize, pageSize);
         封装PageBean对象
        PageBean pageBean = new PageBean(count, list);*/

        PageHelper.startPage(page, pageSize);
        List<Emp> list = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) list;
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setEntrydate(LocalDate.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        emp.setDeptId(1);
        emp.setUsername("wuzhikan");
        empMapper.add(emp);
    }
}
