package org.iclass.board.repository;

import org.iclass.board.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryUploadRepository extends JpaRepository<GalleryEntity, Integer> {

}
