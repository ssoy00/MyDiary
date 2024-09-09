package com.home.mydiary.service;

import com.home.mydiary.dto.DiaryDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class DiaryServiceTest {

    @Autowired
    DiaryService diaryService;

    @Test
    public void testReadDiary() {
        DiaryDTO diaryDTO = diaryService.getDiaryById(1L);
        log.info("diaryDTO : "+diaryDTO);
    }

    @Test
    public void testupdateDiary() {
        DiaryDTO diaryDTO = DiaryDTO.builder()
                .id(1L)
                .title("수정")
                .content("수정11")
                .build();
        diaryService.updateDiary(diaryDTO);
    }
}
