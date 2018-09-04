package com.cabaran.cabaranapi.Utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public class JsonFileProperties {

    private String filedir;
    private String filename;

    public String getFiledir() {
        return filedir;
    }

    public void setFiledir(String filedir) {
        this.filedir = filedir;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
