package com.mrcodekiddie.tobuytoday;

import java.io.Serializable;

public class Order implements Serializable
{
    public String itemCode;
    public String itemName;

    public Order(String itemCode, String itemName)
    {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }
}
