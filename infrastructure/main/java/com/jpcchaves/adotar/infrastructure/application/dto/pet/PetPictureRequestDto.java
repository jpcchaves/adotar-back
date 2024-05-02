package com.jpcchaves.adotar.infrastructure.application.dto.pet;

import com.jpcchaves.adotar.infrastructure.domain.annotations.base64validator.ValidBase64;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class PetPictureRequestDto {

  @NotNull(message = "O nome é obrigatório")
  @NotBlank(message = "O nome não pode estar em branco")
  private String name;

  @NotNull(message = "O tamanho do arquivo é obrigatório")
  @NotBlank(message = "O tamanho do arquivo não pode estar em branco")
  @Length(
      max = 50,
      message = "O tamanho do arquivo deve ter no máximo 50 caracteres")
  private long size;

  @NotNull(message = "O tipo do arquivo é obrigatório")
  @NotBlank(message = "O tipo do arquivo não pode estar em branco")
  @Length(
      max = 50,
      message = "O tipo do arquivo deve ter no máximo 50 caracteres")
  private String type;

  @NotNull(message = "A URL da imagem é obrigatória")
  @NotBlank(message = "A URL da imagem não pode estar em branco")
  @ValidBase64
  private String imgUrl;

  public PetPictureRequestDto() {}

  public PetPictureRequestDto(
      String name, long size, String type, String imgUrl) {
    this.name = name;
    this.size = size;
    this.type = type;
    this.imgUrl = imgUrl;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
