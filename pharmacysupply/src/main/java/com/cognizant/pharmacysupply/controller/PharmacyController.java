package com.cognizant.pharmacysupply.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.exception.TokenValidationFailedException;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;
import com.cognizant.pharmacysupply.service.PharmacyServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PharmacyController {
	
	@Autowired
	private PharmacyServiceImpl pharmacyService;

	/*
	 *  Method Name --> pharmacySupply
	 *  @param      --> Medicine Demand 
	 *  @return     --> Pharmacy Supply
	 *  This method takes medicine demand as input checks if there is enough stock to 
	 *  fulfill the demand, if sufficient stock is available then it supplies the medicine
	 *  else it will not supply
	 */
	@ApiOperation(value="Gets details of placed order")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@PostMapping("/pharmacy-supply")
	public ResponseEntity<?> getPharmacySupply(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException {
		log.info("Start");

		log.debug("medicineDemandList {}:", medicineDemandList);
		List<PharmacyMedicineSupply> pharmacySupply = null;
		if (pharmacyService.validateToken(token)) {
			pharmacySupply = pharmacyService.getPharmacySupplyCount(token, medicineDemandList);
			
			if (pharmacySupply == null)  {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			
			log.info("End");
			return new ResponseEntity<>(pharmacySupply, HttpStatus.OK);
		}
		log.info("End");
		throw new TokenValidationFailedException("Token is no longer valid");
	}
	
	/*
	 *  Method Name --> getMedicineSupply
	 *  @param      --> Null
	 *  @return     --> Medicine Supplied
	 *  This method returns the medicine supplied till this time.
	 */
	@ApiOperation(value="Gets details of medicine supply")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/getMedicineSupply")
	public ResponseEntity<?> getMedicineSupply(@RequestHeader("Authorization") String token) {
		List<PharmacyMedicineSupply> medicineSupply = null;
		if (pharmacyService.validateToken(token)) {
			medicineSupply = pharmacyService.getMedicineSupply();
			return new ResponseEntity<>(medicineSupply, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}

	/*
	 *  Method Name --> getMedicineDemand
	 *  @param      --> Null
	 *  @return     --> Medicine Demanded
	 *  This method returns the medicine demanded till this time.
	 */
	@ApiOperation(value="Gets medicine demand")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping("/getMedicineDemand")
	public ResponseEntity<?> getMedicineDemand(@RequestHeader(name = "Authorization") String token) {
		List<MedicineDemand> medicineDemand = null;
		if (pharmacyService.validateToken(token)) {
			medicineDemand = pharmacyService.getMedicineDemand();
			return new ResponseEntity<>(medicineDemand, HttpStatus.OK);
		}
		throw new TokenValidationFailedException("Token is no longer valid");
	}

}
