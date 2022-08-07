package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wen.common.Constans;
import com.wen.common.Result;
import com.wen.entity.Item;
import com.wen.entity.Project;
import com.wen.entity.ResultItems;
import com.wen.mapper.ItemMapper;
import com.wen.mapper.ProjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    @Resource
    ProjectMapper projectMapper;
    @Resource
    ItemMapper itemMapper;



//    查询
    @GetMapping
    @RequestMapping("/search")
    public Result<?> search(@RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getValidFlag, Constans.VAILD_FALG_YES);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Project::getProjectName,search);
        }
        List<Project> projectList = projectMapper.selectList(wrapper);
        LambdaQueryWrapper<Item> iteWwrapper = Wrappers.<Item>lambdaQuery();
        List<Item> itemList = itemMapper.selectList(iteWwrapper);
        List<ResultItems> items = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++) {
            ResultItems item = new ResultItems();
            item.setId(projectList.get(i).getProjectId());
            item.setText(projectList.get(i).getProjectName());
            if(i == 0){
                List<ResultItems> children = new ArrayList<>();
                for (int k = 0; k < itemList.size(); k++) {
                    if(projectList.get(i).getProjectId().equals(itemList.get(k).getProjectId())){
                        ResultItems relustItem = new ResultItems();
                        relustItem.setId(itemList.get(k).getItemId());
                        relustItem.setText(itemList.get(k).getItemName());
                        relustItem.setItemId(itemList.get(k).getItemId());
                        children.add(relustItem);
                    }
                }
                item.setChildren(children);
            }
            items.add(item);
        }

        return Result.success(items);
    }

    //  切换菜单
    @GetMapping
    @RequestMapping("/change")
    public Result<?> change(@RequestParam(defaultValue = "") int index){
        LambdaQueryWrapper<Project> wrapper = Wrappers.<Project>lambdaQuery();
        wrapper.eq(Project::getValidFlag, Constans.VAILD_FALG_YES);
        List<Project> projectList = projectMapper.selectList(wrapper);
        LambdaQueryWrapper<Item> iteWwrapper = Wrappers.<Item>lambdaQuery();
        List<Item> itemList = itemMapper.selectList(iteWwrapper);
        List<ResultItems> items = new ArrayList<>();
        for (int i = 0; i < projectList.size(); i++) {
            ResultItems item = new ResultItems();
            item.setId(projectList.get(i).getProjectId());
            item.setText(projectList.get(i).getProjectName());
            List<ResultItems> list = new ArrayList<>();
            if(i == index){
                List<ResultItems> children = new ArrayList<>();
                for (int k = 0; k < itemList.size(); k++) {
                    if(projectList.get(i).getProjectId().equals(itemList.get(k).getProjectId())){
                        ResultItems relustItem = new ResultItems();
                        relustItem.setId(itemList.get(k).getItemId());
                        relustItem.setText(itemList.get(k).getItemName());
                        relustItem.setItemId(itemList.get(k).getItemId());
                        children.add(relustItem);
                    }
                }
                item.setChildren(children);
            }
            items.add(item);
        }

        return Result.success(items);
    }

    //  查看详情
    @GetMapping
    @RequestMapping("/details")
    public Result<?> details(@RequestParam(defaultValue = "") String itemId){
        LambdaQueryWrapper<Item> wrapper = Wrappers.<Item>lambdaQuery();
        wrapper.eq(Item::getItemId,itemId);
        return Result.success(itemMapper.selectOne(wrapper));
    }
}
