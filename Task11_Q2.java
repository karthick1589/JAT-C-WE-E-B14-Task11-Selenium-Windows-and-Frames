package sele1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task11_Q2 {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/nested_frames");
		driver.manage().window().maximize();

		WebElement topframe = driver.findElement(By.xpath("//frame[@name='frame-top']"));

		String topname = topframe.getAttribute("name");
		System.out.println("Switched To: " + topname);
		System.out.println("------");

		driver.switchTo().frame(topframe);

		List<WebElement> innerFrames = driver.findElements(By.xpath(".//frame"));

		if (innerFrames.size() == 3) {
			System.out.println("Verifed there are three frames availble in Top Frame");
		} else {
			System.out.println("Expected 3 Frames but found: " + innerFrames.size());
		}
		System.out.println("------");

		for (WebElement frame : innerFrames) {
			String frameName = frame.getAttribute("name");
			System.out.println("Switched to Frame: " + frameName);

			driver.switchTo().frame(frameName);

			WebElement body = driver.findElement(By.xpath("//body"));
			String Text = body.getText();

			if (!Text.isEmpty()) {
				System.out.println(frameName + " Contains a Text: " + Text);
			} else {
				System.out.println(frameName + " has no visible Text");
			}

			driver.switchTo().parentFrame();
			System.out.println("Returned to Top Frame after visiting " + topname);
			System.out.println("------");
		}
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-bottom']")));
		System.out.println("Switched to Frame: frame-bottom");
				
		String bottomText = driver.findElement(By.xpath("//body")).getText().trim();

		if (bottomText.equalsIgnoreCase("BOTTOM")) {
			System.out.println("frame-bottom Contains a Text: " + bottomText);
		} else {
			System.out.println("Unexpected text found in " + ": " + bottomText);
		}

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='frame-top']")));
		System.out.println("Returned to Top Frame after visiting " + topname);
		System.out.println("------");

		//driver.quit();
		

	}
}
