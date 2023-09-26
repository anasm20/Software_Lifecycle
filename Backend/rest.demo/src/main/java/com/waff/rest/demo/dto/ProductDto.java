package com.waff.rest.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.waff.rest.demo.model.Category;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public class ProductDto {

    private String id = UUID.randomUUID().toString();

    @Size(min=2, max=64)
    private String titel;

    @Size(min=2, max=64)
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MultipartFile image;

    @Pattern(regexp = "(\\d+,\\d{1,2})")
    private String price;

    private String categoryId;


    public ProductDto() {
    }

    public String getId() {
        return id;
    }

    public ProductDto setId(String id) {
        if(StringUtils.isNotBlank(id)) {
            this.id = id;
        }
        return this;
    }

    public String getTitel() {
        return titel;
    }

    public ProductDto setTitel(String titel) {
        this.titel = titel;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getImage() {
        return image;
    }

    public ProductDto setImage(MultipartFile image) {
        this.image = image;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ProductDto setPrice(String price) {
        this.price = price;
        return this;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public ProductDto setCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }
}
