package com.customer.rewards.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.service.CustomerRewardsService;

@RestController
public class CustomerRewardsController {
	@Autowired
	private CustomerRewardsService rewardsService;
	
	@GetMapping("/getRewards")
	public CustomerRewardsDTO getRewards(String customer) throws ParseException{
		return rewardsService.getCustomerRewards(customer);
	}
	
}
