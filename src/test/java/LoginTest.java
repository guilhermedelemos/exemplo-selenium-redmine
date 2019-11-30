import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void loginInvalidoTest() {
        driver.get("http://demo.redmine.org/login");

        LoginPage login = new LoginPage(driver);
        login.setUsuario("asdfasdfasdfasd");
        login.setSenha("asdfasdfsdfasd");
        login.entrar();

        WebElement erro = driver.findElement(By.id("flash_error"));
        assertNotNull(erro);
        assertEquals("Usuário ou senha inválido.", erro.getText());
    }

    @Test
    public void loginComValoresEmBrancoTest() {
        driver.get("http://demo.redmine.org/login");

        LoginPage login = new LoginPage(driver);

        String resultado = login.executarLoginComCamposEmBranco();
        assertEquals("Usuário ou senha inválido.", resultado);
    }

}
