package com.smallworld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Issue {
    private Integer issueId;
    private Boolean issueSolved;
    private String issueMessage;
}
