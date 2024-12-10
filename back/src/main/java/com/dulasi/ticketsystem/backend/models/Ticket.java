package com.dulasi.ticketsystem.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "ticket")
public class Ticket {
    @Id
    private String id;
    private String ticketType;
    private BigDecimal ticketPrice;
    private String ticketVendor;
    private String sellTime;
    private String ticketCustomer;
    private String buyTime;

    public Ticket() {
    }

    public Ticket(String ticketType, BigDecimal ticketPrice, String ticketVendor, String sellTime) {
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.ticketVendor = ticketVendor;
        this.sellTime = sellTime;
    }

    public Ticket(String id, String ticketType, BigDecimal ticketPrice, String ticketVendor, String sellTime, String ticketCustomer, String buyTime) {
        this.id = id;
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        this.ticketVendor = ticketVendor;
        this.sellTime = sellTime;
        this.ticketCustomer = ticketCustomer;
        this.buyTime = buyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketVendor() {
        return ticketVendor;
    }

    public void setTicketVendor(String ticketVendor) {
        this.ticketVendor = ticketVendor;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(String sellTime) {
        this.sellTime = sellTime;
    }

    public String getTicketCustomer() {
        return ticketCustomer;
    }

    public void setTicketCustomer(String ticketCustomer) {
        this.ticketCustomer = ticketCustomer;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketType='" + ticketType + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", ticketVendor='" + ticketVendor + '\'' +
                ", sellTime='" + sellTime + '\'' +
                ", ticketCustomer='" + ticketCustomer + '\'' +
                ", buyTime='" + buyTime + '\'' +
                '}';
    }
}
