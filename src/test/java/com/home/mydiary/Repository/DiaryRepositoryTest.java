package com.home.mydiary.Repository;

import com.home.mydiary.domain.Diary;
import com.home.mydiary.repository.DiaryRepository;
import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class DiaryRepositoryTest {
    @Autowired
    DiaryRepository diaryRepository;

    @Test
    public void testAddDiary() {
        IntStream.rangeClosed(1,10).forEach(i ->
        {
            Diary diary = Diary.builder()
                    .id((long)i)
                    .title("오늘의 일기"+i)
                    .content("오늘의 일기내용"+i)
                    .date(LocalDate.now())
                    .build();

            diaryRepository.save(diary);

        });
    }

}
