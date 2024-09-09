package com.home.mydiary.controller;

import com.home.mydiary.domain.Diary;
import com.home.mydiary.dto.DiaryDTO;
import com.home.mydiary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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

    @PostMapping("/create")
    public String create(@ModelAttribute("MyDiary") DiaryDTO diaryDTO,
                         @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                         RedirectAttributes redirectAttributes) {
        diaryDTO.setDate(date);
        diaryService.addDiary(diaryDTO);
        redirectAttributes.addFlashAttribute("message", "Diary entry created successfully!");
        return "redirect:/MyDiary/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        DiaryDTO diaryDTO = new DiaryDTO();
        model.addAttribute("diaryDTO", diaryDTO);
        return "MyDiary/create";
    }

    @GetMapping("/read")
    public String showReadForm(@RequestParam("id") long id, Model model) {
        DiaryDTO diaryDTO = diaryService.getDiaryById(id);
        model.addAttribute("diary", diaryDTO);
        return "MyDiary/read";
    }

    @PostMapping("/update")
    public String update(@Valid DiaryDTO diaryDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         Model model) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(
                    "errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", diaryDTO.getId());
            return "redirect:/MyDiary/update";
        }
        diaryService.updateDiary(diaryDTO);

        redirectAttributes.addFlashAttribute("result", diaryDTO.getId());
        redirectAttributes.addFlashAttribute("resultType","update");
        return "redirect:/MyDiary/list";
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") long id, Model model) {
        DiaryDTO diaryDTO = diaryService.getDiaryById(id);
        model.addAttribute("diary", diaryDTO);
        return "MyDiary/update";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.ok().build();
    }
}
