package com.iyetoo.mpm.lous.application.demo;

import java.io.Serializable;
import java.util.List;

/**
 * @author JacceYang
 *
 * @since 1.0.0 Initialized in 5:21 PM 2019/8/28
 **/
public class Person implements Serializable{

    private Integer id;

    private String name;

    private List<Integer> privilegeIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(List<Integer> privilegeIds) {
        this.privilegeIds = privilegeIds;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", privilegeIds=" + privilegeIds +
                '}';
    }
}
