package org.dongbin.naver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.dongbin.Billboard.Billboard;

public class NaverNews {

	public static void main(String[] args) throws InterruptedException {
		NaverNews news = new NaverNews();
		news.crawl();

	}
	//WebDriver
	private WebDriver driver;

	//ChromeOptions
	private ChromeOptions options;

	//Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:\\Users\\kopo44\\Downloads\\chromedriver_win32\\chromedriver.exe";

	//MelonURL
	private String naverURL = "https://www.naver.com/";

	public NaverNews() {
		options = new ChromeOptions();
		//options.addArguments("headless");
		options.addArguments("--start-maximized");
		options.addArguments("--disable-popup-blocking");
		//options.addArguments("--disable-default-apps"); 

		//System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

		//Driver SetUp
		driver = new ChromeDriver(options);

	}

	public void crawl() throws InterruptedException {
		try {
			driver.get(naverURL);
			driver.findElement(By.xpath("//*[@id=\"NM_FAVORITE\"]/div[1]/ul[2]/li[2]/a")).click();
			List<WebElement> newsHeader = driver.findElements(By.className("hdline_article_tit"));
			Thread.sleep(5000);

			for (int i = 0; i < newsHeader.size(); i ++) {
				System.out.println("뉴스기사 " + i + "번 : " + newsHeader.get(i).getText());
			}	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error");
		} finally {
			System.out.println("Finish");
			driver.close();
		}


	}
}
