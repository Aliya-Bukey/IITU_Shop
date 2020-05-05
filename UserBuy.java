package IITU_shop.data;

import java.io.Serializable;

public class UserBuy implements Serializable {
    private Long id;
    private Long user_id;
    private String organization;
    private int count;
    private int totalsum;

    public UserBuy() {
    }

    public UserBuy(Long id, Long user_id, String organization, int count, int totalsum) {
        this.id = id;
        this.user_id = user_id;
        this.organization = organization;
        this.count = count;
        this.totalsum = totalsum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(int totalsum) {
        this.totalsum = totalsum;
    }
}
