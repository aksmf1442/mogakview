package com.mogakview.domain.tag;

import com.mogakview.domain.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Tag extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Builder
    public Tag(String name) {
        this.name = name;
    }
}
