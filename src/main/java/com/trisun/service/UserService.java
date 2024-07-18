package com.trisun.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trisun.core.UserRoles;
import com.trisun.entities.Buyer;
import com.trisun.entities.User;
import com.trisun.entities.UserType;
import com.trisun.entities.dto.BuyerDto;
import com.trisun.entities.dto.UserAuthDto;
import com.trisun.repository.BuyerRepository;
import com.trisun.repository.UserRepository;
import com.trisun.repository.UserTypeRepository;
import com.trisun.response.ApiResponse;
import com.trisun.utils.DtoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserTypeRepository userTypeRepository;
	private final BuyerRepository buyerRepository;
	
	@Transactional
	public ApiResponse createUser(UserAuthDto userRegisterDto) {
		ApiResponse apiResponse=new ApiResponse();
	try {
		UserType userType=userTypeRepository.findByRole(userRegisterDto.getUserRole())
				   .orElseThrow(()->new RuntimeException("unKnown uSerRole"));	
	    User user = new User();
		user.setEmailId(userRegisterDto.getEmail());
		String encodedPassword=passwordEncoder.encode(userRegisterDto.getPassword());
		user.setPassword(encodedPassword);
		user.setUserType(userType);
		User savedUser=userRepository.save(user);
		Buyer buyer=new Buyer();
		buyer.setFullName(userRegisterDto.getFullName());
		buyer.setUser(savedUser);
	    Buyer savedBuyer= buyerRepository.save(buyer);
		return apiResponse.success(DtoMapper.buyerToBuyerDto(savedBuyer, savedUser));
		
	} catch (Exception e) {
		return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"data not saved in databse");
	}	
	}	
	
	public ApiResponse login(UserAuthDto userAuthDto){	
//		System.out.println("inside login");
		ApiResponse apiResponse=new ApiResponse();
		try {
//			System.out.println("inside try block");
			
			UserType userType=userTypeRepository.findByRole(userAuthDto.getUserRole())
					   .orElseThrow(()->new RuntimeException("unKnown uSerRole"));
//						System.out.println("inside userType");
						
			User user=userRepository.findByEmailId(userAuthDto.getEmail())
					.orElseThrow(()-> new RuntimeException(userAuthDto.getEmail()));
//			System.out.println("inside user repo");
			
			if(passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
//				System.out.println("inside first if");
				if(userAuthDto.getUserRole().equals(UserRoles.BUYER)) {
				
					Buyer buyer=buyerRepository.findByUser(user)
							.orElseThrow(()->new RuntimeException("Buyer not found"));
				  BuyerDto buyerDto=DtoMapper.buyerToBuyerDto(buyer, user);				 
				  return apiResponse.success(buyerDto);				 
				}else {
					return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "user type Not found ");
				}					
			}else {
				return apiResponse.error(HttpStatus.UNAUTHORIZED.value(), "user not found");
			}			
		} catch (Exception e) {
			return apiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR.value(),"data not saved in database");
		}	
	}	 

}
