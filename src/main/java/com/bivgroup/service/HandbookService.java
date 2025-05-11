package com.bivgroup.service;

import com.bivgroup.entity.Contract;
import com.bivgroup.entity.ContractExtension;
import com.bivgroup.entity.ProductDescription;
import com.bivgroup.pojo.HandbookValue;
import com.bivgroup.repository.ProductDescriptionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class HandbookService {

    private Logger logger = LoggerFactory.getLogger(HandbookService.class);

    @Inject
    ProductDescriptionRepository productDescriptionRepository;

    public Map<String, HandbookValue> getContractExtensionMap(Contract contract) {
        List<ProductDescription> productDescription = productDescriptionRepository.getProductDescription(contract.getProduct().getProductId());
        ContractExtension contractExtension = contract.getContractExtension();
        Map<String, HandbookValue> result = new HashMap<>();

        for (Field field: ContractExtension.class.getDeclaredFields()) {
            ProductDescription prd = getProductDescriptionByName(productDescription, field.getName());
            if (Objects.nonNull(prd)) {
                Object value = null;
                try {
                    field.setAccessible(true);
                    value = field.get(contractExtension);
                } catch (Exception ex) {
                    logger.error(String.format("Ошибка при получении значения поля справочника %s: %s", field.getName(), ex));
                    continue;
                } finally {
                    field.setAccessible(false);
                }
                HandbookValue handbookValue = new HandbookValue();
                handbookValue.setValue(value);
                handbookValue.setDescription(prd.getFieldDescription());
                result.put(prd.getFieldName(), handbookValue);

            }
        }

        return result;
    }

    private ProductDescription getProductDescriptionByName(List<ProductDescription> productDescription, String fieldName) {
        return productDescription.stream().filter(prd -> Objects.equals(prd.getFieldSysName(), fieldName)).findFirst().orElse(null);
    }
}
