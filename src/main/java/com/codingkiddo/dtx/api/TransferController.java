package com.codingkiddo.dtx.api;

import com.codingkiddo.dtx.service.TransferService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class TransferController {

	private final TransferService service;

	public TransferController(TransferService service) {
		this.service = service;
	}

	@PostMapping("/transfer")
	public ResponseEntity<?> transfer(@RequestBody TransferRequest req) {
		service.transfer(req.from(), req.to(), req.amount());
		return ResponseEntity.ok(Map.of("status", "ok"));
	}

	public record TransferRequest(@NotBlank String from, @NotBlank String to, @Positive BigDecimal amount) {
	}
}
