package com.greenfox.jasper.DTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zoltán on 2017.02.02..
 */
public class ResourceResponse {
    List<ResourceDto> resources = new ArrayList<>();

    public ResourceResponse(ResourceDto resourceDto){
        resources.add(resourceDto);
    }
    public ResourceResponse(List<ResourceDto> resources) {
        this.resources = resources;
    }

    public List<ResourceDto> getResources() {
        return resources;
    }

    public void setResources(List<ResourceDto> resources) {
        this.resources = resources;
    }
}
