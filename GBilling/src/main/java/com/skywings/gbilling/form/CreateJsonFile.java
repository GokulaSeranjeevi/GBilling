package com.skywings.gbilling.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

@Component
public class CreateJsonFile {

	// Method to create JSON file from the Invoice object
	public void createInvoiceJson() {
		// Create an Invoice object and set data
		Invoice invoice = new Invoice();
		invoice.setVersion("1.1");

		// Create TranDtls and set values
		Invoice.TranDtls tranDtls = new Invoice.TranDtls();
		tranDtls.setTaxSch("GST");
		tranDtls.setSupTyp("B2B");
		tranDtls.setRegRev("N");
		tranDtls.setEcmGstin(null);
		tranDtls.setIgstOnIntra("N");
		invoice.setTranDtls(tranDtls);

		// Create DocDtls and set values
		Invoice.DocDtls docDtls = new Invoice.DocDtls();
		docDtls.setTyp("INV");
		docDtls.setNo("PSM25SA/105909");
		docDtls.setDt("28/02/2025");
		invoice.setDocDtls(docDtls);

		// Create SellerDtls and set values
		Invoice.SellerDtls sellerDtls = new Invoice.SellerDtls();
		sellerDtls.setGstin("33ABFCS7101P1Z5");
		sellerDtls.setLglNm("SREE SENTHOOR MURUGAN TRADERS");
		sellerDtls.setTrdNm("SREE SENTHOOR MURUGAN TRADERS");
		sellerDtls.setAddr1("407/7,GROUND FLOOR & 1ST FLOOR,");
		sellerDtls.setAddr2("POTHYS GST ROAD,");
		sellerDtls.setLoc("CHENNAI");
		sellerDtls.setPin("600044");
		sellerDtls.setStcd("33");
		sellerDtls.setPh("4440747576");
		invoice.setSellerDtls(sellerDtls);

		// Create BuyerDtls and set values
		Invoice.BuyerDtls buyerDtls = new Invoice.BuyerDtls();
		buyerDtls.setGstin("24AAWCA3140L1ZG");
		buyerDtls.setLglNm("ANUBHUTI METCOM PRIVATE LIMITED");
		buyerDtls.setTrdNm("ANUBHUTI METCOM PRIVATE LIMITED");
		buyerDtls.setAddr1("SOPAN KESAR IND HUB");
		buyerDtls.setAddr2("addr2");
		buyerDtls.setLoc("AHMEDABAD");
		buyerDtls.setPin("382213");
		buyerDtls.setStcd("24");
		buyerDtls.setPh("9000000001");
		buyerDtls.setPos("24");
		invoice.setBuyerDtls(buyerDtls);

		// Create ItemList and set values
		List<Invoice.Item> itemList = new ArrayList<>();

		// Adding sample items to the item list
		Invoice.Item item1 = new Invoice.Item();
		item1.setSlNo("1");
		item1.setIsServc("N");
		item1.setPrdDesc("ANTIQUE");
		item1.setHsnCd("711319");
		item1.setBarcde("POT25K32096");
		item1.setQty(120.91);
		item1.setFreeQty(1.0);
		item1.setUnit("GMS");
		item1.setUnitPrice(7950.0);
		item1.setTotAmt(1024676.92);
		item1.setDiscount(0.0);
		item1.setPreTaxVal(1024676.92);
		item1.setAssAmt(1024676.92);
		item1.setGstRt(3.0);
		item1.setIgstAmt(30740.31);
		item1.setTotItemVal(1055581.48);
		item1.setOrgCntry("IN");
		itemList.add(item1);

		// Add more items similarly if needed
		invoice.setItemList(itemList);

		// Create ValDtls and set values
		Invoice.ValDtls valDtls = new Invoice.ValDtls();
		valDtls.setAssVal(1.271952707E7);
		valDtls.setCgstVal(0.0);
		valDtls.setSgstVal(0.0);
		valDtls.setIgstVal(381585.82);
		valDtls.setCesVal(0.0);
		valDtls.setStCesVal(0.0);
		valDtls.setDiscount(0.0);
		valDtls.setOthChrg(0.0);
		valDtls.setRndOffAmt(0.11);
		valDtls.setTotInvVal(1.3101113E7);
		valDtls.setTotInvValFc(1.310111289E7);
		invoice.setValDtls(valDtls);

		// Create ObjectMapper to convert Java object to JSON
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Write the object to a JSON file
			objectMapper.writeValue(new File("invoice.json"), invoice);
			JOptionPane.showMessageDialog(null, "JSON file created successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
