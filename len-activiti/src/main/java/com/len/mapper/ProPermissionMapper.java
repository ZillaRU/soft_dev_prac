package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.ProPermission;

import java.util.List;

public interface ProPermissionMapper extends BaseMapper<ProPermission, String>{
    int insertNew(ProPermission proPermission);

    int selectNotInNum(ProPermission proPermission);

    int deleteDataByProId(String proId);

    List<ProPermission> selectByPmId(String pmId);

    List<ProPermission> selectByPmIdPName(ProPermission proPermission);

    ProPermission selectUserInfoByPidUid(ProPermission proPermission);

    int updateUserInfoByPidUid(ProPermission proPermission);

    int updPerm(ProPermission proPermission);

    int deleteById(String id);

}
