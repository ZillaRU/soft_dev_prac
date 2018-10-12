package com.len.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog_category")
public class BlogCategory {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name="sequence")
    private Byte sequence;

    /**
     * 搜索code
     */
    private String code;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 上层id(目前最多两次层)
     */
    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public Byte getSequence() {
        return sequence;
    }

    public void setSequence(Byte sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取搜索code
     *
     * @return code - 搜索code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置搜索code
     *
     * @param code 搜索code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取类别名称
     *
     * @return name - 类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类别名称
     *
     * @param name 类别名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取上层id(目前最多两次层)
     *
     * @return parent_id - 上层id(目前最多两次层)
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上层id(目前最多两次层)
     *
     * @param parentId 上层id(目前最多两次层)
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * @return update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}