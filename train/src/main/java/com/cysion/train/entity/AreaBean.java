package com.cysion.train.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 地域对象，包括省，市，县，地点选择用
 */
public class AreaBean implements Serializable {
    private String id;
    private String name;
    private String province;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String aProvince) {
        province = aProvince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String aCity) {
        city = aCity;
    }

    public String getId() {
        return id;
    }

    public void setId(String aId) {
        id = aId;
    }

    public static class City implements Serializable {
        private Map<String, List<AreaBean>> city;

        public Map<String, List<AreaBean>> getCity() {
            return city;
        }

        public void setCity(Map<String, List<AreaBean>> aCity) {
            city = aCity;
        }
    }

    public static class County implements Serializable {
        private Map<String, List<AreaBean>> county;

        public Map<String, List<AreaBean>> getCity() {
            return county;
        }

        public void setCity(Map<String, List<AreaBean>> aCity) {
            county = aCity;
        }
    }
}
