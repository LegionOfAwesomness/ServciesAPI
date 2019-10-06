package com.project.shoponline.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xml.sax.SAXException;

import com.project.shoponline.dao.CommisonAndReward.LinkSearchLSRepo;
import com.project.shoponline.model.module3.Advertiser;
import com.project.shoponline.model.module3.Advertisers;
import com.project.shoponline.model.module3.CJPI;
import com.project.shoponline.model.module3.Link;
import com.project.shoponline.model.module3.Links;
import com.project.shoponline.model.module4.AdvancedReportResponse;
import com.project.shoponline.model.module4.CommissionDetail;
import com.project.shoponline.model.module4.Couponfeed;
import com.project.shoponline.model.module4.Items;
import com.project.shoponline.model.module4.LSLinkLocatorResponse;
import com.project.shoponline.model.module4.PaymentHistorySummary;
import com.project.shoponline.model.module4.PublisherCommissions;
import com.project.shoponline.model.module4.Records;

@Service
public class AdvertiserLookupService {
	@Autowired
	AdvertiserLookupRepo advertiserLookupRepo;
	
	@Autowired
	LinkSearchLSRepo linkSearchLSRepo;
	
	@Autowired
	LinkSearchRepo linkSearchRepo;
	@Autowired
	RestTemplate restTemplate;

	// public AdvertiserLookupService() {
	// restTemplate = new RestTemplate();
	// }
	public CJPI saveAdvertiserLookupData(CJPI cJPIData) throws Exception {
		for (Advertiser ad : cJPIData.getAdvertisers().getAdvertiserList()) {
			advertiserLookupRepo.save(ad);
		}
		return null;
	}
	//all advertisers 
	public List<Link> getAllLinksForAllAdvertisersFromCJ() {
//		System.err.println(linkSearchRepo.findAllByLinkType("Banner"));
//		return linkSearchRepo.findAllByLinkType("Banner");Iterable
		
		Link link;
		HashMap<String,Link> advertiseWithLinks = new HashMap<String, Link>();
		Iterator<Link> iter = linkSearchRepo.findAll().iterator();
		while(iter.hasNext()){
			
			link = iter.next();
			if(link.getLink_type().equals("Banner")) {
			advertiseWithLinks.put(link.getAdvertiserId(), link);
			}
		}
		//System.err.println(advertiseWithLinks.values());
		return new ArrayList<Link>(advertiseWithLinks.values());
	}
	public List<Link> getLinkForAdvertisersCJ(String advertiserId) {
		return linkSearchRepo.findByAdvertiserIdAndLinkType(advertiserId, "Banner");
	}
	public LSLinkLocatorResponse saveAllLinksForAllAdvertisers(CJPI cJPIData) throws Exception {
		for (Link lnk : cJPIData.getLinks().getLinkList()) {
			linkSearchRepo.save(lnk);
		}
		return null;
	}
	public CJPI saveCouponsForAllAdvertisers(CJPI cJPIData) throws Exception {
		for (Link lnk : cJPIData.getLinks().getLinkList()) {
			linkSearchRepo.save(lnk);
		}
		return null;
	}
	
	public List<Link>getCouponsForAdvertiser(String advertiserId){
		return linkSearchRepo.findAllByAdvertiserId(advertiserId);
	}
	//all coupons
	public Iterable<Link> getCouponsForAllAdvertisersCJ(){
		return linkSearchRepo.findAll();
	}
	public CJPI getCJS() throws IOException, JAXBException, SAXException, ParserConfigurationException, Exception {
		ResponseEntity<String> aa1 = makeACallToCJDeveloper(1);
		ResponseEntity<String> aa2 = makeACallToCJDeveloper(2);
		ResponseEntity<String> aa3 = makeACallToCJDeveloper(3);
		ResponseEntity<String> aa4 = makeACallToCJDeveloper(4);
		ResponseEntity<String> aa5 = makeACallToCJDeveloper(5);
// make it to loop once it check the pages
		CJPI cjpi1 = changeXmltoModel(aa1);
		CJPI cjpi2 = changeXmltoModel(aa2);
		CJPI cjpi3 = changeXmltoModel(aa3);
		CJPI cjpi4 = changeXmltoModel(aa4);
		CJPI cjpi5 = changeXmltoModel(aa5);
		CJPI cjpiAggregate = new CJPI();
		Advertisers advertisers = new Advertisers();
		List<Advertiser> advertiserList = new ArrayList<>();
		cjpi1.getAdvertisers().getAdvertiserList().stream().forEach(y -> advertiserList.add(y));
		cjpi2.getAdvertisers().getAdvertiserList().stream().forEach(y -> advertiserList.add(y));
		cjpi3.getAdvertisers().getAdvertiserList().stream().forEach(y -> advertiserList.add(y));
		cjpi4.getAdvertisers().getAdvertiserList().stream().forEach(y -> advertiserList.add(y));
		cjpi5.getAdvertisers().getAdvertiserList().stream().forEach(y -> advertiserList.add(y));
		advertisers.setAdvertiserList(advertiserList);
		cjpiAggregate.setAdvertisers(advertisers);
		return cjpiAggregate;

	}

	public ResponseEntity<String> makeACallToCJDeveloper(int pageNum) throws JSONException {
//		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://advertiser-lookup.api.cj.com/v2/advertiser-lookup?requestor-cid=2446749&advertiser-ids=joined")
				.queryParam("records-per-page", 100).queryParam("page-number", pageNum);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return bc;
	}

	public CJPI changeXmltoModel(ResponseEntity<String> aa) throws JAXBException {
		StringReader reader = new StringReader(aa.getBody());
		// initialize jaxb classes
		JAXBContext context = JAXBContext.newInstance(CJPI.class);
		Unmarshaller un = context.createUnmarshaller();
		// convert to desired object
		CJPI cjpi = (CJPI) un.unmarshal(reader);
		return cjpi;
	}

	public List<Advertiser> getCJDeveloperFromDB() {
		return advertiserLookupRepo.findAll();
	}
	
	public List<Long> getListOfAdvertisersID(){
		return advertiserLookupRepo.findAll().stream().map(Advertiser::getAdvertiser_id).collect(Collectors.toList());
	}
	
	public CJPI getCouponsForAllAdvertisersFromCJ() throws IOException, JAXBException, SAXException, ParserConfigurationException, Exception {
//		List<Long> listofAdvertisersID = getListOfAdvertisersID();
		CJPI cjpiAggregate = new CJPI();
		Links links = new Links();
		List<Link> linkList = new ArrayList<>();
		for(int i=1;i<22;i++) {
			CJPI cjpii = changeXmltoModel(makeCJCallLinkSearchForCoupon(i));
			
			cjpii.getLinks().getLinkList().stream().forEach(y -> linkList.add(y)); 
		}
		links.setLinkList(linkList);
		cjpiAggregate.setLinks(links);
		System.out.println("----response---"+cjpiAggregate.toString());
		return cjpiAggregate;
	}
	
	
	public CJPI makeACallToLinkSearchServiceCJ() throws IOException, JAXBException, SAXException, ParserConfigurationException, Exception {
//		List<Long> listofAdvertisersID = getListOfAdvertisersID();
		CJPI cjpiAggregate = new CJPI();
		Links links = new Links();
		List<Link> linkList = new ArrayList<>();
//		for(int i=1;i<22;i++) {
			CJPI cjpii = changeXmltoModel(makeACallToLinkSearchServiceCJ(1));
			
			cjpii.getLinks().getLinkList().stream().forEach(y -> linkList.add(y)); 
//		}
		links.setLinkList(linkList);
		cjpiAggregate.setLinks(links);
		System.out.println("----response---"+cjpiAggregate.toString());
		return cjpiAggregate;
	}
	
	public CJPI getBannersForAllAdvertisersFromCJ() throws IOException, JAXBException, SAXException, ParserConfigurationException, Exception {
		ResponseEntity<String> firstCall = makeCJCallLinkSearchForBannerFirstCall();
		System.out.println("first call "+firstCall.toString());
		CJPI cjpiAggregate = new CJPI();
		Links links = new Links();
		List<Link> linkList = new ArrayList<>();
		for(int i=1;i<190;i++) {
			CJPI cjpii = changeXmltoModel(makeCJCallLinkSearchForBanner(i));
			
			cjpii.getLinks().getLinkList().stream().forEach(y -> linkList.add(y)); 
		}
		links.setLinkList(linkList);
		cjpiAggregate.setLinks(links);
		System.out.println("----response---"+cjpiAggregate.getLinks().getLinkList().size());
		return cjpiAggregate;
	}
	
	
	public ResponseEntity<String> makeCJCallLinkSearchForCoupon(int pageNum) throws JSONException {
//		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://link-search.api.cj.com/v2/link-search?website-id=7266456&promotion-type=coupon&advertiser-ids=joined")
				.queryParam("records-per-page", 100).queryParam("page-number", pageNum);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return bc;
	}
	//@
	public ResponseEntity<String> makeCJCallLinkSearchForBannerFirstCall() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://link-search.api.cj.com/v2/link-search?website-id=7266456&link-type=banner&advertiser-ids=joined")
				.queryParam("records-per-page", 100).queryParam("page-number", 1);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return bc;
//	"https://link-search.api.cj.com/v2/link-search?website-id=7266456&promotion-type=coupon&advertiser-ids=joined")
	}
	public ResponseEntity<String> makeCJCallLinkSearchForBanner(int pageNum) throws JSONException {
//		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://link-search.api.cj.com/v2/link-search?website-id=7266456&link-type=banner&advertiser-ids=joined")
				.queryParam("records-per-page", 100).queryParam("page-number", pageNum);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return bc;
//	"https://link-search.api.cj.com/v2/link-search?website-id=7266456&promotion-type=coupon&advertiser-ids=joined")
	}
	public ResponseEntity<String> makeACallToLinkSearchServiceCJ(int pageNum) throws JSONException {
//		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://link-search.api.cj.com/v2/link-search?website-id=7266456&advertiser-ids=joined")
				.queryParam("records-per-page", 100).queryParam("page-number", pageNum);
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		return bc;
//	"https://link-search.api.cj.com/v2/link-search?website-id=7266456&promotion-type=coupon&advertiser-ids=joined")
	}
	
	//--------------------
	
	public LSLinkLocatorResponse  getLSFiles() throws JSONException, JAXBException {
		ResponseEntity<String> lsfiles =makeCallToLSDeveloperLinkLocatore();
		System.out.println("lssss"+lsfiles.toString());
		LSLinkLocatorResponse lsLinkLocatorResponse = changeLSXmltoLSLinkLocatorResponse(lsfiles);
		return lsLinkLocatorResponse;
	}
//	
//	public LSFile getLSFilesFromDB() {}
	
	
//	public LSCoupons getCouponsForAllAdvertisersLS(@PathVariable String advertiserId) {}
	
	public Couponfeed getCouponsForAllAdvertisersLS() throws JSONException, JAXBException, InterruptedException {
		ResponseEntity<String> aa1 = makeACallToLSDeveloper();
//		Couponfeed couponfeed = changeLSXmltoCouponfeed(aa1);
//		int totalMatches=0;
//		if(null!=couponfeed) {
//			totalMatches = (int) couponfeed.getTotalMatches();
//		}
//		for(int i=0;i<5;i++) {
//		TimeUnit.SECONDS.sleep(5);
//			makeACallToLSDeveloperasasa(500,1);
			System.out.println("count+  "+1);
//			makeACallToLSDeveloper(500,2);
//			System.out.println("count+  "+2);
//			makeACallToLSDeveloper(500,3);
//			System.out.println("count+  "+3);
//			TimeUnit.SECONDS.sleep(10);
//			
//		}
//		System.out.println("Service^^^^^^"+couponfeed.toString());
			Couponfeed couponfeed = changeLSXmltoCouponfeed(makeACallToLSDeveloperasasa(500,1));
		return couponfeed;
	}

	public ResponseEntity<String> makeACallToLSDeveloper() throws JSONException {
//		restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer ac418f61b055f274c055784e3eb2464");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://api.rakutenmarketing.com/linklocator/1.0/getBannerLinks/-1/-1///-1/-1/2");
//				.queryParam("resultsperpage", resultsperpage).queryParam("pagenumber", pagenumber);
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		System.out.println("response************"+bc.toString());
		return bc;
	}
	public ResponseEntity<String> makeACallToLSDeveloperasasa(int resultsperpage, int pagenumber) throws JSONException {
//		restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer c3eab895b797747c3ad5a8afdf7217c");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://api.rakutenmarketing.com/linklocator/1.0/getBannerLinks/-1/-1///-1/-1/1")
				.queryParam("resultsperpage", resultsperpage).queryParam("pagenumber", pagenumber);
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		System.out.println("response************"+bc.toString());
		return bc;
	}
	public Couponfeed changeLSXmltoCouponfeed(ResponseEntity<String> aa) throws JAXBException {
		StringReader reader = new StringReader(aa.getBody());
		// initialize jaxb classes
		JAXBContext context = JAXBContext.newInstance(Couponfeed.class);
		Unmarshaller un = context.createUnmarshaller();
		// convert to desired object
		Couponfeed couponfeed = (Couponfeed) un.unmarshal(reader);
		return couponfeed;
	}
	public LSLinkLocatorResponse changeLSXmltoLSLinkLocatorResponse(ResponseEntity<String> bb) throws JAXBException {
		StringReader reader = new StringReader(bb.getBody());
		// initialize jaxb classes
		JAXBContext context = JAXBContext.newInstance(LSLinkLocatorResponse.class);
		Unmarshaller un = context.createUnmarshaller();
		// convert to desired object
		LSLinkLocatorResponse lsLinkLocatorResponse = (LSLinkLocatorResponse) un.unmarshal(reader);
		return lsLinkLocatorResponse;
	}
	
	
	public ResponseEntity<String> makeCallToLSDeveloperLinkLocatore() throws JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer 5329a98bf124d4ede97baccae6bab82");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://api.rakutenmarketing.com/linklocator/1.0/getMerchByAppStatus/approved");
		HttpEntity<String> entity = new HttpEntity<String>("Hello World!", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		System.out.println("response************"+bc.toString());
		return bc;
		
	}
	
	public AdvancedReportResponse getadvancedreportsLS(Map<String,String> allParams){
		HttpHeaders headers = new HttpHeaders();
		headers.set("authorization", "Bearer 2e7c14ce4a1465541f514dd8a93c97bb");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://api.rakutenmarketing.com/advancedreports/1.0/?bdate=20160101&edate=20170101&token=f06fbca27944345df1b8e13f2b91ca3c0d48e5e60acd5555c685b37252d0547b&reportid=1");
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		List<PaymentHistorySummary> paymentHistorySummaryList= new ArrayList();
		PaymentHistorySummary paymentHistory= new PaymentHistorySummary();
		paymentHistory.setAmountPaid("883463");
		paymentHistory.setCheckNumber("NA");
		paymentHistory.setCurrencyCode("USD");
		paymentHistory.setDate("4/19/2012");
		paymentHistory.setPaymentID("883463");
		paymentHistory.setPaymentStatus("Issued");
		paymentHistory.setPaymentType("Direct Deposit");
		paymentHistory.setTotalCommission("568.24");
		paymentHistorySummaryList.add(paymentHistory);
		AdvancedReportResponse advancedReportResponse= new AdvancedReportResponse();
		advancedReportResponse.setPaymentHistorySummary(paymentHistorySummaryList);
		return advancedReportResponse;
		
	}
	public CommissionDetail getCommissionDetailCJ(Map<String,String> allParams){
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer 6vpfyx74zycmpepe3n6vkgvdby");
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
				"https://commissions.api.cj.com/query");
				
		HttpEntity<String> entity = new HttpEntity<String>("{ publisherCommissions(forPublishers: [\"2446749\"], sincePostingDate:\"2018-08-08T00:00:00Z\",beforePostingDate:\"2018-08-09T00:00:00Z\"){count payloadComplete records {actionTrackerName websiteName advertiserName postingDate pubCommissionAmountUsd items { quantity perItemSaleAmountPubCurrency totalCommissionPubCurrency }  }  } }", headers);
		ResponseEntity<String> bc = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
		System.out.println("response************"+bc.toString());
		CommissionDetail commissionDetail= new CommissionDetail();
		PublisherCommissions publisherCommissions= new PublisherCommissions();
		List<Records> records= new ArrayList();
		List<Items> items= new ArrayList();
		Items item= new Items();
		Records record = new Records();
		publisherCommissions.setCount(2);
		publisherCommissions.setPayloadComplete(true);
		record.setActionTrackerName("Advanced Sale Test");
		record.setAdvertiserName("Visit Canada");
		record.setCommissionId("2292067663");
		record.setPostingDate("2018-05-03T22:31:48Z");
		record.setPubCommissionAmountUsd("7.781");
		record.setWebsiteName("Donuts and Denim");
		item.setPerItemSaleAmountPubCurrency("75.474");
		item.setQuantity(1);
		item.setTotalCommissionPubCurrency("7.548");
		items.add(item);
		record.setItems(items);		
		records.add(record);
		publisherCommissions.setRecords(records);
		commissionDetail.setPublisherCommissions(publisherCommissions);
		return commissionDetail;
	}
}
