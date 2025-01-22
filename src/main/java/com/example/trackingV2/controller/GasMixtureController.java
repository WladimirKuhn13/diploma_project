package com.example.trackingV2.controller;

import com.example.trackingV2.enums.StatusGasMixture;
import com.example.trackingV2.model.GasMixture;
import com.example.trackingV2.model.User;
import com.example.trackingV2.service.GasMixtureService;
import com.example.trackingV2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class GasMixtureController {

    private final GasMixtureService gasMixtureService;

    private final UserService userService;

    public GasMixtureController(GasMixtureService gasMixtureService, UserService userService) {
        this.gasMixtureService = gasMixtureService;
        this.userService = userService;
    }

    @GetMapping("/stock-gas-mixtures")
    @PreAuthorize("hasRole('STOCK') or hasRole('STOCK_HEAD') or hasRole('MANAGER') or hasRole('MANAGER_HEAD')")
    public String findAll(Model model) {
        List<GasMixture> gasMixtures = gasMixtureService.findAll();
        model.addAttribute("mixtures", gasMixtures);
        return "stock-gas-mixtures";
    }

    @GetMapping("/stock-gas-mixtures/search")
    @PreAuthorize("hasRole('STOCK') or hasRole('STOCK_HEAD') or hasRole('MANAGER') or hasRole('MANAGER_HEAD')")
    public String searchGasMixtureInStockForm(Model model) {
        model.addAttribute("mixture", new GasMixture());
        return "search-in-stock";
    }

    @PostMapping("/stock-gas-mixtures/search-result")
    @PreAuthorize("hasRole('STOCK') or hasRole('STOCK_HEAD') or hasRole('MANAGER') or hasRole('MANAGER_HEAD')")
    public String searchGasMixtureInStock(@ModelAttribute @Valid GasMixture gasMixture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Вы ввели некорректные данные!");
            return "error-page";
        }

        try {
            GasMixture searchResult = gasMixtureService.searchGasMixturesInStock
                    (gasMixture.getNameGasMixture(), gasMixture.getGasСylinderVolume(), gasMixture.getNumberOfGasCylinder());
            if (searchResult != null) {
                model.addAttribute("mixture", searchResult);
                model.addAttribute("messageAboutResult", "Газовая смесь есть на складе");
            } else {
                model.addAttribute("messageAboutResult", "Нет подходящих позиций на складе");
            }
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка при обращении к базе данных");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "search-result";
    }

    @GetMapping("/create-gas-mixture")
    @PreAuthorize("hasRole('FILLING') or hasRole('FILLING_HEAD')")
    public String createGasMixtureForm(Model model) {
        model.addAttribute("mixture", new GasMixture());
        return "create-gas-mixture";
    }

    @PostMapping("/create-gas-mixture")
    @PreAuthorize("hasRole('FILLING') or hasRole('FILLING_HEAD')")
    public String createGasMixture(@ModelAttribute @Valid GasMixture gasMixture, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Вы ввели некорректные данные!");
            return "error-page";
        }

        try {
            User currentUser = userService.getCurrentUser();
            String responsibleUserName = userService.getLastNameAndFirstLetterOfName(currentUser);
            Long responsibleUserId = currentUser.getId();

            GasMixture createdGasMixture = gasMixtureService.createGasMixture(
                    gasMixture.getNameGasMixture(),
                    gasMixture.getGasСylinderVolume(),
                    gasMixture.getNumberOfGasCylinder(),
                    responsibleUserName, responsibleUserId,
                    responsibleUserId);
            gasMixtureService.saveGasMixture(createdGasMixture);
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка при сохранении газовой смеси");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "redirect:/orders";
    }

    @PostMapping("/update-gas-mixture-status/{id}")
    public String changeOfStatusByUser(@PathVariable("id") Long id,
                                       @RequestParam String status,
                                       @RequestParam Long responsibleUserId,
                                       Model model) {

        try {
            GasMixture changedGasMixture = gasMixtureService.findById(id);

            if (changedGasMixture == null) {
                model.addAttribute("errorMessage", "Газовая смесь не найдена");
                return "error-page";
            }

            gasMixtureService.changeStatus(changedGasMixture, status);
            changedGasMixture.setResponsibleUserId(responsibleUserId);
            changedGasMixture.setResponsibleUserName(userService.getLastNameAndFirstLetterOfName(userService.findById(responsibleUserId)));

            GasMixture gasMixtureInStock = gasMixtureService.findAllByNameGasMixture(changedGasMixture.getNameGasMixture())
                    .stream()
                    .filter(mixture -> mixture.getStatus().equals(StatusGasMixture.ACCEPTED_TO_STOCK.getStatus()))
                    .findFirst().orElse(null);

            if (status.equals(StatusGasMixture.ACCEPTED_TO_STOCK.getStatus()) && gasMixtureInStock != null && !Objects.equals(gasMixtureInStock.getId(), changedGasMixture.getId())) {
                gasMixtureService.addCylindersToGasMixture(gasMixtureInStock.getId(), gasMixtureInStock.getNumberOfGasCylinder());
                gasMixtureService.saveGasMixture(gasMixtureInStock);
                gasMixtureService.deleteById(id);
            } else {
                gasMixtureService.saveGasMixture(changedGasMixture);
            }
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "redirect:/orders";
    }
}

