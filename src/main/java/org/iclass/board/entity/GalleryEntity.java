package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Builder
@Data       // 불변객체 관련된 메소드 재정의
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class GalleryEntity {
// JPA 로 테이블을 만들기 위한 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    @Column(nullable = false)
    private String title;
    private String fileNames;
    private String writer;

    @CreatedDate
    private LocalDateTime createdAt;

}
/*
IDX NUMBER(5) primary key,
TITLE VARCHAR2(40) not null,
createdAt DATE,
FILENAMES VARCHAR2(200),
WRITER VARCHAR2(50)
*/