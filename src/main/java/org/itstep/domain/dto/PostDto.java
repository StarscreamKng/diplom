package org.itstep.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.itstep.domain.entity.Post;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private Integer authorId;

    private String title;

    private String text;

    private Integer categoryId;

    private List<Integer> tagsId;

    private boolean wideImage = false;
}
