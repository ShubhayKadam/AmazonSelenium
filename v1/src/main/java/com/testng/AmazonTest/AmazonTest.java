package com.testng.AmazonTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.testng.AmazonWebpage.v1.AmazonPage;
import com.testng.Utilities.BaseClassPage;

public class AmazonTest extends BaseClassPage{
	@Test(enabled = false)
	public void addMonitorToCart() throws InterruptedException{
		AmazonPage monitor = PageFactory.initElements(driver, AmazonPage.class);
		System.out.println("Inside addMonitorToCart");
		monitor.addMonitor();
	}
	@Test(enabled = false)
	public void addLaptopToCart() throws InterruptedException {
		AmazonPage laptop = PageFactory.initElements(driver, AmazonPage.class);
		System.out.println("Inside addLaptopToCart");
		laptop.addLaptop();
	}
	@Test(enabled=true)
	public void addHeadphonePlusKeyboard() throws InterruptedException{
		AmazonPage headphoneKeyboard = PageFactory.initElements(driver, AmazonPage.class);
		System.out.println("Inside addHeadphonePlusKeyboard");
		headphoneKeyboard.addHeadphonePlusKB();
	}

}
