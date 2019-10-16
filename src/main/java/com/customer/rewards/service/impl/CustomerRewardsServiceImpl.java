package com.customer.rewards.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.customer.rewards.data.SampleData;
import com.customer.rewards.dto.CustomerRewardsDTO;
import com.customer.rewards.service.CustomerRewardsService;

@Service
public class CustomerRewardsServiceImpl implements CustomerRewardsService{
	
	@Override
	public CustomerRewardsDTO getCustomerRewards(String customer)throws ParseException {
		List<SampleData> sampleData = getSampleData();
		//caluclate points
		List<SampleData> includePoints = new ArrayList<>();
		for(SampleData data:sampleData){
			data.setPoints(caluclatePoints(data));
			includePoints.add(data);
		}

		//get total count
		Map<Object, Long> months = includePoints.stream()
				.filter(c -> c.getCustomerName().equals(customer))
				.collect(Collectors.groupingBy(element -> element.getCreatedDate().getMonth(), Collectors.summingLong(element->element.getPoints())));

		List<CustomerRewardsDTO.MonthDetails> month = new ArrayList<CustomerRewardsDTO.MonthDetails>(); 
		Long total = 0l;
		for (Entry<Object, Long> entry : months.entrySet()) {
			CustomerRewardsDTO.MonthDetails dto = new CustomerRewardsDTO().new MonthDetails();
			dto.setMonth(((Integer)entry.getKey()+1));
			Long monthTotal = entry.getValue();
			total += monthTotal;
			dto.setMonthPoints(monthTotal);
			
			month.add(dto);
		}
		
		CustomerRewardsDTO customerDetails = new CustomerRewardsDTO();
		customerDetails.setTotal(total);
		customerDetails.setMonthDetails(month);
		return customerDetails;
	}
	//caluclate points
	private Integer caluclatePoints(SampleData data) {
		Integer amount = data.getPurchaseAmount();
		return amount>=100 ?  50+(amount%100)*2 : amount>=50 ? (amount-50)*1 : 0;
	}

	//sample data
	private List<SampleData> getSampleData() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		SampleData cr1 = new SampleData();
		cr1.setCustomerName("CR1");
		cr1.setPurchaseAmount(130);
		cr1.setCreatedDate(sdf.parse("20/08/2019"));
		
		SampleData cr2 = new SampleData();
		cr2.setCustomerName("CR1");
		cr2.setPurchaseAmount(120);
		cr2.setCreatedDate(sdf.parse("22/08/2019"));
		
		SampleData cr3 = new SampleData();
		cr3.setCustomerName("CR1");
		cr3.setPurchaseAmount(100);
		cr3.setCreatedDate(sdf.parse("10/09/2019"));
		
		// 2nd Customer
		SampleData c1 = new SampleData();
		c1.setCustomerName("CR2");
		c1.setPurchaseAmount(70);
		c1.setCreatedDate(sdf.parse("23/08/2019"));
		
		SampleData c2 = new SampleData();
		c2.setCustomerName("CR2");
		c2.setPurchaseAmount(140);
		c2.setCreatedDate(sdf.parse("12/09/2019"));
		
		List<SampleData> list = new ArrayList<>();
		list.add(cr1);
		list.add(cr2);
		list.add(cr3);
		list.add(c1);
		list.add(c2);
		
		return list;
	}
}
