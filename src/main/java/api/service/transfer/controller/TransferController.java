package api.service.transfer.controller;

import api.service.transfer.entity.Transfer;
import api.service.transfer.service.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @Operation(summary = "Create a new transfer", description = "Creates a new transfer and initiates the transfer process")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Transfer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Transfer> createTransfer(
            @Parameter(description = "Transfer object containing transfer details", required = true)
            @RequestBody Transfer transfer) {
        Transfer createdTransfer = transferService.createTransfer(transfer);
        return ResponseEntity.ok(createdTransfer);
    }

    @Operation(summary = "Get all transfers", description = "Retrieves a list of all transfers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of transfers retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Transfer.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }

    @Operation(summary = "Update transfer status", description = "Updates the status of an existing transfer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer status updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Transfer.class))),
            @ApiResponse(responseCode = "404", description = "Transfer not found",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Transfer> updateTransferStatus(
            @Parameter(description = "ID of the transfer to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Transfer object containing updated transfer details", required = true)
            @RequestBody Transfer transferDetails) {
        Transfer updatedTransfer = transferService.updateTransferStatus(id, transferDetails);
        if (updatedTransfer != null) {
            return ResponseEntity.ok(updatedTransfer);
        }
        return ResponseEntity.notFound().build();
    }
}
