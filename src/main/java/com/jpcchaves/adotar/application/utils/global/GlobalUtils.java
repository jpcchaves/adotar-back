package com.jpcchaves.adotar.application.utils.global;

import com.jpcchaves.adotar.application.dto.ApiResponsePaginatedDto;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GlobalUtils {
  public <T, D> ApiResponsePaginatedDto<D> buildApiResponsePaginated(
      Page<T> page, List<D> content) {
    ApiResponsePaginatedDto<D> response = new ApiResponsePaginatedDto<>();
    response.setContent(content);
    response.setPageNo(page.getNumber());
    response.setPageSize(page.getSize());
    response.setTotalElements(page.getTotalElements());
    response.setTotalPages(page.getTotalPages());
    response.setLast(page.isLast());
    return response;
  }

  public boolean isObjectNull(Object object) {
    return Objects.isNull(object);
  }

  public boolean isObjectNotNull(Object object) {
    return Objects.nonNull(object);
  }
}
