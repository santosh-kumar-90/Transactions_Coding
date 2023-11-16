package com.smallworld.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    private Long issueId;
    private Boolean issueSolved;
    private String issueMessage;
}
