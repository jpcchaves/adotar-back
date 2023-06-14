package com.jpcchaves.adotar.utils.global;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalUtils {
    public <T, D> ApiResponsePaginatedDto<D> buildApiResponsePaginated(Page<T> page,
                                                                       List<D> content) {
        ApiResponsePaginatedDto<D> response = new ApiResponsePaginatedDto<>();
        response.setContent(content);
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());
        return response;
    }
}
