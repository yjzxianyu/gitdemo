package com.demo.im.web;
import java.io.IOException;
import java.util.*;

import com.demo.im.util.ResultUtil;
import com.demo.im.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.demo.im.entity.Area;
import com.demo.im.service.AreaService;

@RestController
@RequestMapping("/superAdmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    /**
     * 获取所有的区域信息
     *
     * @return
     */
    @RequestMapping(value = "/listArea", method = RequestMethod.GET)
    private ResultVo listArea() {
        return ResultUtil.success(areaService.getAreaList());
    }

    /**
     * 通过区域Id获取区域信息
     *
     * @return
     */
    @RequestMapping(value = "/getAreaById", method = RequestMethod.GET)
    private ResultVo getAreaById(Integer areaId) {
        Area area = new Area();
        area = areaService.getAreaById(areaId);
        if (area !=null){
            try {
                return ResultUtil.success(area);
            }catch (Exception e){
                return ResultUtil.error(e.toString());
            }
        } else {
            return ResultUtil.error(areaId);
        }
    }


    /**
     * 增加区域信息
     * @param name
     * @param priority
     * @return
     */
    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    private ResultVo addArea(String name, Integer priority){
        Area area = new Area();
        Date createTime = new Date();// 创建时间
        Date nowTime = new Date();// 最后修改时间
        area.setAreaName(name);
        area.setPriority(priority);
        area.setCreateTime(createTime);
        area.setLastEditTime(nowTime);
        if (name != null) {
            try {
                    return ResultUtil.success(areaService.addArea(area));
                }
             catch (Exception e) {
                return ResultUtil.error(e.toString());
            }
        } else {
            return  ResultUtil.error();
        }
    }


    /**
     * 修改信息
     * @param areaId
     * @param name
     * @param priority
     * @return
     */
    @RequestMapping(value = "/modifyArea", method = RequestMethod.PUT)
    private ResultVo modifyArea(Integer areaId, String name, Integer priority){
        Area area = new Area();
        area.setAreaId(areaId);
        area.setAreaName(name);
        area.setPriority(priority);
        Date now = new Date();
        if (areaId != null) {
            try {
                return ResultUtil.success(areaService.modifyArea(area));
            } catch (Exception e){
                return  ResultUtil.error(e.toString());
           }
        } else {
            return ResultUtil.error();
        }
    }

    /**
     * 根据id删除信息
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/removeArea", method = RequestMethod.DELETE)
    private ResultVo removeArea(Integer areaId) {
        boolean flag = areaService.deleteArea(areaId);
        final boolean existed = areaId != null && flag == true;
        if (existed) {
            try {
                return ResultUtil.success(flag);
            } catch (Exception e) {
                return ResultUtil.error(e.toString());
            }
        } else {
            return ResultUtil.error(areaId);
        }
    }
}
