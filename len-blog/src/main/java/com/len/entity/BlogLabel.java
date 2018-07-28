package com.len.entity;

import javax.persistence.*;

@Table(name = "blog_label")
public class BlogLabel {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 标签code
     */
    @Column(name = "label_code")
    private String labelCode;

    /**
     * 标签name
     */
    @Column(name = "label_name")
    private String labelName;

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

    /**
     * 获取标签code
     *
     * @return label_code - 标签code
     */
    public String getLabelCode() {
        return labelCode;
    }

    /**
     * 设置标签code
     *
     * @param labelCode 标签code
     */
    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode == null ? null : labelCode.trim();
    }

    /**
     * 获取标签name
     *
     * @return label_name - 标签name
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * 设置标签name
     *
     * @param labelName 标签name
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }
}