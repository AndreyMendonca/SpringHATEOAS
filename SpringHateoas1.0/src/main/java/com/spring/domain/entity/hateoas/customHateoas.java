package com.spring.domain.entity.hateoas;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class customHateoas {
	private String rel;
    private String href;
    private String action;
}
