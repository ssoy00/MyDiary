package com.home.mydiary.service;

import com.home.mydiary.domain.Diary;
import com.home.mydiary.dto.DiaryDTO;

import java.util.List;

public interface DiaryService {
    DiaryDTO addDiary(DiaryDTO diaryDTO);
    List<DiaryDTO> getAllDiary();
    DiaryDTO getDiaryById(Long id);
    void updateDiary(DiaryDTO diaryDTO);
    void deleteDiary(Long id);

    default DiaryDTO entityToDTO(Diary diary) {
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .date(diary.getDate())
                .build();
        return diaryDTO;
    }
}
