package com.cleanarch.usecase.auth;

import com.cleanarch.usecase.auth.dto.*;
import com.cleanarch.usecase.common.dto.*;

public interface RequestPasswordResetUseCase {

  MessageResponseDTO resetTokenRequest(BasePasswordResetRequestDTO requestDTO);
}
