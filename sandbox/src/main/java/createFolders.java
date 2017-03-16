import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class createFolders {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        init();
        goToLoginPage();
        login("admin", "");
    }

    private void init() {
        System.setProperty("webdriver.gecko.driver","D:\\JavaTools\\geckodriver.exe");
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @Test
    public void createFolders() {
        goToShare();
        createFolder();
        goToFolder();
    }

    private void goToFolder() {
        new WebDriverWait(wd,5).until(ExpectedConditions.elementToBeClickable(By.
                xpath("//div[9]/div[1]/div[2]/div[3]/div[1]/div/div/div/div[2]/div[12]/table/tbody[2]/tr[2]/td[4]/div/h3/span[2]/a")));
        wd.findElement(By.xpath("//div[9]/div[1]/div[2]/div[3]/div[1]/div/div/div/div[2]/div[12]/table/tbody[2]/tr[2]/td[4]/div/h3/span[2]/a")).click();
    }


    private void createFolder() {
        new WebDriverWait(wd,5).until(ExpectedConditions.elementToBeClickable(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createContent-button-button")));
        wd.findElement(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createContent-button-button")).click();
        new  WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable((By.className("folder-file"))));
        wd.findElement(By.className("folder-file")).click();
        new WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createFolder_prop_cm_name")));
        wd.findElement(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createFolder_prop_cm_name")).clear();
        new WebDriverWait(wd, 5).until(ExpectedConditions.elementToBeClickable(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createFolder_prop_cm_name")));
        wd.findElement(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createFolder_prop_cm_name")).sendKeys("test" + new Date().getTime());
        wd.findElement(By.id("template_x002e_documentlist_v2_x002e_sharedfiles_x0023_default-createFolder-form-submit-button")).click();
    }

    private void goToShare() {
        new WebDriverWait(wd,5).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Общие файлы")));
        wd.findElement(By.linkText("Общие файлы")).click();
    }

    private void login(String username, String password) {
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username")).click();
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username")).clear();
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-username")).sendKeys(username);
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password")).click();
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password")).clear();
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-password")).sendKeys(password);
        wd.findElement(By.id("page_x002e_components_x002e_slingshot-login_x0023_default-submit")).click();
    }

    private void goToLoginPage() {
        wd.get("http://tst01.alf.gosbroker.pro/share/page/");
    }

    @AfterMethod
    public void tearDown() {
        stop();
    }

    private void stop() {
        wd.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
