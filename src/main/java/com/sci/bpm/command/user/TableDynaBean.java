package com.sci.bpm.command.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TableDynaBean implements Serializable {

    private List<DynaBean> beans = new ArrayList<DynaBean>();

    public List<DynaBean> getBeans() {
        return beans;
    }

    public void setBeans(List<DynaBean> beans) {
        this.beans = beans;


    }

    public void addDynaBean(DynaBean bean) {
        this.beans.add(bean);
    }
}
