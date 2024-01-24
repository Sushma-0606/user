package com.user.task.request;

import java.io.Serializable;

import org.springframework.data.domain.Sort;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaginationRequestModel implements Serializable {

	
	private static final long serialVersionUID = 1L;

	Integer pageNumber;

	Integer pageSize;

	String sortBy;

	Sort.Direction sortDirection;

}
