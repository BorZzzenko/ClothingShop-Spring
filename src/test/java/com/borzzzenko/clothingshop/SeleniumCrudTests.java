package com.borzzzenko.clothingshop;

import com.borzzzenko.clothingshop.model.Clothes;
import com.borzzzenko.clothingshop.service.ClothesService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SeleniumCrudTests {
	@LocalServerPort
	private int port;

	private static WebDriver driver;

	@Autowired
	private ClothesService clothesService;

	@BeforeAll
	static void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@AfterAll
	static void tearDown() {
		driver.quit();
	}

	@BeforeEach
	void goToAdmin(){
		// Go to admin page
		driver.navigate().to("http://localhost:" + port + "/admin");

		int beforeClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();

	}

	@Test
	@Order(1)
	void creatingClothes() {
		int beforeCreatingClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();

		// Adding new clothes
		String clothesName = "Тестовый Selenium WebDriver тест";
		String description = "Selenium WebDriver test";
		String price = "0.0";
		String size = "XL";

		driver.findElement(By.linkText("Добавить")).click();
		driver.findElement(By.id("name")).sendKeys(clothesName);
		driver.findElement(By.id("costInput")).sendKeys(price);
		driver.findElement(By.id("sizeInput")).sendKeys(size);
		driver.findElement(By.id("controlDescriptionTextArea")).sendKeys(description);
		driver.findElement(By.id("submitButton")).sendKeys(Keys.ENTER);

		// Check that clothes cards count increased
		int afterCreatingClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();
		Assertions.assertEquals(beforeCreatingClothesCount + 1, afterCreatingClothesCount);

		// Check that clothes created in DB and on page have correct description
		Clothes createdInDB = clothesService.findByName(clothesName);

		String descriptionFromDB = createdInDB.getDescription();
		String descriptionFromPage = driver.findElement(By.cssSelector(".col:nth-child(" +
				afterCreatingClothesCount + ") .card-text")).getText();

		Assertions.assertEquals(descriptionFromDB, descriptionFromPage);
		Assertions.assertEquals(descriptionFromPage, description);
	}

	@Test
	@Order(2)
	void updateClothes() {
		int ClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();

		// Click Update button
		driver.findElement(By.cssSelector(".col:nth-child(" +
				ClothesCount + ") form:nth-child(1) > .btn")).sendKeys(Keys.ENTER);

		// Updating price field
		String newPrice = "99999.99";

		WebElement priceField = driver.findElement(By.id("costInput"));
		priceField.click();
		priceField.clear();
		priceField.sendKeys(newPrice);

		driver.findElement(By.id("submitButton")).sendKeys(Keys.ENTER);

		// Check that clothes count not changed
		int afterUpdatingClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();
		Assertions.assertEquals(ClothesCount, afterUpdatingClothesCount);

		// Check that field updated on page and in DB
		String updatedClothesName = driver.findElement(By.cssSelector(".col:nth-child(" +
				ClothesCount + ") .card-title")).getText();

		Clothes clothes = clothesService.findByName(updatedClothesName);

		String clothesPriceFromDB = clothes.getPrice().toString();
		String clothesPriceFromPage = driver.findElement(By.cssSelector(".col:nth-child(" +
				ClothesCount + ") .d-flex > .fw-bold")).getText();

		Assertions.assertEquals(clothesPriceFromDB, clothesPriceFromDB);
		Assertions.assertEquals(clothesPriceFromDB, newPrice);
	}

	@Test
	@Order(3)
	void deletionClothes() {
		int beforeDeletionClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();

		// Get clothes name before deletion
		String name = driver.findElement(By.cssSelector(".col:nth-child(" +
				beforeDeletionClothesCount + ") .card-title")).getText();

		// Click deletion button on created clothes card
		driver.findElement(By.xpath("//div[" +
				beforeDeletionClothesCount + "]/div/div/div/div/form[2]/button")).sendKeys(Keys.ENTER);

		// Check that clothes cards count decreased
		int afterDeletionClothesCount = driver.findElements(By.xpath("//div/div/h5")).size();
		Assertions.assertNotEquals(afterDeletionClothesCount, beforeDeletionClothesCount);

		// Check that clothes with that name doesn't exist in DB
		boolean isExist = clothesService.existsByName(name);
		Assertions.assertFalse(isExist);
	}

}
