package com.len.service.impl;

import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.ProPermission;
import com.len.mapper.ProPermissionMapper;
import com.len.service.ProPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ProPermissionServiceImpl extends BaseServiceImpl<ProPermission, String> implements ProPermissionService{
    @Autowired
    private ProPermissionMapper proPermissionMapper;

    @Override
    public BaseMapper<ProPermission, String> getMappser() {
        return proPermissionMapper;
    }

    public int insertNew(ProPermission proPermission){
        return proPermissionMapper.insertNew(proPermission);
    }

    public int selectNotInNum(ProPermission proPermission){
        return proPermissionMapper.selectNotInNum(proPermission);
    }

    public List<ProPermission> selectByPmId(String pmId){
        return proPermissionMapper.selectByPmId(pmId);
    }

    public List<ProPermission> selectByPmIdPName(ProPermission proPermission){
        return proPermissionMapper.selectByPmIdPName(proPermission);
    }

    public int deleteDataByProId(String proId){
        return proPermissionMapper.deleteDataByProId(proId);
    }

    public ProPermission selectUserInfoByPidUid(ProPermission proPermission){
        return proPermissionMapper.selectUserInfoByPidUid(proPermission);
    }

    public int updateUserInfoByPidUid(ProPermission proPermission){
        return proPermissionMapper.updateUserInfoByPidUid(proPermission);
    }

    public int updPerm(ProPermission proPermission){
        return proPermissionMapper.updPerm(proPermission);
    }

    public int deleteById(String id){
        return proPermissionMapper.deleteById(id);
    }
}
