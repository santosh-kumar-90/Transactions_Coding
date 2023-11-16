package com.smallworld.service.impl;

import com.smallworld.service.IssueService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class IssueServiceImpl implements IssueService {
    @Override
    public List<String> getAllSolvedIssueMessages() {
        return null;
    }

    @Override
    public Set<Integer> getUnsolvedIssueIds() {
        return null;
    }
}
