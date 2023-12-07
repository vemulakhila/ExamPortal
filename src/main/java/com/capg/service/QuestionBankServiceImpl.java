package com.capg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.entity.Category;
import com.capg.entity.QuestionBank;
import com.capg.exceptions.IdNotFoundException;
import com.capg.repo.CategoryRepository;
import com.capg.repo.QuestionBankRepo;
import com.capg.util.AppConstants;

@Service
public class QuestionBankServiceImpl implements QuestionBankService {

	@Autowired
	QuestionBankRepo questionBankRepo;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public QuestionBank addQuestionBank(QuestionBank questionBank) {
//		System.out.println(questionBank);
//		System.out.println(questionBank.getCategory());
		Category category=null;
		System.out.println(questionBank.getCategory());
		int category_id = questionBank.getCategory().getCategoryId();
		if(categoryRepository.existsById(category_id)) {
			category = categoryRepository.findById(category_id).get();
			System.out.println("Fetching Category: \n"+category);
			questionBank.setCategory(category);
		}
		return questionBankRepo.save(questionBank);
//		return questionBank;
	}

	@Override
	public QuestionBank updateQuestionBankById(int QuestionBankId, QuestionBank questionbank)throws IdNotFoundException
	
	{
		
		QuestionBank updateQuestionBankById=null;
		
		if(questionBankRepo.existsById(QuestionBankId))
		{
			updateQuestionBankById=questionBankRepo.findById(QuestionBankId).get();
			questionbank.setQuestionBankId(QuestionBankId);
			return questionBankRepo.save(questionbank);
		}
		else
		{
			throw new IdNotFoundException(AppConstants.TEST_ID_NOT_FOUND_INFO);
		}
		
		}


		public List<QuestionBank> getAllQuestionBanks() 
		{
		
			return questionBankRepo.findAll();
		}

		
	public String deleteQuestionBankById(int QuestionBankId) throws IdNotFoundException
		{
		
			String msg;
			if(questionBankRepo.existsById(QuestionBankId))
			{
				questionBankRepo.deleteById(QuestionBankId);
				msg="Id deleted successfully";
			}
			else
			{
				throw new IdNotFoundException(AppConstants.TEST_ID_NOT_FOUND_INFO);
			}
			
			return msg;
		}

	
}
