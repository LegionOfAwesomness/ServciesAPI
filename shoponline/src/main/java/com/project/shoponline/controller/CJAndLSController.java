package com.project.shoponline.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shoponline.model.module1.Consumer;
import com.project.shoponline.model.module1.ConsumerData;
import com.project.shoponline.model.module1.User;
import com.project.shoponline.model.module2.Account;
import com.project.shoponline.model.module2.Commission2;
import com.project.shoponline.model.module2.CommissionDistribution;
import com.project.shoponline.model.module3.Advertiser;
import com.project.shoponline.model.module3.CJPI;
import com.project.shoponline.model.module3.Link;
import com.project.shoponline.model.module4.AdvancedReportResponse;
import com.project.shoponline.model.module4.CommissionDetail;
import com.project.shoponline.model.module4.Couponfeed;
import com.project.shoponline.model.module4.LSLinkLocatorResponse;
import com.project.shoponline.model.module4.PaymentHistorySummary;
import com.project.shoponline.service.AdvertiserLookupService;
import com.project.shoponline.service.CommissionAndRewardService;
import com.project.shoponline.service.EmailService;
import com.project.shoponline.service.UserService;
@RestController
public class CJAndLSController {

		@Autowired
		AdvertiserLookupService advertiserLookupService;

		
		@RequestMapping(value = "/getcjfiles", method = RequestMethod.GET)
		public CJPI getCJS() throws Exception {
			CJPI cjpiData = advertiserLookupService.getCJS();
			advertiserLookupService.saveAdvertiserLookupData(cjpiData);
			return cjpiData;
		}

		@RequestMapping(value = "/getCJDeveloperFromDB", method = RequestMethod.GET)
		public List<Advertiser> getCJDeveloperFromDB() throws Exception {
			return advertiserLookupService.getCJDeveloperFromDB();
		}
		@RequestMapping(value = "/saveCouponsForAllAdvertisers", method = RequestMethod.GET)
		public CJPI getCouponsForAllAdvertisers() throws Exception {
			CJPI cjpiData = advertiserLookupService.getCouponsForAllAdvertisersFromCJ();
			advertiserLookupService.saveCouponsForAllAdvertisers(cjpiData);
			return cjpiData;
		}
		@RequestMapping(value = "/saveAllLinksForAllAdvertisers", method = RequestMethod.GET)
		public CJPI getBannersForAllAdvertisers() throws Exception {
			CJPI cjpiData = advertiserLookupService.getBannersForAllAdvertisersFromCJ();
			advertiserLookupService.saveCouponsForAllAdvertisers(cjpiData);
			return cjpiData;
		}
		@RequestMapping(value = "/getAllLinksForAllAdvertisers", method = RequestMethod.GET)
		public List<Link> getAllLinksForAllAdvertisers() throws Exception {
			return advertiserLookupService.getAllLinksForAllAdvertisersFromCJ();
		}
		
		
		@RequestMapping(value = "/getLinkForAdvertisers/{advertiserId}", method = RequestMethod.GET)
		public List<Link> getLinkForAdvertisersCJ(@PathVariable String advertiserId) throws Exception {
			return advertiserLookupService.getLinkForAdvertisersCJ(advertiserId);
		}
		@RequestMapping(value = "/getCouponsForAllAdvertisers", method = RequestMethod.GET)
		public Iterable<Link> makeACallToLinkSearchServiceCJ() throws Exception {
			return advertiserLookupService.getCouponsForAllAdvertisersCJ();
		}
		@RequestMapping(value = "/getCouponsForAdvertiser/{advertiserId}", method = RequestMethod.GET)
		public List<Link> getCouponsForAllAdvertisers(@PathVariable String advertiserId) throws Exception {
			return advertiserLookupService.getCouponsForAdvertiser(advertiserId);
		}
//		@RequestMapping(value = "/getAllAdvertisers/LS", method = RequestMethod.GET)
		public LSLinkLocatorResponse  getLSFiles() throws Exception {
			System.out.println("entered controller----");
			LSLinkLocatorResponse lSLinkLocatorResponse = advertiserLookupService.getLSFiles();
			System.out.println("----lSLinkLocatorResponse------"+lSLinkLocatorResponse);
//			advertiserLookupService.saveLSLinkLocatorResponse(lSLinkLocatorResponse);
			return lSLinkLocatorResponse;
		}
//
//		@RequestMapping(value = "/getAllLinksForAllAdvertisers/LS", method = RequestMethod.GET)
		public ResponseEntity<String> getLSeveloperFromDB() throws Exception {
			return advertiserLookupService.makeACallToLSDeveloper();
		}
//		@RequestMapping(value = "/getCouponsForAllAdvertisersLS", method = RequestMethod.GET)
		public Couponfeed getCouponsForAllAdvertisersLS() throws Exception {
			System.out.println("it came to the controller---------------");
		return advertiserLookupService.getCouponsForAllAdvertisersLS();
		}
		
//		@RequestMapping(value = "/getCouponsLSForAdvertiser/{advertiserId}", method = RequestMethod.GET)
//		public List<Link> getCouponsForAllAdvertisersLS(@PathVariable String advertiserId) throws Exception {
//			return advertiserLookupService.getCouponsForAllAdvertisersLS(advertiserId);
//		}
		
		@RequestMapping(value = "/ls/advancedreports/PaymentHistorySummary", method = RequestMethod.GET)
		public AdvancedReportResponse getadvancedreportsLS(@RequestParam Map<String,String> allParams) throws Exception {
			System.out.println("it came to the controller---------------"+allParams.toString());
		     return advertiserLookupService.getadvancedreportsLS(allParams);
		}
		
		@RequestMapping(value = "/cj/CommissionDetail", method = RequestMethod.GET)
		public CommissionDetail getCommissionDetailCJ(@RequestParam Map<String,String> allParams) throws Exception {
			System.out.println("it came to the controller---------------"+allParams.toString());
		     return advertiserLookupService.getCommissionDetailCJ(allParams);
		}
	}
