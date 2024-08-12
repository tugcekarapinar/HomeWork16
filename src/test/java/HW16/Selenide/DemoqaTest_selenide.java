package HW16.Selenide;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class DemoqaTest_selenide {
    private ElementsPage elementsPage;
    private WebTablesPage webTablesPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver();
        Configuration.timeout = 1000;
        elementsPage = new ElementsPage();
        webTablesPage = new WebTablesPage();
    }

    @Test(priority = 0)
    public void testClickButton() {
        elementsPage.OpenPage();
        elementsPage.Buttons().click();
        elementsPage.ClickButton().click();
        Assert.assertEquals(elementsPage.DynamicClickMessage().text(), "You have done a dynamic click");
    }

    @Test(priority = 1)
    public void testDoubleClickButton() {
        elementsPage.OpenPage();
        elementsPage.Buttons().click();
        elementsPage.DoubleClickButton().doubleClick();
        Assert.assertEquals(elementsPage.DoubleClickMessage().text(), "You have done a double click");
    }


    @Test(priority = 2)
    public void testAddEndEditRecord(){
        webTablesPage.OpenPage();
        webTablesPage.AddButton().click();
        webTablesPage.FirstNameField().setValue("Demir");
        webTablesPage.LastNameField().setValue("Karapinar");
        webTablesPage.AgeField().setValue("0");
        webTablesPage.EmailField().setValue("demir@gmail.com");
        webTablesPage.SalaryField().setValue("100000");
        webTablesPage.DepartmentField().setValue("bebek");
        webTablesPage.SubmitButton().click();

        webTablesPage.AddButton().scrollTo();
        webTablesPage.EditButton("Demir").click();
        webTablesPage.SalaryField().clear();
        webTablesPage.SalaryField().setValue("120000");
        webTablesPage.SubmitButton().click();

        Assert.assertEquals(webTablesPage.SalaryCell("Demir").text(),"120000");
    }
}