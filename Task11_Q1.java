package sele1;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task11_Q1 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
		
		String ParentWindow = driver.getWindowHandle();
		
		WebElement clickwin = driver.findElement(By.xpath("//a[text()='Click Here']"));
		clickwin.click();
		
		Thread.sleep(2000);
		
		Set<String> allwindows = driver.getWindowHandles();
		
		for(String NewWindow : allwindows) {
			if(!NewWindow.equals(ParentWindow)) {
				driver.switchTo().window(NewWindow);
				
				WebElement textpage = driver.findElement(By.xpath("//h3[text()='New Window']"));
				System.out.println(textpage.getText());
				break;
			}
		}
		
        driver.close();
        driver.switchTo().window(ParentWindow);
        
        System.out.println(driver.getCurrentUrl());

        driver.quit();;

	}

}
