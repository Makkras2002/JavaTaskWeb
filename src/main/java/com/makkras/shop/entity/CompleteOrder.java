package com.makkras.shop.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompleteOrder extends CustomEntity{
    private Long completeOrderId;
    private LocalDate completeOrderDate;
    private User user;
    private boolean isCompleted;
    private List<ComponentOrder> componentOrders = new ArrayList<>();

    public CompleteOrder(LocalDate completeOrderDate, User user, boolean isCompleted, List<ComponentOrder> componentOrders) {
        this.completeOrderDate = completeOrderDate;
        this.user = user;
        this.isCompleted = isCompleted;
        this.componentOrders = componentOrders;
    }

    public CompleteOrder(Long completeOrderId, LocalDate completeOrderDate, User user, boolean isCompleted, List<ComponentOrder> componentOrders) {
        this.completeOrderId = completeOrderId;
        this.completeOrderDate = completeOrderDate;
        this.user = user;
        this.isCompleted = isCompleted;
        this.componentOrders = componentOrders;
    }

    public Long getCompleteOrderId() {
        return completeOrderId;
    }

    public void setCompleteOrderId(Long completeOrderId) {
        this.completeOrderId = completeOrderId;
    }

    public LocalDate getCompleteOrderDate() {
        return completeOrderDate;
    }

    public void setCompleteOrderDate(LocalDate completeOrderDate) {
        this.completeOrderDate = completeOrderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompletedStatus(boolean completed) {
        isCompleted = completed;
    }

    public List<ComponentOrder> getComponentOrders() {
        return componentOrders;
    }

    public void setComponentOrders(List<ComponentOrder> componentOrders) {
        this.componentOrders = componentOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompleteOrder that = (CompleteOrder) o;

        if (isCompleted != that.isCompleted) return false;
        if (completeOrderId != null ? !completeOrderId.equals(that.completeOrderId) : that.completeOrderId != null)
            return false;
        if (completeOrderDate != null ? !completeOrderDate.equals(that.completeOrderDate) : that.completeOrderDate != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return componentOrders != null ? componentOrders.equals(that.componentOrders) : that.componentOrders == null;
    }

    @Override
    public int hashCode() {
        int result = completeOrderId != null ? completeOrderId.hashCode() : 0;
        result = 31 * result + (completeOrderDate != null ? completeOrderDate.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (isCompleted ? 1 : 0);
        result = 31 * result + (componentOrders != null ? componentOrders.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CompleteOrder{");
        sb.append("completeOrderId=").append(completeOrderId);
        sb.append(", completeOrderDate=").append(completeOrderDate);
        sb.append(", user=").append(user);
        sb.append(", isCompleted=").append(isCompleted);
        sb.append(", componentOrders=").append(componentOrders);
        sb.append('}');
        return sb.toString();
    }
}
