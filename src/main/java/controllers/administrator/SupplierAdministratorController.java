package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PetSitterService;
import controllers.AbstractController;
import domain.Supplier;

@Controller
@RequestMapping("/supplier/administrator")
public class SupplierAdministratorController extends AbstractController{
	
	@Autowired
	private PetSitterService petSitterService;
	
	@RequestMapping(value="/block", method = RequestMethod.GET)
	public ModelAndView block(@RequestParam int supplierId){
		ModelAndView result;
		Supplier supplier;
		
		result = new ModelAndView("supplier/list");
		supplier = petSitterService.findOne(supplierId);
		
		if(supplier!=null){
			result.addObject("isPetSitter", true);
		}else{
			//TODO: Complete with PetShipper in 3rd sprint
			result.addObject("notExist", true);
		}
		
		result.addObject("supplierId", supplierId);
		result.addObject("requestURI", "supplier/administrator/list.do");//TODO: Create list method
		
		return result;
	}
	
//	@RequestMapping(value = "/sign", method = RequestMethod.GET)
//	public ModelAndView sign(@RequestParam int contractId){
//		ModelAndView result;
//		Contract contract;
//		Boolean showSign = true;
//		Boolean isMine = false;
//		Consumer consumer;
//		
//		consumer = consumerService.findByUserAccount(LoginService.getPrincipal());
//		contract = contractService.findOne(contractId);
//		
//		if(consumer.getContracts().contains(contract)){
//			isMine=true;
//		}
//		
//		result = createSignModelAndView(contract);
//		result.addObject("showSign", showSign);
//		result.addObject("isMine", isMine);
//		
//		return result;
//	}
//	
//	@RequestMapping(value = "/sign", method = RequestMethod.POST, params = "sign")
//	public ModelAndView saveSign(@Valid Contract contract, BindingResult binding){
//		ModelAndView result;
//		
//		if(binding.hasErrors()){
//			result = createSignModelAndView(contract);
//		}else{
//			try{
//				contractService.editSignedByConsumer(contract);
//				result = new ModelAndView("redirect:list.do");
//			}catch(Throwable oops){
//				result = createEditModelAndView(contract,"contract.commit.error");
//			}
//		}
//		
//		return result;
//	}
	
//	"https://bitbucket.org/hielf/d-t201415/src/9a8b10a7bdc65e384889893a01322d318b05c27c/L07%20Workspace/Acme-Broker/src/main/java/controllers/consumer/ContractConsumerController.java?at=master&fileviewer=file-view-default

}
