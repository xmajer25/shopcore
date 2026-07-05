package com.xmajer.shopcore.paymentservice.controller;

import com.xmajer.shopcore.paymentservice.data.model.Payment;
import com.xmajer.shopcore.paymentservice.dto.response.PaymentResponse;
import com.xmajer.shopcore.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/payments")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "Attempts to perform the payment action, updating payment status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment succeeded")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<PaymentResponse> performPayment(@PathVariable UUID id){
        PaymentResponse response = paymentService.performPayment(id);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Return all payments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payments fetched ok")
    })
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll(){
        List<PaymentResponse> responses = paymentService.findAll();

        return ResponseEntity.ok(responses);
    }
}
