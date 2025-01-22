package com.example.trackingV2.controller;

import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.GasOrderArchive;
import com.example.trackingV2.model.HistoryOfChanges;
import com.example.trackingV2.service.HistoryOfChangesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryOfChangesController {

    private final HistoryOfChangesService historyOfChangesService;

    public HistoryOfChangesController(HistoryOfChangesService historyOfChangesService) {
        this.historyOfChangesService = historyOfChangesService;
    }

    @GetMapping("/history")
    public String getAllHistory(Model model) {
        model.addAttribute("allHistory", historyOfChangesService.getAllHistoryOfChanges());
        return "history-of-changes";
    }

    @GetMapping("/history-filter")
    public String getAllHistoryFiltered(@RequestParam String filterField,
                                          @RequestParam String filterValue, Model model){
        List<HistoryOfChanges> historyFiltered = historyOfChangesService.findByFilter(filterField, filterValue);


        try{
            if (historyFiltered.isEmpty()){
                model.addAttribute("errorMessage", "Результаты, удовлетворяющие запросу, не найдены");
                return "error-page";
            } else {
                model.addAttribute("historyOfChanges", historyOfChangesService.findByFilter(filterField, filterValue));
            }
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Неверно введены данные" + e.getMessage());
            return "error-page";
        }

        return "history-filter";
    }
}
