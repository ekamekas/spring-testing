package io.github.ekamekas.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
public class KeranjangService {
    /*
        Process payment from client then return change if exist.
     */
    public Double payment(Double payment, Long time){
        if(null==payment) return 0d;
        return payment - getDiscount(payment, Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalTime());
    }

    /*
        Get total discount based on payment and time of payment
     */
    public Double getDiscount(Double payment, LocalTime time){
        return getDiscountByPaymentTotal(payment) + getDiscountByPaymentTime(payment, time);
    }

    /*
        Get discount based on payment
     */
    public Double getDiscountByPaymentTotal(Double payment){
        if(null==payment) return 0d;
        if(payment >= 500000){
            return payment * .1;
        } else if(payment >= 200000){
            return payment * .05;
        } else if(payment >= 100000){
            return payment * .025;
        } else
            return 0d;
    }

    /*
        Get discount based on time of payment
     */
    public Double getDiscountByPaymentTime(Double payment, LocalTime time){
        if(null==payment || null==time) return 0d;
        if(time.getSecond() >= 45 )
            return 0d;
        else if(time.getSecond() >= 30)
            return payment * .1;
        else if(time.getSecond() >= 15)
            return payment * .05;
        else if(time.getSecond() >= 5)
            return payment * .025;
        else return 0d;
    }

}
