package com.smallworld.service;

import java.util.List;
import java.util.Set;

public interface IssueService {
    List<String> getAllSolvedIssueMessages();
    Set<Integer> getUnsolvedIssueIds();
}
