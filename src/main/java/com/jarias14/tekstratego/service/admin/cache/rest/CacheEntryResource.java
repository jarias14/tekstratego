package com.jarias14.tekstratego.service.admin.cache.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by jarias14 on 4/13/2015.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CacheEntryResource {
    private Object key;
    private Object value;
}
