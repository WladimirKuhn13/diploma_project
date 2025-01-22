package com.example.trackingV2.controller;

import com.example.trackingV2.model.GasOrderArchive;
import com.example.trackingV2.service.GasOrderArchiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class GasOrderArchiveController {

    private final GasOrderArchiveService gasOrderArchiveService;

    public GasOrderArchiveController(GasOrderArchiveService gasOrderArchiveService) {
        this.gasOrderArchiveService = gasOrderArchiveService;
    }

    @GetMapping("/archive")
    public String getAllOrdersInArchive(Model model) {
        model.addAttribute("orders", gasOrderArchiveService.findAll());
        return "archive";
    }

    @GetMapping("/archive-filter")
    public String getAllOrdersInArchiveFiltered(@RequestParam String filterField,
                                                @RequestParam String filterValue, Model model){
        List<GasOrderArchive> archiveOrdersFiltered = gasOrderArchiveService.findByFilter(filterField, filterValue);

        try{
            if (archiveOrdersFiltered.isEmpty()){
                model.addAttribute("errorMessage", "Результаты, удовлетворяющие запросу, не найдены");
                return "error-page";
            } else {
                model.addAttribute("orders", gasOrderArchiveService.findByFilter(filterField, filterValue));
            }
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Неверно введены данные" + e.getMessage());
            return "error-page";
        }

        return "archive-filter";
    }
}
