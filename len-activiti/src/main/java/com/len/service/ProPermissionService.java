package com.len.service;

import com.len.base.BaseService;
import com.len.entity.ProPermission;

import java.util.List;

public interface ProPermissionService extends BaseService<ProPermission, String> {

    int insertNew(ProPermission proPermission);

    int deleteDataByProId(String proId);

    List<ProPermission> selectByPmId(String pmId);

    int selectNotInNum(ProPermission proPermission);

    ProPermission selectUserInfoByPidUid(ProPermission proPermission);

    int updateUserInfoByPidUid(ProPermission proPermission);

    int updPerm(ProPermission proPermission);

    List<ProPermission> selectByPmIdPName(ProPermission proPermission);

    int deleteById(String id);
}
