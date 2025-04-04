package com.bivgroup.resource;

import com.bivgroup.entity.Contract;
import com.bivgroup.entity.Insurer;
import com.bivgroup.exception.HandledServiceException;
import com.bivgroup.pojo.request.GetNotificationsByContractNumberRequest;
import com.bivgroup.repository.ContractRepository;
import com.bivgroup.repository.InsurerRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

@QuarkusTest
public class InputValidatorTest {

    @Mock
    ContractRepository contractRepository;

    @Mock
    InsurerRepository insurerRepository;

    @InjectMocks
    InputValidator inputValidatorWithMocks;

    @Inject
    InputValidator inputValidator;

    @BeforeEach
    public void setup() {
        contractRepository = Mockito.mock(ContractRepository.class);
        insurerRepository = Mockito.mock(InsurerRepository.class);
        Mockito.when(contractRepository.findByContractNumber(Mockito.anyString())).thenReturn(Optional.of(new Contract()));
        Mockito.when(insurerRepository.findByInsurerId(Mockito.anyLong())).thenReturn(Optional.of(new Insurer()));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateGetNotificationsByContractNumberRequestTest() {
        GetNotificationsByContractNumberRequest request = new GetNotificationsByContractNumberRequest();
        Assertions.assertThrows(ConstraintViolationException.class, () -> inputValidator.validateGetNotificationsByContractNumberRequest(request));
    }

    @Test
    public void validateGetNotificationsByContractNumberRequestTest2() {
        GetNotificationsByContractNumberRequest request = new GetNotificationsByContractNumberRequest();
        request.setContractNumber("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> inputValidator.validateGetNotificationsByContractNumberRequest(request));
    }

    @Test
    public void validateGetNotificationsByContractNumberRequestTest3() throws HandledServiceException {
        GetNotificationsByContractNumberRequest request = new GetNotificationsByContractNumberRequest();
        request.setContractNumber("31231");
        inputValidatorWithMocks.validateGetNotificationsByContractNumberRequest(request);
        Mockito.verify(contractRepository).findByContractNumber("31231");
    }


}
