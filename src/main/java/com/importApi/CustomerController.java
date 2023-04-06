package com.importApi;

import com.commercetools.importapi.client.ProjectApiRoot;
import com.commercetools.importapi.models.customers.CustomerAddress;
import com.commercetools.importapi.models.customers.CustomerImport;
import com.commercetools.importapi.models.importrequests.CustomerImportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    ProjectApiRoot apiRoot =client.createApiClient();

    @PostMapping("/im")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            List<CustomerDto> customerDTOs = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                CustomerDto customerDTO = new CustomerDto();
                customerDTO.setCustomerNumber(data[0]);
                customerDTO.setEmail(data[1]);
                customerDTO.setId(data[2]);
                customerDTO.setExternalId(data[3]);
                customerDTO.setPassword(data[4]);
                customerDTO.setFirstName(data[5]);
                customerDTO.setLastName(data[6]);
                customerDTO.setCustomerNumber(data[7]);
            }
           List<CustomerDto> customerDto2= customerDTOs;
            customerDto2.stream().map(e->

            apiRoot.customers().importContainers().withImportContainerKeyValue("arvind").post(
                            CustomerImportRequest.builder()
                                    .resources( CustomerImport.builder()
                                            .key("jdshfg")
                                            .firstName(e.getFirstName())
                                            .lastName(e.getLastName())
                                            .companyName(e.getCompanyName())
                                            .password(e.getPassword())
                                            .email(e.getEmail())
                                            .customerNumber(e.getCustomerNumber())
                                            .externalId(e.getExternalId())
                                            .addresses(
                                                    CustomerAddress.builder()
                                                            .key("kshdfgu")
                                                            .apartment("dsahufad")
                                                            .country("nhsjg")
                                                            .city("sdadnkf")
                                                            .build()
                                            )
                                            .build())
                                    .build()
                    )
                    .executeBlocking().getBody()
            ).collect(Collectors.toList());
            // do something with the customerDTOs list, e.g. save to a database
//            CustomerAddress jds = CustomerAddress.builder()
//                    .key("kshdfgu")
//                    .apartment("dsahufad")
//                    .country("nhsjg")
//                    .city("sdadnkf")
//
//                    .build();
//            CustomerImport customerImport = CustomerImport.builder()
//                    .key("jdshfg")
//                    .firstName(",nv")
//                    .lastName("sds")
//                    .companyName("dshjk")
//                    .password("nbdsjhkf")
//                    .email("badsfsadh@gmail.com")
//                    .addresses(jds)
//                    .build();
//            CustomerImportRequest customerImportRequest=CustomerImportRequest.builder()
//                    .resources(customerImport)
//                    .build();
//              apiRoot.customers().importContainers().withImportContainerKeyValue("arvind").post(customerImportRequest)
//                    .executeBlocking().getBody();
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }
}
