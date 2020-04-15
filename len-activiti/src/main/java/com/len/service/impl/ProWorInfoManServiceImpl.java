package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProWorInfoMan;
import com.len.mapper.ProWorInfoManMapper;
import com.len.service.ProWorInfoManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProWorInfoManServiceImpl extends BaseServiceImpl<ProWorInfoMan, String> implements ProWorInfoManService {

    @Autowired
    private ProWorInfoManMapper proWorInfoManMapper;

    @Override
    public BaseMapper<ProWorInfoMan, String> getMappser() { return proWorInfoManMapper; }

    public List<ProWorInfoMan> selectByPmId(String pro_id) {
        return proWorInfoManMapper.selectByPmId(pro_id);
    }

    public List<ProWorInfoMan> selectByProName(ProWorInfoMan worInfo) {
        return proWorInfoManMapper.selectByProName(worInfo);
    }

    public List<ProWorInfoMan> selectByProId(String proId) {
        return proWorInfoManMapper.selectByProId(proId);
    }

    public int delById(String id){
        return proWorInfoManMapper.delById(id);
    }

    public int selectRoleNum(ProWorInfoMan worInfo){return proWorInfoManMapper.selectRoleNum(worInfo);}

    public int insertProWor(ProWorInfoMan worInfo){
        return proWorInfoManMapper.insertProWor(worInfo);
    }

    // 查找某一项目的某一角色的所有用户
    public List<ProWorInfoMan> selectUserByRoleName(ProWorInfoMan worInfo){
        return proWorInfoManMapper.selectUserByRoleName(worInfo);
    }

    // 更新项目人员
    public int updateRoleById(ProWorInfoMan worInfo){
        return proWorInfoManMapper.updateRoleById(worInfo);}

}
