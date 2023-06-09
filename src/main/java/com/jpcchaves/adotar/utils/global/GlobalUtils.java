package com.jpcchaves.adotar.utils.global;

import com.jpcchaves.adotar.payload.dto.ApiResponsePaginatedDto;
import com.jpcchaves.adotar.utils.colletions.CollectionsUtils;
import com.jpcchaves.adotar.utils.mapper.MapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalUtils {

    private final MapperUtils mapper;
    private final CollectionsUtils collectionsUtils;

    public GlobalUtils(MapperUtils mapper,
                       CollectionsUtils collectionsUtils) {
        this.mapper = mapper;
        this.collectionsUtils = collectionsUtils;
    }

    public <T> ApiResponsePaginatedDto<T> buildApiResponsePaginated(Page<T> page) {
        ApiResponsePaginatedDto<T> response = new ApiResponsePaginatedDto<>();
        response.setContent(page.getContent());
        response.setPageNo(page.getNumber());
        response.setPageSize(page.getSize());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setLast(page.isLast());
        return response;
    }


    public <O, D> Page<D> buildPage(Page<O> pageOrigin,
                                    Pageable pageable,
                                    Class<D> destinationClass) {
        List<D> destinationList = mapper.parseListObjects(pageOrigin.getContent(), destinationClass);
        return new PageImpl<>(destinationList, pageable, destinationList.size());
    }
}
