package com.jarias14.tekstratego.service.admin.cache.rest;

import lombok.Data;

import java.util.Set;

/**
 * Created by jarias14 on 4/13/2015.
 */
@Data
public class CacheResource {
    private String name;
    private Set<CacheEntryResource> entries;

}
