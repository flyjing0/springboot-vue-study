package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Constans;
import com.wen.common.Result;
import com.wen.entity.Item;
import com.wen.entity.News;
import com.wen.entity.Project;
import com.wen.entity.ResultItems;
import com.wen.mapper.ItemMapper;
import com.wen.mapper.ProjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Resource
    ProjectMapper projectMapper;
    @Resource
    ItemMapper itemMapper;

//    添加用户的
    @PostMapping
    public Result<?> save(@RequestBody Project project){
        project.setProjectId(UUID.randomUUID().toString().replaceAll("-", ""));
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
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "")String projectId){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Project::getProjectName,search);
        }
        if (StrUtil.isNotBlank(projectId)){
            wrapper.eq(Project::getProjectId,projectId);
        }
        wrapper.eq(Project::getValidFlag, Constans.VAILD_FALG_YES);
        Page<Project> projectsPage = projectMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(projectsPage);
    }

    @GetMapping("/getList")
    public Result<?> getList(@RequestParam(defaultValue = "")String projectId){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        if (StrUtil.isNotBlank(projectId)){
            wrapper.eq(Project::getProjectId,projectId);
        }
        wrapper.eq(Project::getValidFlag, Constans.VAILD_FALG_YES);
        return Result.success(projectMapper.selectList(wrapper));
    }

}
