package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Project;
import com.wen.mapper.ProjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Resource
    ProjectMapper projectMapper;

//    添加用户的
    @PostMapping
    public Result<?> save(@RequestBody Project project){
        projectMapper.insert(project);
        return Result.success();
    }
//    更新
    @PutMapping
    public Result<?> update(@RequestBody Project project){
        projectMapper.updateById(project);
        return Result.success();
    }
    //    s删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        projectMapper.deleteById(id);
        return Result.success();
    }
//    查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Project::getProjectName,search);
        }
        Page<Project> projectPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectPage);
    }
}
