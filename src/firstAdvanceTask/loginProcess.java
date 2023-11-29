package firstAdvanceTask;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

public class loginProcess extends Parameter {
	@BeforeTest
	public void setup() {
		driver.get(WibeURL);
	}

	@Test(priority = 1)
	public void lonigTest() {

		WebElement UserNameLogin = driver.findElement(By.id("user-name"));
		WebElement PasswordLogin = driver.findElement(By.id("password"));

		UserNameLogin.sendKeys(Username_Login);
		PasswordLogin.sendKeys(Password_Login);

		WebElement Login = driver.findElement(By.id("login-button"));
		Login.click();
	}

	@Test(priority = 2)
	public void addToCard() {
		// Lists
		List<WebElement> AddButton = driver.findElements(By.className("btn"));
		List<WebElement> ItemsName = driver.findElements(By.className("inventory_item_name"));
		List<WebElement> ItemsPrice = driver.findElements(By.className("inventory_item_price"));

		for (int i = 0; i < AddButton.size(); i++) {
//Item was not added
			if (ItemsName.get(i).getText().contains("Backpack") || ItemsName.get(i).getText().contains("Fleece")
					|| ItemsName.get(i).getText().contains("Onesie")) {

				String pricewithTax = ItemsPrice.get(i).getText().replace("$", "");
				double PriceAsDouble = Double.parseDouble(pricewithTax);
				double Tax = 0.1;
				double FinalPrice = PriceAsDouble * Tax + PriceAsDouble;
				int IntPrice = (int) FinalPrice;
				if (IntPrice % 2 == 0) {
					System.out.println("This item " + ItemsName.get(i).getText() + " Was'nt added"
							+ " the Original price of this Item is " + ItemsPrice.get(i).getText()
							+ " the final price is Even number and the value of this number " + FinalPrice);
				} else {
					System.out.println("This item " + ItemsName.get(i).getText() + " Was'nt added"
							+ " the Original price of this Item is " + ItemsPrice.get(i).getText()
							+ " the final price is Odd number and the value of this number " + FinalPrice);
				}
				continue;

			} // end if

// Item was added
			else {
				String pricewithTax = ItemsPrice.get(i).getText().replace("$", "");
				double PriceAsDouble = Double.parseDouble(pricewithTax);
				double Tax = 0.10;
				double FinalPrice = PriceAsDouble * Tax + PriceAsDouble;
				AddButton.get(i).click();
				int IntPrice = (int) FinalPrice;
				if (IntPrice % 2 == 0) {
					System.out.println("This item " + ItemsName.get(i).getText() + " Was added"
							+ " the Original price of this Item is " + ItemsPrice.get(i).getText()
							+ " the final price is Even number and the value of this number " + FinalPrice);
				} else {
					System.out.println("This item " + ItemsName.get(i).getText() + " Was added"
							+ " the Original price of this Item is " + ItemsPrice.get(i).getText()
							+ " the final price is Odd number and the value of this number " + FinalPrice);
				}
			} // end else

		} // end for
	}

	@AfterTest
	public void AfterTesting() {
	}

}// end of the class
