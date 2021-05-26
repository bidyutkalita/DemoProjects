package com.bidyut.service;

import java.util.Date;

import com.bidyut.dao.AccountDao;
import com.bidyut.dao.AccountDaoImpl;
import com.bidyut.dto.AccountDeLinkRequestDTO;
import com.bidyut.dto.AccountLinkRequestDTO;
import com.bidyut.dto.AccountValidationResponse;
import com.bidyut.model.BankAccountMapping;


public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao= new AccountDaoImpl();

	@Override
	public AccountValidationResponse getAccountMappingByAccountNo(String acccountNo) {
		AccountValidationResponse accountValidationResponse = null;

		BankAccountMapping accountMapping = accountDao.getBankAccountMappingByAccountNo(acccountNo);

		if (accountMapping != null) {
			accountValidationResponse = new AccountValidationResponse();
			accountValidationResponse.setAccountNumber(accountMapping.getAccountNumber());
			accountValidationResponse.setAccountStatus(accountMapping.getAccountStatus());
			accountValidationResponse.setAccountTypes(accountMapping.getAccountType());
			accountValidationResponse.setLinkedStatus(accountMapping.getLinkedStatus());
			accountValidationResponse.setTransactionID(accountMapping.getId() + "");
			accountValidationResponse.setTransactionTimeStamp(new Date().toString());

		}
		return accountValidationResponse;
	}
	
	
	@Override
	public void save(AccountLinkRequestDTO accountLinkRequestDTO) {
		
		BankAccountMapping bankAccountMapping = new BankAccountMapping();
		BankAccountMapping accountMapping=new AccountDaoImpl().getBankAccountMappingByAccountNo(accountLinkRequestDTO.getAccountNumber());
		if(accountMapping!=null)
		{
			bankAccountMapping.setId(accountMapping.getId());
		}
		bankAccountMapping.setAccountNumber(accountLinkRequestDTO.getAccountNumber());
		bankAccountMapping.setAccountStatus("Active");
		bankAccountMapping.setAccountType(accountLinkRequestDTO.getAccountTitle());
		bankAccountMapping.setBankName(accountLinkRequestDTO.getBankName());
		bankAccountMapping.setBranchCode(accountLinkRequestDTO.getBranchCode());
		bankAccountMapping.setCountryCode(accountLinkRequestDTO.getCountryCode());
		bankAccountMapping.setiDNumber(accountLinkRequestDTO.getiDNumber());
		bankAccountMapping.setiDType(accountLinkRequestDTO.getiDType());
		bankAccountMapping.setiMSI(accountLinkRequestDTO.getiMSI());
		bankAccountMapping.setLinkedStatus("Y");
		bankAccountMapping.setMobileNumber(accountLinkRequestDTO.getAuthorizedMobileNumber());
		bankAccountMapping.setNationality(accountLinkRequestDTO.getNationality());
		bankAccountMapping.setReferenceID(accountLinkRequestDTO.getReferenceID());

		accountDao.save(bankAccountMapping);
		

	
	}
	
	@Override
	public boolean deLink(AccountDeLinkRequestDTO accountDeLinkRequestDTO) {
		
		BankAccountMapping bankAccountMapping = new BankAccountMapping();
		BankAccountMapping accountMapping=new AccountDaoImpl().getBankAccountMappingByAccountNo(accountDeLinkRequestDTO.getAccountNumber());
		try {
		if(accountMapping!=null)
		{
			bankAccountMapping.setId(accountMapping.getId());
			accountDao.deLink(bankAccountMapping);
			return true;
		}else
			return false;
		}catch (Exception e) {
			return false;
		}
	
		
		

	
	}

}
