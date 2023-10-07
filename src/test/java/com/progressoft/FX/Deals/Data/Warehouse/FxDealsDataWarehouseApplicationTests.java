package com.progressoft.FX.Deals.Data.Warehouse;

import com.progressoft.FX.Deals.Data.Warehouse.dto.FXDealDto;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.DealAlreadyExistsException;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.InvalidCurrencyCodeException;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.NoDealsFoundException;
import com.progressoft.FX.Deals.Data.Warehouse.exceptions.SameCurrenciesException;
import com.progressoft.FX.Deals.Data.Warehouse.service.FXDealService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(properties = "spring.profiles.active=test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FxDealsDataWarehouseApplicationTests {

	@Autowired
	FXDealService fxDealService;

	@Test
	@Order(1)
	public void testNoDealsFound() {
		assertThrows(NoDealsFoundException.class, () -> {
			fxDealService.getAllDeals();
		});
	}

	@Test
	@Order(2)
	public void testValidFxDeal() {
		FXDealDto validFxDeal = FXDealDto.builder()
				.dealId(1)
				.fromCurrency("USD")
				.toCurrency("JOD")
				.dealTimestamp("2023-10-04T12:34:56")
				.dealAmount(50.25)
				.build();
		HttpStatus status = fxDealService.saveDeal(validFxDeal).getStatusCode();
		assertEquals(status, HttpStatus.CREATED);
	}

	@Test
	@Order(3)
	public void testDuplicatedFxDeal() {
		FXDealDto validFxDeal = FXDealDto.builder()
				.dealId(1)
				.fromCurrency("USD")
				.toCurrency("JOD")
				.dealTimestamp("2023-10-04T12:34:56")
				.dealAmount(50.25)
				.build();
		assertThrows(DealAlreadyExistsException.class, () -> {
				fxDealService.saveDeal(validFxDeal);
		});
	}

	@Test
	@Order(4)
	public void testInvalidCurrencyCode() {
		FXDealDto validFxDeal = FXDealDto.builder()
				.dealId(2)
				.fromCurrency("eqeqweq") //wrong ISO code entry
				.toCurrency("JOD")
				.dealTimestamp("2023-10-04T12:34:56")
				.dealAmount(50.25)
				.build();
		assertThrows(InvalidCurrencyCodeException.class, () -> {
			fxDealService.saveDeal(validFxDeal);
		});
	}

	@Test
	@Order(5)
	public void testDuplicatedCurrencies() {
		FXDealDto validFxDeal = FXDealDto.builder()
				.dealId(3)
				.fromCurrency("JOD")
				.toCurrency("JOD") //from and to currencies have the same ISO code
				.dealTimestamp("2023-10-04T12:34:56")
				.dealAmount(50.25)
				.build();
		assertThrows(SameCurrenciesException.class, () -> {
			fxDealService.saveDeal(validFxDeal);
		});
	}

	@Test
	@Order(6)
	public void testDealIdNotFound() {
		assertThrows(NoDealsFoundException.class, () -> {
			fxDealService.getDealByID(4);
		});
	}

	@Test
	@Order(7)
	public void testValidGetAllDeals() {
		HttpStatus status = fxDealService.getAllDeals().getStatusCode();
		assertEquals(status, HttpStatus.OK);
	}

	@Test
	@Order(8)
	public void testValidGetDealById() {
		HttpStatus status = fxDealService.getDealByID(1).getStatusCode();
		assertEquals(status, HttpStatus.OK);
	}
}
