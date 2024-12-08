package com.example.demo;

import java.time.LocalDateTime;

public class LPS {
    private final LocalDateTime timestamp;
    private final String component;
    private final String operation;
    private final String entity;
    private final String action;
    private final Long productId;
    private final Long userId;

    private LPS(Builder builder) {
        this.timestamp = builder.timestamp;
        this.component = builder.component;
        this.operation = builder.operation;
        this.entity = builder.entity;
        this.action = builder.action;
        this.productId = builder.productId;
        this.userId = builder.userId;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getComponent() { return component; }
    public String getOperation() { return operation; }
    public String getEntity() { return entity; }
    public String getAction() { return action; }
    public Long getProductId() { return productId; }
    public Long getUserId() { return userId; }

    @Override
    public String toString() {
        return String.format("Timestamp: %s, Component: %s, Event: Operation=%s Entity=%s Action=%s, User: %d, ProductId: %s",timestamp, component, operation, entity, action, userId, productId != null ? productId : "N/A");
    }

    static class Builder {
        private LocalDateTime timestamp;
        private String component;
        private String operation;
        private String entity;
        private String action;
        private Long productId;
        private Long userId;

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setComponent(String component) {
            this.component = component;
            return this;
        }

        public Builder setOperation(String operation) {
            this.operation = operation;
            return this;
        }

        public Builder setEntity(String entity) {
            this.entity = entity;
            return this;
        }

        public Builder setAction(String action) {
            this.action = action;
            return this;
        }

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public LPS build() {
            return new LPS(this);
        }
    }
}