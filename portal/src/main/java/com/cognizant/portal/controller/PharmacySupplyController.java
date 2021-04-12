package com.cognizant.portal.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cognizant.portal.exception.MedicineNotFoundException;
import com.cognizant.portal.model.MedicineDemand;
import com.cognizant.portal.model.MedicineSupply;
import com.cognizant.portal.service.SupplyFeignService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/user")
@Controller
public class PharmacySupplyController {
	

	@Autowired
	private SupplyFeignService feignService;

	List<MedicineSupply> medicineSupplyList = new ArrayList<>();

	@SuppressWarnings("unchecked")
	@ApiOperation(value="Get medicine supply details")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping("/getMedicineSupply")
	public ModelAndView getMedicineSupply(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand,
			HttpSession session) throws Exception {

		log.info("Start");

		ModelAndView modelAndView = new ModelAndView();
		String token = (String) session.getAttribute("token");
		List<MedicineDemand> medicineDemandList = new ArrayList<>();
		medicineDemandList.add(medicineDemand);
		log.debug("medicineDemand{} :", medicineDemand);

		ResponseEntity<?> response = feignService.getPharmacySupply(token, medicineDemandList);
		
		HttpStatus statusCode = response.getStatusCode();
		
		if (statusCode == HttpStatus.NOT_FOUND) {
			modelAndView.addObject("error", true);
		}
		
		if (response.getBody() instanceof String) {
			throw new MedicineNotFoundException("Medicine not found");
		}
		
		log.debug("response atik{}:", response);

		medicineSupplyList = (List<MedicineSupply>) response.getBody();
		
		log.debug("medicineSupplyList{}:", medicineSupplyList);
		

		modelAndView.setViewName("medicineSupplyList");
		modelAndView.addObject("medicineSupplyList", medicineSupplyList);

		return modelAndView;
	}
	@ApiOperation(value="Shows medicine supply details")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping("/showMedicineSupply")
	public ModelAndView showMedicineSupply(HttpSession session) {
		String token = (String) session.getAttribute("token");
		@SuppressWarnings("unchecked")
		List<MedicineSupply> medicineSupply = (List<MedicineSupply>) feignService.getMedicineSupply(token).getBody();
		ModelAndView mv = new ModelAndView("medicineSupplyList");
		mv.addObject("medicineSupplyList", medicineSupply);
		return mv;
	}
	@ApiOperation(value="Displays medicine demand")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping("/medicineDemand")
	public String getMedicineDemandPage(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand) {
		log.info("Start");
		return "medicineDemand";
	}

	@ApiOperation(value="Shows medince demand")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping("/showMedicineDemand")
	public ModelAndView getMedicineDemandList(@ModelAttribute("medicineDemand") MedicineDemand medicineDemand,
			HttpSession session) {
		log.info("Start");
		String token = (String) session.getAttribute("token");
		@SuppressWarnings("unchecked")
		List<MedicineDemand> medicineDemandList = (List<MedicineDemand>) feignService.getMedicineDemand(token)
				.getBody();
		ModelAndView mv = new ModelAndView("showMedicineDemand");
		mv.addObject("medicineDemandList", medicineDemandList);
		log.debug("medicineDemandList{}:", medicineDemandList);
		return mv;
	}

}
