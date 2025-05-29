package com.library.bookmanager.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class IdListRequest {
    private List<Long> ids;

}
