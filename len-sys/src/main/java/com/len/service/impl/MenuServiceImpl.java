package com.len.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.len.base.BaseMapper;
import com.len.base.impl.BaseServiceImpl;
import com.len.entity.SysMenu;
import com.len.entity.SysRoleMenu;
import com.len.mapper.SysMenuMapper;
import com.len.mapper.SysRoleMenuMapper;
import com.len.service.MenuService;
import com.len.service.SysUserService;
import com.len.util.TreeUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhuxiaomeng
 * @date 2017/12/12.
 * @email 154040976@qq.com
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<SysMenu,String> implements MenuService {

  @Autowired
  private SysMenuMapper menuDao;

  @Autowired
  private SysUserService userService;

  @Autowired
  private SysRoleMenuMapper roleMenuMapper;

  @Override
  public BaseMapper<SysMenu, String> getMappser() {
    return menuDao;
  }

  @Override
  public List<SysMenu> getMenuNotSuper() {
    return menuDao.getMenuNotSuper();
  }

  @Override
  public int insert(SysMenu menu) {
    return menuDao.insert(menu);
  }

  @Override
  public SysMenu selectByPrimaryKey(String id) {
    return menuDao.selectByPrimaryKey(id);
  }

  @Override
  public List<SysMenu> getMenuChildren(String id) {
    return menuDao.getMenuChildren(id);
  }

  @Override
  public JSONArray getMenuJson(String roleId){
    List<SysMenu> mList=menuDao.getMenuNotSuper();
    JSONArray jsonArr=new JSONArray();
    int pNum=1000,num=0;
    for(SysMenu sysMenu:mList){
      SysMenu menu=getChild(sysMenu.getId(),true,pNum,num);
      jsonArr.add(menu);
      pNum+=1000;
    }
    System.out.println(jsonArr);
    return jsonArr;
  }

  @Override
  public JSONArray getMenuJsonList() {
    List<SysMenu> mList=menuDao.getMenuNotSuper();
    JSONArray jsonArr=new JSONArray();
    for(SysMenu sysMenu:mList){
      SysMenu menu=getChild(sysMenu.getId(),false,0,0);
      jsonArr.add(menu);
    }
    System.out.println(jsonArr);
    return jsonArr;
  }

  @Override
  public JSONArray getMenuJsonByUser(List<SysMenu> menuList){
    //List<SysMenu> menuListOne=new ArrayList<>();//获取第一级别
    JSONArray jsonArr=new JSONArray();
    Collections.sort(menuList, new Comparator<SysMenu>() {
      @Override
      public int compare(SysMenu o1, SysMenu o2) {
        if(o1.getOrderNum()==null||o2.getOrderNum()==null){
          return -1;
        }
        if(o1.getOrderNum()>o2.getOrderNum()){
          return 1;
        }
        if(o1.getOrderNum()==o2.getOrderNum()){
          return 0;
        }
        return -1;
      }
    });
    int pNum=1000;
    for(SysMenu menu:menuList){
      if(StringUtils.isEmpty(menu.getPId())){
        SysMenu sysMenu=getChilds(menu,pNum,0,menuList);
        jsonArr.add(sysMenu);
        pNum+=1000;
      }
    }
    return jsonArr;
  }

  public SysMenu getChilds(SysMenu menu,int pNum,int num,List<SysMenu> menuList){
    for(SysMenu menus:menuList){
      if(menu.getId().equals(menus.getPId())&&menus.getMenuType()==0){
        ++num;
        SysMenu m=getChilds(menus,pNum,num,menuList);
        m.setNum(pNum+num);
        menu.addChild(m);
      }
    }
    return menu;

  }
  @Override
  public List<SysMenu> getMenuChildrenAll(String id) {
    return menuDao.getMenuChildrenAll(id);
  }

  /**
   *
   * @param id 父菜单id
   * @param flag true 获取非按钮菜单 false 获取包括按钮在内菜单 用于nemuList展示
   * @param pNum 用户控制侧拉不重复id tab 父循环+1000
   * @param num 用户控制侧拉不重复id tab 最终效果 1001 10002 2001 2002
   * @return
   */
  public SysMenu getChild(String id,boolean flag,int pNum,int num){
    SysMenu sysMenu=menuDao.selectByPrimaryKey(id);
    List<SysMenu> mList=null;
    if(flag){
      mList= menuDao.getMenuChildren(id);
    }else{
      mList=menuDao.getMenuChildrenAll(id);
    }
    for(SysMenu menu:mList){
      ++num;
      SysMenu m=getChild(menu.getId(),flag,pNum,num);
      if(flag)
        m.setNum(pNum+num);
      sysMenu.addChild(m);
    }
    return sysMenu;
  }

  @Override
  public JSONArray getTreeUtil(String roleId){
    TreeUtil treeUtil=null;
    List<SysMenu> mList=menuDao.getMenuNotSuper();
    JSONArray jsonArr=new JSONArray();
    for(SysMenu sysMenu:mList){
      treeUtil=getChildByTree(sysMenu.getId(),false,0,null,roleId);
      jsonArr.add(treeUtil);
    }
    System.out.println(jsonArr);
    return jsonArr;

  }

  @Override
  public List<SysMenu> getUserMenu(String id) {
    return menuDao.getUserMenu(id);
  }

  public TreeUtil getChildByTree(String id,boolean flag,int layer,String pId,String roleId){
    layer++;
    SysMenu sysMenu=menuDao.selectByPrimaryKey(id);
    List<SysMenu> mList=null;
    if(flag){
      mList= menuDao.getMenuChildren(id);
    }else{
      mList=menuDao.getMenuChildrenAll(id);
    }
    TreeUtil treeUtil = new TreeUtil();
    treeUtil.setId(sysMenu.getId());
    treeUtil.setName(sysMenu.getName());
    treeUtil.setLayer(layer);
    treeUtil.setPId(pId);
    /**判断是否存在*/
    if(!StringUtils.isEmpty(roleId)){
      SysRoleMenu sysRoleMenu = new SysRoleMenu();
      sysRoleMenu.setMenuId(sysMenu.getId());
      sysRoleMenu.setRoleId(roleId);
      int count = roleMenuMapper.selectCountByCondition(sysRoleMenu);
      if (count > 0)
        treeUtil.setChecked(true);
    }
    for(SysMenu menu:mList){
      TreeUtil m=getChildByTree(menu.getId(),flag,layer,menu.getId(),roleId);
      treeUtil.getChildren().add(m);
    }
    return treeUtil;
  }
}
