package com.trisun.utils;

import com.trisun.entities.Buyer;
import com.trisun.entities.User;
import com.trisun.entities.dto.BuyerDto;

public class DtoMapper {
	public static BuyerDto buyerToBuyerDto(Buyer buyer,User user) {
		BuyerDto buyerDto=new BuyerDto();
		buyerDto.setEmail(user.getEmailId());
		buyerDto.setFullName(buyer.getFullName());
		buyerDto.setId(buyer.getId());
		return buyerDto;
	}
	

}
