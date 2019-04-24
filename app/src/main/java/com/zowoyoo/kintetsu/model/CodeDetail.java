package com.zowoyoo.kintetsu.model;

public class CodeDetail {

    private String code;
    private String parent_cust_id;
    private String id;
    private String name;
    private String agent;


    public CodeDetail() {
    }

    public CodeDetail(String code, String parent_cust_id, String id, String name, String agent) {
        this.code = code;
        this.parent_cust_id = parent_cust_id;
        this.id = id;
        this.name = name;
        this.agent = agent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent_cust_id() {
        return parent_cust_id;
    }

    public void setParent_cust_id(String parent_cust_id) {
        this.parent_cust_id = parent_cust_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
