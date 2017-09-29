package com.sws.newtec.modules.train.service;

import com.sws.newtec.modules.train.beans.Train;

import java.util.List;

public interface TrainService {
    public List<Train> queryTrainsList();

    public Train getTrainById(Long id);
    
    public void saveTrain(Train train);
    
    public void removeTrainById(Long id);
    
    public void removeTrainsList();

    public void updateTrain(Train train);
}
