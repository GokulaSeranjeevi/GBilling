package com.skywings.gbilling.form;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class CreateJsonFile {

	// Method to create JSON file from the Invoice object
	public void createInvoiceJson() {
		// Create an Invoice object and set data
		Invoice invoice = new Invoice();
		invoice.setVersion("1.1");

		// Create TranDtls, DocDtls, SellerDtls, BuyerDtls, ItemList, and ValDtls
		Invoice.TranDtls tranDtls = new Invoice.TranDtls();
		tranDtls.setTaxSch("GST");
		tranDtls.setSupTyp("B2B");
		tranDtls.setRegRev("N");
		tranDtls.setEcmGstin(null);
		tranDtls.setIgstOnIntra("N");
		invoice.setTranDtls(tranDtls);

		Invoice.DocDtls docDtls = new Invoice.DocDtls();
		docDtls.setTyp("INV");
		docDtls.setNo("PSM25SA/105909");
		docDtls.setDt("28/02/2025");
		invoice.setDocDtls(docDtls);

		// Create ObjectMapper to convert Java object to JSON
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			// Write the object to a JSON file
			objectMapper.writeValue(new File("invoice.json"), invoice);
			System.out.println("JSON file created successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
