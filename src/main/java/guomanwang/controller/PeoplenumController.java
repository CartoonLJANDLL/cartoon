package guomanwang.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import guomanwang.domain.Peoplenum;
import guomanwang.service.PeoplenumService;

@RequestMapping("/peoplenum")
@Controller
public class PeoplenumController {

	@Autowired
	private PeoplenumService peoplenumService;
	
	@ResponseBody
	@RequestMapping("/getPeoplenum")
	public void getPeoplenum() throws Exception {
		
		Peoplenum peoplenum = new Peoplenum();
		peoplenum.setHoldtime(3.00);;
		peoplenum.setUserid(3);
		peoplenum.setAccesstime(new Date());
		System.out.println("-------------------------------------------------peoplenum");
		peoplenum.setLefttime(new Date());
		
		int rs = 0;
		rs = this.peoplenumService.insertPeoplenum(peoplenum);
		try {
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Peoplenum============+ " + rs);
	}
	
	
}
