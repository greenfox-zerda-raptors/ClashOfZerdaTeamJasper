package com.greenfox.jasper.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoltán on 2017.02.02..
 */
public class ResourceListDTO {
    List<ResourceDto> resources = new ArrayList<>();

    public ResourceListDTO(ResourceDto resourceDto){
        resources.add(resourceDto);
    }
    public ResourceListDTO(List<ResourceDto> resources) {
        this.resources = resources;
    }

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }
}
