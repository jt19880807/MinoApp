package com.minoapp.base;

import java.io.Serializable;

/**
 * Created by Devin on 2017/9/12.
 */

public class BaseBean implements Serializable {

    public BaseBean(String name) {
        Name = name;
    }

    protected String Name;
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
