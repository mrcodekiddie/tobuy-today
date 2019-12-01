package com.mrcodekiddie.tobuytoday;

public class ItemData
{
  Order order;
  EditModel itemQuantity;

    public ItemData(Order order, EditModel itemQuantity) {
        this.order = order;
        this.itemQuantity = itemQuantity;
    }
}
