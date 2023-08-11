package com.example.controller;

import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class DeptController {
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//    @RequestMapping(value = "/depts", method = RequestMethod.GET)
    @GetMapping("/depts")
    public Result getList() {
        log.info("查询全部部门信息");
        List<Dept> list = deptService.list();
        return Result.success(list);
    }

    @DeleteMapping("/depts/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除id为"+ id +"的部门信息");
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门信息");
        deptService.add(dept);
        return Result.success();
    }
}
