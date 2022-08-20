package service;

import domain.Fish;

public interface FishingSeivice {

    String luckyFishing(int userId);
    void reducePoints(int userId);
}
