package com.len.mapper;

import com.len.base.BaseMapper;
import com.len.entity.UserLeave;
import java.util.List;

public interface UserLeaveMapper extends BaseMapper<UserLeave,String>{
    int deleteByPrimaryKey(String id);

    int insert(UserLeave record);

    int insertSelective(UserLeave record);

    UserLeave selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserLeave record);

    int updateByPrimaryKey(UserLeave record);

    @Override
    List<UserLeave> selectListByPage(UserLeave userLeave);
}