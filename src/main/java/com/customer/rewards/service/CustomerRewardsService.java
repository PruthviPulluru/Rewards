package com.customer.rewards.service;

import java.text.ParseException;

import com.customer.rewards.dto.CustomerRewardsDTO;

public interface CustomerRewardsService {

	CustomerRewardsDTO getCustomerRewards(String customer)throws ParseException;
}
