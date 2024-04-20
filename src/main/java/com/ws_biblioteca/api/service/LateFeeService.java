package com.ws_biblioteca.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws_biblioteca.api.model.LateFee;
import com.ws_biblioteca.api.repository.LateFeeRepository;

@Service
public class LateFeeService {
    
    @Autowired
    private LateFeeRepository lateFeeRepository;

    public List<LateFee> listLateFees() {
        List<LateFee> result = lateFeeRepository.listLateFees();
        return result;
    }

    public String payLateFee(int idMulta) {
        String result = lateFeeRepository.payLateFee(idMulta);
        return result;
    }
}
