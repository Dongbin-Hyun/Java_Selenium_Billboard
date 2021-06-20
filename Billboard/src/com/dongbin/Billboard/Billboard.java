package com.dongbin.Billboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Billboard {

	public static void main(String[] args) {
		Billboard billboard = new Billboard();
		billboard.crawl();

	}
	//WebDriver
	private WebDriver driver;

	//ChromeOptions
	private ChromeOptions options;

	//Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "/opt/homebrew/Caskroom/chromedriver/91.0.4472.101/chromedriver";
	
	//MelonURL
	private String billboardURL = "https://www.billboard.com/";
	
	public Billboard() {
		//super();
		options = new ChromeOptions();
		options.addArguments("headless");
		//options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		//options.addArguments("--disable-default-apps"); 

		//System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		//Driver SetUp
		driver = new ChromeDriver(options);

	}

	public void crawl() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");

		try {
			driver.get(billboardURL);
			Thread.sleep(100);
			driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div/div/div[2]/div[2]/a")).click();
			List<WebElement> thisWeekRankElement = driver.findElements(By.className("chart-element__rank__number"));
			List<WebElement> titleElement = driver.findElements(By.className("chart-element__information__song"));
			List<WebElement> singerElement = driver.findElements(By.className("chart-element__information__artist"));
			List<WebElement> lastWeekRankElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--last"));
			List<WebElement> peakRankElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--peak"));
			List<WebElement> weeksOnChartElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--week"));
			Thread.sleep(5000);
			
			System.out.println("Billboard HOT 100");
			System.out.println("Now : " + sdf.format(cal.getTime()));
			System.out.println("----------------------------------------");
			for (int i = 0; i < thisWeekRankElement.size() - 1; i ++) {
				System.out.println("This week : " + thisWeekRankElement.get(i).getAttribute("textContent"));
				System.out.println("Title : " + titleElement.get(i).getAttribute("textContent"));
				System.out.println("Singer : " + singerElement.get(i).getAttribute("textContent"));
				System.out.println("Last Week : " + lastWeekRankElement.get(i).getAttribute("innerText"));
				System.out.println("Peak : " + peakRankElement.get(i).getAttribute("innerText"));
				System.out.println("Weeks on chart : " + weeksOnChartElement.get(i).getAttribute("innerText"));
				System.out.println("----------------------------------------");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}	
}