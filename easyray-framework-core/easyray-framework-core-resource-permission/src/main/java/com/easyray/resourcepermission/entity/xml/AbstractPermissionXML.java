package com.easyray.resourcepermission.entity.xml;

/**
 * @Date: 20-2-3
 * @Author: wyy
 */
public abstract class AbstractPermissionXML {
    private String version;
    private String module;

    @Override
    public String toString() {
        return "AbstractPermissionXML{" +
                "version='" + version + '\'' +
                ", module='" + module + '\'' +
                '}';
    }

    public String getVersion() {
        return version;
    }

    public AbstractPermissionXML setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getModule() {
        return module;
    }

    public AbstractPermissionXML setModule(String module) {
        this.module = module;
        return this;
    }
}
