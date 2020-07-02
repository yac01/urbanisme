package com.ybn.urban.service;

import com.ybn.common.collection.Group;
import com.ybn.common.dto.GroupDto;
import com.ybn.urban.rest.technical.RestPage;
import org.bson.types.ObjectId;

import java.util.Collection;

public interface IGroupService {
    void create(GroupDto groupDto);

    RestPage<Group> findAll(int limit, int offset);

    Collection<Group> all();

    void delete(String name);
}
