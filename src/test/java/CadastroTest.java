import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CadastroTest {

    private static final String USUARIO_NOVO = randomUsername();
    private static final String EMAIL_NOVO = USUARIO_NOVO + "@exemplo.com";
    public static final String EMAIL_INVALIDO_SEM_ARROBA = "invalido+exemplo.com";
    public static final String EMAIL_INVALIDO_SEM_IDENTIFICADOR = "@exemplo.com";
    public static final String EMAIL_INVALIDO_SEM_DOMINIO = "fulano@";

    private WebDriver driver;

    public CadastroTest() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    public WebElement getUsuario() {
        return driver.findElement(By.id("user_login"));
    }

    public static String randomUsername() {
        return UUID.randomUUID().toString();
    }

    @Test
    public void cadastroComSucessoTest() {
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

    @Test
    public void emailInvalido() {
        // Acessa a página desejada
        driver.get("http://demo.redmine.org/account/register");

        // Exemplo XPATH
        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
        assertEquals("Cadastre-se", titulo.getText());

        WebElement usuario = driver.findElement(By.id("user_login"));
        assertNotNull(usuario);
        usuario.sendKeys(this.randomUsername());

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
        email.sendKeys(EMAIL_INVALIDO_SEM_ARROBA);

        WebElement enviar = driver.findElement(By.name("commit"));
        assertNotNull(enviar);
        enviar.click();

        WebElement falha = driver.findElement(By.xpath("//*[@id=\"errorExplanation\"]/ul/li"));
        assertNotNull(falha);
        assertEquals("E-mail não é válido", falha.getText());

        // Fecha o navegador
//        driver.quit();
    }

    @Test
    public void emailInvalidoSemDominio() {
        // Acessa a página desejada
        driver.get("http://demo.redmine.org/account/register");

        // Exemplo XPATH
        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
        assertEquals("Cadastre-se", titulo.getText());

//        WebElement usuario = driver.findElement(By.id("user_login"));
        WebElement usuario = this.getUsuario();
        assertNotNull(usuario);
        usuario.sendKeys(this.randomUsername());

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
        email.sendKeys(EMAIL_INVALIDO_SEM_DOMINIO);

        WebElement enviar = driver.findElement(By.name("commit"));
        assertNotNull(enviar);
        enviar.click();

        WebElement falha = driver.findElement(By.xpath("//*[@id=\"errorExplanation\"]/ul/li"));
        assertNotNull(falha);
        assertEquals("E-mail não é válido", falha.getText());

        // Fecha o navegador
//        driver.quit();
    }

}
