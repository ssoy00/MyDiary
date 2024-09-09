package com.home.mydiary.service;

import com.home.mydiary.domain.Diary;
import com.home.mydiary.dto.DiaryDTO;
import com.home.mydiary.repository.DiaryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final ModelMapper modelMapper;

    @Override
    public DiaryDTO addDiary(DiaryDTO diaryDTO) {
        Diary diary = Diary.builder()
                .id(diaryDTO.getId())
                .title(diaryDTO.getTitle())
                .content(diaryDTO.getContent())
                .date(diaryDTO.getDate())
                .build();

        Diary adddiary = diaryRepository.save(diary);
        return entityToDTO(adddiary);
    }

    @Override
    public List<DiaryDTO> getAllDiary() {
        List<Diary> diaryList = diaryRepository.findAll();
        return diaryList.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DiaryDTO getDiaryById(Long id) {
        Optional<Diary> diaryID = diaryRepository.findById(id);
        Diary diary = diaryID.orElseThrow();
        return modelMapper.map(diary, DiaryDTO.class);
    }

    @Override
    public void updateDiary(DiaryDTO diaryDTO) {
        Optional<Diary> diaryID = diaryRepository.findById(diaryDTO.getId());
        Diary diary = diaryID.orElseThrow();
        diary.setTitle(diaryDTO.getTitle());
        diary.setContent(diaryDTO.getContent());
        diaryRepository.save(diary);
    }

    @Override
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }
}
