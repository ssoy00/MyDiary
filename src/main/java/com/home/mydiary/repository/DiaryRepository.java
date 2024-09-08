package com.home.mydiary.repository;

import com.home.mydiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
}
