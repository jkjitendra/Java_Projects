package org.program.streamAPIconcepts.prepare;

public class Order {
    private int orderId;
    private String productId;
    private OrderStatus status;

    public Order(int orderId, String productId, OrderStatus status) {
        this.orderId = orderId;
        this.productId = productId;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
