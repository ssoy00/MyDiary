package com.home.mydiary.controller;

import com.home.mydiary.dto.DiaryDTO;
import com.home.mydiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/MyDiary")
@Log4j2
@RequiredArgsConstructor
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/list")
    public String list(Model model) {
        List<DiaryDTO> diaries = diaryService.getAllDiary();
        model.addAttribute("diaries", diaries);
        return "MyDiary/list";
    }
}
