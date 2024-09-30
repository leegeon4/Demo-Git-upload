package org.iclass.board.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.GalleryDTO;
import org.iclass.board.repository.GalleryUploadRepository;
import org.iclass.board.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
@AllArgsConstructor
public class GalleryUploadController {

    private final GalleryUploadService uploadService;


    @GetMapping("/gallery")
    public String gallery(Model model) {
        GalleryDTO dto = uploadService.one(1);
        model.addAttribute("dto", dto);
        return "gallery";
    }

    @PostMapping("/gallery")
    public String upload(GalleryDTO dto) throws IOException {
        MultipartFile file = dto.getFile();
        log.info("파일명: {}", file.getOriginalFilename());
        log.info("파일크기: {}", file.getSize());

        uploadService.uploadGallery(dto);

        // 서버 디렉토리 위치 c:\\upload
        String path = "c:\\upload";

        return "redirect:/gallery";
    }

}
