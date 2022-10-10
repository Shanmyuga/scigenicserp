package com.sci.bpm.command.user;

import java.io.Serializable;
import java.util.List;

public class DynaBean implements Serializable {
    private String title;
    private List<String> value;

    public DynaBean(String title, List<String> value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getValue() {
        return value;
    }
}