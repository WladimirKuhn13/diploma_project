package com.example.trackingV2.controller;

import com.example.trackingV2.enums.StatusAdditionalServices;
import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.enums.TypeGasOrder;
import com.example.trackingV2.model.*;
import com.example.trackingV2.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GasOrderController {

    private final GasOrderService gasOrderService;

    private final UserService userService;

    private final GasMixtureService gasMixtureService;

    private final SimpMessagingTemplate messagingTemplate;

    private final OrderForAdditionalServicesService orderForAdditionalServicesService;


    public GasOrderController(GasOrderService gasOrderService, UserService userService
            , GasMixtureService gasMixtureService, SimpMessagingTemplate messagingTemplate
            , OrderForAdditionalServicesService orderForAdditionalServicesService) {

        this.gasOrderService = gasOrderService;
        this.userService = userService;
        this.gasMixtureService = gasMixtureService;
        this.messagingTemplate = messagingTemplate;
        this.orderForAdditionalServicesService = orderForAdditionalServicesService;
    }

    @GetMapping("/orders")
    public String findAll(Model model) {
        try{
            List<GasOrder> gasOrders = gasOrderService.findAll();
            List<String> allStatus = gasOrderService.getAllStatusesAsStrings();
            User currentUser = userService.getCurrentUser();
            String currentUserRole = userService.getCurrentUserRole(currentUser);
            List<GasMixture> allGasMixturesInStock = gasMixtureService.findAll();

            if(!currentUserRole.equals("ROLE_MANAGER") && !currentUserRole.equals("ROLE_MANAGER_HEAD")) {
                List<GasOrder> gasOrderForRole = gasOrderService.getGasOrdersForRole(currentUserRole);
                model.addAttribute("gasOrderForRole", gasOrderForRole);
                if (currentUserRole.contains("_HEAD")) {
                    List<User> usersWithSpecificRole = userService.getAllUsersWithSpecificRole(userService.getCurrentUser().getRoles());
                    model.addAttribute("usersWithSpecificRole", usersWithSpecificRole);
                }
            } else {
                List<OrderForAdditionalServices> orderForAdditionalServices = orderForAdditionalServicesService.findAll();
                model.addAttribute("orderForAdditionalServices", orderForAdditionalServices);
            }

            model.addAttribute("orders", gasOrders);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("orderStatus", allStatus);//тестовая фича, добавил 21.12
            model.addAttribute("currentUserRole", currentUserRole);
            model.addAttribute("gasMixtures", allGasMixturesInStock);
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "orders";
    }

    @PostMapping("/update-order-status-to-accepted/{id}")
    @PreAuthorize("hasRole('LAB_HEAD') or hasRole('STOCK_HEAD') or hasRole('FILLING_HEAD') or hasRole('PREPARATION_HEAD')")
    public String changeOfStatusByUserToAccepted(@PathVariable("id") Long id,
                                                 @RequestParam String status,
                                                 @RequestParam Long responsibleUserId,
                                                 Model model) {
        try{
            GasOrder changedGasOrder = gasOrderService.findById(id);

            if(changedGasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }

            gasOrderService.changeStatus(changedGasOrder, status);
            gasOrderService.changeResponsibleUserId(changedGasOrder, responsibleUserId);

        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "redirect:/orders";
    }

    @PostMapping("/update-order-status/{id}")
    public String changeOfStatusByUserToTransferred(@PathVariable("id") Long id,
                                                    @RequestParam String status,
                                                    Model model) {
        try{
            GasOrder changedGasOrder = gasOrderService.findById(id);

            if(changedGasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }

            if (status.equals(StatusGasOrder.SENT_TO_CLIENT.getStatus())){
                gasOrderService.changeStatus(changedGasOrder, status);
                gasOrderService.moveToArchive(id);
            } else {
                gasOrderService.changeStatus(changedGasOrder, status);
                messagingTemplate.convertAndSend("/topic/notifications", "Поступила новая заявка! ID заявки: "
                        + id + "\n" + "Не забудьте назначить ответственного!");
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

    
    @GetMapping("/order-update/{id}")
    @PreAuthorize("hasRole('LAB_HEAD')")
    public String updateGasOrder(@PathVariable("id") Long id, Model model) {
        try{
            GasOrder gasOrder = gasOrderService.findById(id);

            if(gasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }

            model.addAttribute("order", gasOrder);
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "update-order";
    }

    @PostMapping("/order-update/{id}")
    @PreAuthorize("hasRole('LAB_HEAD')")
    public String updateGasOrder(@ModelAttribute GasOrder order, @PathVariable("id") Long id, Model model) {
        try{
            GasOrder existingGasOrder = gasOrderService.findById(id);

            if(existingGasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }

            gasOrderService.saveOrder(order);
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        return "redirect:/orders";
    }

    @GetMapping("/create-gas-order")
    @PreAuthorize("hasRole('MANAGER_HEAD') or hasRole('MANAGER')")
    public String createGasOrderForm(Model model) {
        model.addAttribute("gasOrder", new GasOrder());
        return "create-gas-order";
    }

    @PostMapping("/create-gas-order")
    @PreAuthorize("hasRole('MANAGER_HEAD') or hasRole('MANAGER')")
    public String createGasOrder(@ModelAttribute @Valid GasOrder gasOrder, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errorMessage", "Вы ввели некорректные данные");
            return "error-page";
        }

        User currentUser = userService.getCurrentUser();

        GasOrder createdGasOrder;
        try{
            createdGasOrder = gasOrderService.createGasOrder(currentUser.getId(), gasOrder.getClient(), gasOrder.getGasMixture(),
                    gasOrder.getGasСylinderVolume(), gasOrder.getNumberOfGasCylinder(), gasOrder.getTypeOrder(),
                    userService.getLastNameAndFirstLetterOfName(currentUser), currentUser.getId());
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным при создании заказа");
            return "error-page";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла непредвиденная ошибка");
            return "error-page";
        }

        boolean checkGasMixtureFromGasOrderInStock;
        try{
            checkGasMixtureFromGasOrderInStock = gasMixtureService.checkGasMixtureInStock(
                    createdGasOrder.getGasMixture(), createdGasOrder.getGasСylinderVolume(), createdGasOrder.getNumberOfGasCylinder());
        } catch (DataAccessException e) {
            model.addAttribute("errorMessage", "Ошибка доступа к данным при проверке наличия газовой смеси");
            return "error-page";
        }

        updateGasOrderStatusAfterCreation(createdGasOrder, checkGasMixtureFromGasOrderInStock);

        messagingTemplate.convertAndSend("/topic/notifications", "Поступила новая заявка! ID заявки: "
                + createdGasOrder.getId() + "\n" + "Не забудьте назначить ответственного!");

        return "redirect:/orders";
    }

    private void updateGasOrderStatusAfterCreation(GasOrder createdGasOrder, boolean checkGasMixtureFromGasOrderInStock) {
        if (checkGasMixtureFromGasOrderInStock && createdGasOrder.getTypeOrder().equals(TypeGasOrder.CIRCULATING_CYLINDERS.getTypeGasOrder())) {
            gasOrderService.changeStatus(createdGasOrder, StatusGasOrder.TRANSFERRED_TO_STOCK_FOR_SHIPMENT.getStatus());
            gasMixtureService.removeCylindersFromGasMixture(gasMixtureService.findByNameGasMixture(createdGasOrder.getGasMixture()).getId(), createdGasOrder.getNumberOfGasCylinder());
        } else if (!checkGasMixtureFromGasOrderInStock && createdGasOrder.getTypeOrder().equals(TypeGasOrder.CIRCULATING_CYLINDERS.getTypeGasOrder())) {
            gasOrderService.changeStatus(createdGasOrder, StatusGasOrder.TRANSFERRED_TO_FILLING_AREA.getStatus());
        } else if (createdGasOrder.getTypeOrder().equals(TypeGasOrder.CUSTOMER_CYLINDERS.getTypeGasOrder())) {
            gasOrderService.changeStatus(createdGasOrder, StatusGasOrder.TRANSFERRED_FOR_CYLINDER_ACCEPTANCE.getStatus());
        } else if (createdGasOrder.getTypeOrder().equals(TypeGasOrder.NEW_CYLINDERS.getTypeGasOrder())) {
            gasOrderService.changeStatus(createdGasOrder, StatusGasOrder.TRANSFERRED_TO_PREPARATION_AREA.getStatus());
        }
    }


    @PostMapping("/checking-need-to-accept-cylinders/{id}")
    public String checkingNeedToAcceptCylinders(@PathVariable("id") Long id,
                                                @RequestParam String choice,
                                                @RequestParam Long managerId,
                                                HttpSession session,
                                                Model model) {
        try{
            if("yes".equals(choice)) {
                session.setAttribute("managerId", managerId);
                session.setAttribute("orderId", id);
                return "redirect:/create-order-for-additional-services";
            } else if("no".equals(choice)) {
                GasOrder changedGasOrder = gasOrderService.findById(id);
                if(changedGasOrder == null) {
                    model.addAttribute("errorMessage", "Заказ не найден");
                    return "error-page";
                }
                gasOrderService.changeStatus(changedGasOrder, StatusGasOrder.TRANSFERRED_TO_FILLING_AREA.getStatus());
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка при обработке запроса");
            return "error-page";
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders-filter")
    public String getAllGasOrdersFiltered(@RequestParam String filterField,
                                                @RequestParam String filterValue, Model model){
        List<GasOrder> gasOrdersFiltered = gasOrderService.findByFilter(filterField, filterValue);


        try{
            if (gasOrdersFiltered.isEmpty()){
                model.addAttribute("errorMessage", "Результаты, удовлетворяющие запросу, не найдены");
                return "error-page";
            } else {
                model.addAttribute("orders", gasOrderService.findByFilter(filterField, filterValue));
            }
        } catch (IllegalArgumentException e){
            model.addAttribute("errorMessage", "Неверно введены данные" + e.getMessage());
            return "error-page";
        }

        return "orders-filter";
    }
}
