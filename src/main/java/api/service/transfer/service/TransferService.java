package api.service.transfer.service;


import api.service.transfer.entity.Transfer;
import api.service.transfer.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public Transfer createTransfer(Transfer transfer) {
        return transferRepository.save(transfer);
    }

    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransferById(Long id) {
        return transferRepository.findById(id);
    }

    public Transfer updateTransferStatus(Long id, Transfer transferDetails) {
        Optional<Transfer> existingTransfer = transferRepository.findById(id);
        if (existingTransfer.isPresent()) {
            Transfer transfer = existingTransfer.get();
            transfer.setStatus(transferDetails.getStatus());
            return transferRepository.save(transfer);
        }
        return null;
    }
}
