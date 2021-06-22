package com.dongbin.Billboard;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
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
	public static final String WEB_DRIVER_PATH = "C:\\Users\\kopo44\\Downloads\\chromedriver_win32\\chromedriver.exe";
	
	//MelonURL
	private String billboardURL = "https://www.billboard.com/";
	
	public Billboard() {
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

	public void crawl() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss");
		String writeFileName = "C:\\Users\\kopo44\\Documents\\DataAna\\Billboard\\billboard.csv";

		try {
			driver.get(billboardURL);
			driver.findElement(By.xpath("//*[@id=\"main-content\"]/div[2]/div/div/div[2]/div[2]/a")).click();
			String thisWeek = driver.findElement(By.cssSelector("#charts > div > div.date-selector.container > div > div.date-selector__text.color--primary.display--flex.flex--xy-center > button")).getAttribute("textContent");
			List<WebElement> thisWeekRankElement = driver.findElements(By.className("chart-element__rank__number"));
			List<WebElement> titleElement = driver.findElements(By.className("chart-element__information__song"));
			List<WebElement> singerElement = driver.findElements(By.className("chart-element__information__artist"));
			List<WebElement> lastWeekRankElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--last"));
			List<WebElement> peakRankElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--peak"));
			List<WebElement> weeksOnChartElement = driver.findElements(By.cssSelector(".chart-element__metas--large .chart-element__meta.text--week"));
			Thread.sleep(5000);
			
			System.out.println("Billboard HOT 100");
			System.out.println("Now" + thisWeek);
			System.out.println("----------------------------------------");
			for (int i = 0; i < thisWeekRankElement.size() - 1; i ++) {
				System.out.println("This week : " + thisWeekRankElement.get(i).getAttribute("textContent"));
				System.out.println("Title : " + titleElement.get(i).getAttribute("textContent"));
				System.out.println("Singer : " + singerElement.get(i).getAttribute("textContent"));
				System.out.println("Last week : " + lastWeekRankElement.get(i).getAttribute("textContent"));
				System.out.println("Peak : " + peakRankElement.get(i).getAttribute("textContent"));
				System.out.println("Weeks on chart : " + weeksOnChartElement.get(i).getAttribute("textContent"));
				System.out.println("----------------------------------------");
			}
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFileName, true), "CP949"));
			String cover = "Billboard Top 100 chart";
			bw.write(cover);
			bw.newLine();
			bw.write(thisWeek.replace(",", ""));
			bw.newLine();
			String header = "Ranking," + "Title," + "Singer," + "Last week," + "Peak," + "Weeks on chart";
			bw.write(header);
			bw.newLine();
			for (int i = 0; i < thisWeekRankElement.size() - 1; i ++) {
				String context = thisWeekRankElement.get(i).getAttribute("textContent") + "," + titleElement.get(i).getAttribute("textContent") + ","
						+ singerElement.get(i).getAttribute("textContent").replace(",", "") + "," +  lastWeekRankElement.get(i).getAttribute("textContent") + "," + peakRankElement.get(i).getAttribute("textContent")
						+ "," + weeksOnChartElement.get(i).getAttribute("textContent");
				bw.write(context);
				bw.newLine();
			}
			bw.flush();
			bw.close();
			System.out.println("Writing process is finished.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}	
}