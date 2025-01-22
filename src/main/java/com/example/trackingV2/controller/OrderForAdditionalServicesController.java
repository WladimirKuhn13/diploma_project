package com.example.trackingV2.controller;

import com.example.trackingV2.enums.StatusAdditionalServices;
import com.example.trackingV2.enums.StatusGasOrder;
import com.example.trackingV2.model.GasOrder;
import com.example.trackingV2.model.OrderForAdditionalServices;
import com.example.trackingV2.service.GasOrderService;
import com.example.trackingV2.service.OrderForAdditionalServicesService;
import com.example.trackingV2.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderForAdditionalServicesController {

    private final GasOrderService gasOrderService;
    private final UserService userService;
    private final SimpMessagingTemplate messagingTemplate;
    private final OrderForAdditionalServicesService orderForAdditionalServicesService;

    public OrderForAdditionalServicesController(GasOrderService gasOrderService
            , UserService userService, SimpMessagingTemplate messagingTemplate
            , OrderForAdditionalServicesService orderForAdditionalServicesService) {

        this.gasOrderService = gasOrderService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
        this.orderForAdditionalServicesService = orderForAdditionalServicesService;
    }


    @PostMapping("/update-order-status-for-additional-services/{id}")
    public String changeOfStatusByUser(@PathVariable("id") Long id,
                                       @RequestParam String status,
                                       @RequestParam Long responsibleUserId,
                                       @RequestParam Long gasOrderId,
                                       Model model) {
        try {
            OrderForAdditionalServices changedOrderForAdditionalServices = orderForAdditionalServicesService.findById(id);
            GasOrder gasOrder = gasOrderService.findById(gasOrderId);

            if(changedOrderForAdditionalServices == null) {
                model.addAttribute("errorMessage", "Заказ на дополнительные услуги не найден");
                return "error-page";
            }

            if(gasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }
            if(status.equals("Услуги оплачены")) {
                gasOrderService.changeStatus(gasOrder, StatusGasOrder.ADDITIONAL_SERVICES_PAID.getStatus());
                gasOrderService.changeStatus(gasOrder,StatusGasOrder.TRANSFERRED_TO_PREPARATION_AREA.getStatus());
            } else {
                gasOrderService.changeStatus(gasOrder, status);
            }
            gasOrderService.changeResponsibleUserId(gasOrder, responsibleUserId);
            orderForAdditionalServicesService.changeStatus(changedOrderForAdditionalServices, status);
            orderForAdditionalServicesService.changeStatus(changedOrderForAdditionalServices, status);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка при обновлении статуса заказа");
            return "error-page";
        }

        return "redirect:/orders";
    }

    @GetMapping("/create-order-for-additional-services")
    @PreAuthorize("hasRole('STOCK_HEAD') or hasRole('STOCK')")
    public String createdOrderForAdditionalServicesForm(Model model) {
        model.addAttribute("allType", orderForAdditionalServicesService.getAllTypeOfAdditionalServicesAsStrings());
        model.addAttribute("order", new OrderForAdditionalServices());
        return "create-order-for-additional-services";
    }

    @PostMapping("/create-order-for-additional-services")
    @PreAuthorize("hasRole('STOCK_HEAD') or hasRole('STOCK')")
    public String createdOrderForAdditionalServices(@ModelAttribute @Valid OrderForAdditionalServices order,
                                                    HttpSession session,
                                                    BindingResult result,
                                                    Model model) {

        if(result.hasErrors()) {
            model.addAttribute("errorMessage", "Вы ввели некорректные данные");
            return "error-page";
        }

        try{
            Long idCurrentUser = userService.getCurrentUser().getId();
            Long orderId = (Long) session.getAttribute("orderId");
            GasOrder currentGasOrder = gasOrderService.findById(orderId);

            if (currentGasOrder == null) {
                model.addAttribute("errorMessage", "Заказ не найден");
                return "error-page";
            }

            Long managerId = (Long) session.getAttribute("managerId");
            OrderForAdditionalServices createdOrder = orderForAdditionalServicesService.createOrder(managerId, idCurrentUser
                    , orderId, order.getTypeService(), order.getNumberOfServices());

            orderForAdditionalServicesService.changeStatus(createdOrder, StatusAdditionalServices.TRANSFERRED_TO_MANAGER.getStatus());
            gasOrderService.changeStatus(currentGasOrder, StatusGasOrder.TRANSFERRED_TO_MANAGER.getStatus());

            gasOrderService.saveOrder(currentGasOrder);
            orderForAdditionalServicesService.saveOrder(createdOrder);

            messagingTemplate.convertAndSend("/topic/notifications", "Поступила новая заявка на дополнительные услуги! ID заявки: "
                    + createdOrder.getId());
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка при создании заказа на доп. услуги");
            return "error-page";
        }

        return "redirect:/resend-form";
    }

    @GetMapping("/resend-form")
    @PreAuthorize("hasRole('STOCK_HEAD') or hasRole('STOCK')")
    public String resendForm() {
        return "resend-form";
    }

}
