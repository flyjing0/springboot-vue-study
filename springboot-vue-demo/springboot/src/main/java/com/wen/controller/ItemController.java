package com.wen.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.common.Result;
import com.wen.entity.Item;
import com.wen.entity.Project;
import com.wen.mapper.ItemMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Resource
    ItemMapper itemMapper;

//    添加用户的
    @PostMapping
    public Result<?> save(@RequestBody Item item){
        item.setItemId(UUID.randomUUID().toString().replaceAll("-", ""));
        itemMapper.insert(item);
        return Result.success();
    }
//    更新
    @PostMapping(value = "/update")
    public Result<?> update(@RequestBody Item Item){
        itemMapper.updateById(Item);
        return Result.success();
    }
    //    s删除
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        itemMapper.deleteById(id);
        return Result.success();
    }
//    查询
    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam(defaultValue = "") String projectId){
        LambdaQueryWrapper<Item> wrapper = Wrappers.<Item>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Item::getItemName,search);
        }
        if (StrUtil.isNotBlank(projectId)){
            wrapper.eq(Item::getProjectId,projectId);
        }
        Page<Item> ItemPage = itemMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(ItemPage);
    }
}
