package com.progressoft.FX.Deals.Data.Warehouse.repository;

import com.progressoft.FX.Deals.Data.Warehouse.model.FXDeal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealRepository extends MongoRepository<FXDeal, Long> {
}
