package com.ybn.urban.service.impl;

import com.ybn.common.collection.Group;
import com.ybn.common.collection.TicketUser;
import com.ybn.common.dto.GroupDto;
import com.ybn.common.repository.custom.GroupRepository;
import com.ybn.urban.rest.exception.ExceptionKeyCode;
import com.ybn.urban.rest.exception.TicketException;
import com.ybn.urban.rest.technical.RestPage;
import com.ybn.urban.service.IGroupService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class GroupServiceImpl implements IGroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public void create(GroupDto groupDto) {
        Optional<Group> op = this.groupRepository.findByName(groupDto.getName());
        if (groupDto.getName() == null | groupDto.getName().equals("")) {
            throw new TicketException(ExceptionKeyCode.E_G_0001);
        }
        if (op.isPresent()) {
            throw new TicketException(ExceptionKeyCode.E_F_0004);
        }
        Group g = new Group();
        g.setName(groupDto.getName());
        g.setDescription(groupDto.getDescription());
        this.groupRepository.save(g);
    }

    @Override
    public RestPage<Group> findAll(int limit, int offset) {
        Page<Group> page = this.groupRepository.findAll(PageRequest.of(offset, limit));
        return RestPage.from(page);
    }

    @Override
    public Collection<Group> all() {
        return this.groupRepository.findAll();
    }

    @Override
    public void delete(String id) {
        Optional<Group> op = this.groupRepository.findByName(id);
        if (op.isPresent()) {
            this.groupRepository.delete(op.get());
        }
    }


}
