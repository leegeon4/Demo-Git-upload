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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //upload 파일 여러개 가져와서 저장하기 : 파일명 여러개를 , 로 구분. 나열한 값을 db에 저장
    public void uploadManyFile(GalleryDTO dto) throws IOException {
        List<MultipartFile> files = dto.getFileS();
        StringBuilder fnBuilder = new StringBuilder();
        for(MultipartFile file : files){
            if(file.getSize() !=0){
                //서버디렉토리에 저장은 java.io.File 객체를 생성합니다.
                File pathFile = new File(path + "\\" + file.getOriginalFilename());
                file.transferTo(pathFile);
                //db에 저장할 파일명 저장.
                fnBuilder.append(file.getOriginalFilename()).append(",");
            }
            //db 테이블에 저장될 값 확인
            dto.setFileNames(fnBuilder.toString());
            log.info("dto:{}",dto);
            uploadRepository.save(dto.toEntity());
        }

    }

    public List<GalleryDTO> list() {
        List<GalleryEntity> entities = uploadRepository.findAll();
        return entities.stream().map(GalleryDTO::of)
                .collect(Collectors.toList());
    }
}
