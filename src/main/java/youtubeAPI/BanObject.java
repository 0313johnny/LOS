package youtubeAPI;

import java.math.BigInteger;

public class BanObject {
    String bannerId;
    BigInteger banTime;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public BigInteger getBanTime() {
        return banTime;
    }

    public void setBanTime(BigInteger banTime) {
        this.banTime = banTime;
    }

    @Override
    public String toString() {
        return "BanObject{" +
                "bannerId='" + bannerId + '\'' +
                ", banTime=" + banTime +
                '}';
    }
}
