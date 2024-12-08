package com.example.demo;

class UserProfile {
    private final Long userId;
    private int readCount;
    private int writeCount;
    private int searchCount;

    public UserProfile(Long userId) {
        this.userId = userId;
        this.readCount = 0;
        this.writeCount = 0;
        this.searchCount = 0;
    }

    public void incrementOperationCount(String operationType) {
        switch (operationType) {
            case "READ": this.readCount++; break;
            case "WRITE": this.writeCount++; break;
            case "SEARCH": this.searchCount++; break;
        }
    }

    public Long getUserId() { return userId; }
    public int getReadCount() { return readCount; }
    public int getWriteCount() { return writeCount; }
    public int getSearchCount() { return searchCount; }
}