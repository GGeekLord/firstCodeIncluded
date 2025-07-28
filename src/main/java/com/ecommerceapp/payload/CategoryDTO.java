package com.ecommerceapp.payload;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private Long categoryId;
    private String categoryName;
}
