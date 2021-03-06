package com.osbilisim.controllers.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.osbilisim.controllers.PaymentController;
import com.osbilisim.inputs.PaymentRequest;
import com.osbilisim.output.BaseResponse;
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentControllerTest {
	
	@Autowired
	PaymentController paymentController ;
	
	@Test
	public void payTest()  {
		PaymentRequest request = new PaymentRequest();
		request.setUserId(2);
		request.setDiscount(0.0);
		request.setItemId("asddasdasd");
		BaseResponse response;
		try {
			response = paymentController.pay("asd", request);
			System.out.println("response: "+response.getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		
		}
		
	}

}
