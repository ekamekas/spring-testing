package io.github.ekamekas.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeranjangServiceTest {

    @Autowired
    private KeranjangService service;

    @Test
    public void givenPayment_whenGetDiscountByPaymentTotal_thenReturnDiscount(){
        Double payment;
        Double discount;
        // Case 1   payment < 100000 get 0% discount
        // Given
        payment = Double.valueOf(90000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(0);
        //
        // Given
        payment = Double.valueOf(0);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(0);

        // Case 2   100000 <= payment < 200000 get 2.5% discount
        // Given
        payment = Double.valueOf(150000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(3750);
        //
        // Given
        payment = Double.valueOf(100000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(2500);

        // Case 3   200000 <= payment < 500000 get 5% discount
        // Given
        payment = Double.valueOf(300000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(15000);
        //
        // Given
        payment = Double.valueOf(200000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(10000);

        // Case 4   payment >= 500000 get 10% discount
        // Given
        payment = Double.valueOf(1000000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(100000);
        //
        // Given
        payment = Double.valueOf(500000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(50000);

        // Case 5   payment is null get 0% discount
        // Given
        payment = Double.valueOf(90000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(0);

        // Case 6   payment is negative number get 0% discount
        // Given
        payment = Double.valueOf(-1000);
        // When
        discount = service.getDiscountByPaymentTotal(payment);
        // Then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    public void givenTimeInEpoch_whenGetDiscountByPaymentTime_thenReturnDiscount(){
        Double payment = 500000d;
        LocalTime time;
        Double discount;
        // Case 1   5 >= time < 15 get 2.5% discount
        // Given
        time = LocalTime.of(0,0,5);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(12500);
        //
        // Given
        time = LocalTime.of(0,0,7);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(12500);

        // Case 2   15 >= time < 30 get 5% discount
        // Given
        time = LocalTime.of(0,0,15);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(25000);
        //
        // Given
        time = LocalTime.of(0,0,28);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(25000);

        // Case 3   30 >= time < 45 get 10% discount
        // Given
        time = LocalTime.of(0,0,30);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(50000);
        //
        // Given
        time = LocalTime.of(0,0,38);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(50000);

        // Case 4   45 >= time < 5 get 0% discount
        // Given
        time = LocalTime.of(0,0,45);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(0);
        //
        // Given
        time = LocalTime.of(0,0,52);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(0);
        //
        // Given
        time = LocalTime.of(0,0,2);
        // When
        discount = service.getDiscountByPaymentTime(payment,time);
        // Then
        assertThat(discount).isEqualTo(0);

        // Case 5   payment is null get discount 0%
        // Given
        // payment null
        time = LocalTime.of(0,0,45);
        // When
        discount = service.getDiscountByPaymentTime(null,time);
        // Then
        assertThat(discount).isEqualTo(0);
        //
        // Given
        // time null
        // When
        discount = service.getDiscountByPaymentTime(null,null);
        // Then
        assertThat(discount).isEqualTo(0);

        // Case 5   time is null throw err
        // Given
        // time null
        // When
        discount = service.getDiscountByPaymentTime(payment,null);
        // Then
        assertThat(discount).isEqualTo(0);
    }

}
