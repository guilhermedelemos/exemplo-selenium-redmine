import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CadastroTest {

    private static final String USUARIO_NOVO = "univale1";
    private static final String EMAIL_NOVO = USUARIO_NOVO + "@exemplo.com";

    @Test
    public void cadastroComSucessoTest() {
        WebDriverManager.chromedriver().setup();

        //ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless"); // Descomente se quiser esconder o navegador (apenas chrome)
        //chromeOptions.addArguments("window-size=1200x600");
        //chromeOptions.addArguments("start-maximized");

        // Instância do driver de navegador a ser usado
        WebDriver driver = new ChromeDriver();

        // Acessa a página desejada
        driver.get("http://demo.redmine.org/account/register");

        // Exemplo XPATH
        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
        assertEquals("Cadastre-se", titulo.getText());

        WebElement usuario = driver.findElement(By.id("user_login"));
        assertNotNull(usuario);
        usuario.sendKeys(USUARIO_NOVO);

        WebElement senha = driver.findElement(By.id("user_password"));
        assertNotNull(senha);
        senha.sendKeys("123mudar");

        WebElement confirmacao = driver.findElement(By.id("user_password_confirmation"));
        assertNotNull(confirmacao);
        confirmacao.sendKeys("123mudar");

        WebElement nome = driver.findElement(By.id("user_firstname"));
        assertNotNull((nome));
        nome.sendKeys("Fulano");

        WebElement sobrenome = driver.findElement(By.id("user_lastname"));
        assertNotNull(sobrenome);
        sobrenome.sendKeys("de Tal");

        WebElement email = driver.findElement(By.id("user_mail"));
        assertNotNull(email);
        email.sendKeys(EMAIL_NOVO);

        Select idioma = new Select(driver.findElement(By.id("user_language")));
        assertNotNull(idioma);
        idioma.selectByValue("en");

        WebElement enviar = driver.findElement(By.name("commit"));
        assertNotNull(enviar);
        enviar.click();

        WebElement sucesso = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
        assertNotNull(sucesso);
        assertEquals("My account", sucesso.getText());

        // Fecha o navegador
//        driver.quit();
    }

}
