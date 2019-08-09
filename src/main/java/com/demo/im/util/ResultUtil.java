package com.demo.im.util;

import com.demo.im.vo.ResultVo;

public class ResultUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("请求成功");
        resultVo.setData(object);
        return resultVo;
    }

    public  static ResultVo success(){
        return null;
    }

    public static ResultVo error(String msg){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg(msg);

        return resultVo;
    }

    public static ResultVo error(){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("请求失败,信息有缺失");
        return resultVo;
    }

    public static ResultVo error(Integer id){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg("不存在id"+id+"所对应的区域");
        resultVo.setData(false);
        return resultVo;
    }

}
