package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.entity.GalleryEntity;
import org.iclass.board.repository.GalleryUploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class GalleryUploadService {

    private final GalleryUploadRepository uploadRepository;


    public void uploadGallery(GalleryDTO dto) throws IOException {

        MultipartFile file = dto.getFile();

        String path = "c:\\upload";

        if(file.getSize() !=0) {
            // 서버디렉토리에 저장은 java.io.File 객체를 생성합니다.
            File pathFile = new File(path + "\\" + file.getOriginalFilename());
            // 파일 전송 MultiFile 객체를 파일시스템으로 저장(전송)
            file.transferTo(pathFile);
            // db에 저장할 파일명 저장.
            dto.setFileNames(file.getOriginalFilename());

            // db테이블에 저장될 값 확인
            log.info("dto: {}", dto);
            uploadRepository.save(dto.toEntity());

        }
    }
    // 람다식 안에서 리턴 받을 변수는 전역변수만 가능
    // 람다식은 함수형 인터페이스는 추상메소드가 1개인 익명클래스를 구현한 것

    GalleryDTO dto = null;
    public GalleryDTO one(int i) {

        Optional<GalleryEntity>  optional
                = uploadRepository.findById(i);
        optional.ifPresent( o -> {
            GalleryEntity entity = optional.get();
            dto = GalleryDTO.of(entity);
        });
        log.info("dto: {}", dto);
        return dto;
    }
}
