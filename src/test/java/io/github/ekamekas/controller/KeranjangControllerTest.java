package io.github.ekamekas.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KeranjangControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenRequestWithCartQuery_whenInquiryPaymentAndCartIsNumberOrNull_thenReturnPaymentWith200() throws Exception{
        this.mockMvc.perform(get("/v1/keranjang/payment/inquiry?cart=100")).andDo(print()).andExpect(status().is(200));
        this.mockMvc.perform(get("/v1/keranjang/payment/inquiry?cart=")).andDo(print()).andExpect(status().is(200));
    }

    @Test
    public void givenRequestWithCartQuery_whenInquiryPaymentAndCartIsNotNumber_thenReturnPaymentWith400() throws Exception{
        this.mockMvc.perform(get("/v1/keranjang/payment/inquiry?cart=testing")).andDo(print()).andExpect(status().is(400));
    }

    @Test
    public void givenRequest_whenInquiryPayment_thenReturnPaymentWith400() throws Exception{
        this.mockMvc.perform(get("/v1/keranjang/payment/inquiry")).andDo(print()).andExpect(status().is(400));
    }

}
