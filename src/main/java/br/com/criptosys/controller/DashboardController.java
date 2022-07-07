package br.com.criptosys.controller;

import br.com.criptosys.domain.entity.UserDE;
import br.com.criptosys.service.DashboardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    private final DashboardService DashboardService;
    @Autowired
    public DashboardController(DashboardService DashboardService){
        this.DashboardService = DashboardService;
    }

    @Operation(description = "BTC price"
            , summary = "Get BTC summary")
    @GetMapping("/getBtc")
    public ResponseEntity<?> getBtcPrice() throws URISyntaxException, JsonProcessingException {
        return ResponseEntity.ok(this.DashboardService.getBtcPrice());
    }

}
