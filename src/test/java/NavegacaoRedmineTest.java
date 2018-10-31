import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;
import java.util.concurrent.ThreadLocalRandom;

public class NavegacaoRedmineTest {
    
    @Test
    public void navegacaoTest() {
        // Carrega o driver do navegador
        ChromeDriverManager.getInstance().setup(); 
        
        // Define opções do navegador
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("headless"); // Descomente se quiser esconder o navegador (apenas chrome)
        chromeOptions.addArguments("window-size=1200x600");
        chromeOptions.addArguments("start-maximized");
        
        // Instância do driver de navegador a ser usado
        WebDriver driver = new ChromeDriver(chromeOptions);
        
        // Acessa a página desejada
        driver.get("http://demo.redmine.org");
        
        // Exemplo XPATH
        WebElement titulo = driver.findElement(By.xpath("//*[@id=\"content\"]/h2"));
        assertEquals("Página inicial", titulo.getText());
        
        // Exemplo ID
        WebElement rodape = driver.findElement(By.id("footer"));
        assertNotNull(rodape);
        
        // Exemplo NAME
        WebElement busca = driver.findElement(By.name("q"));
        busca.sendKeys("projeto");
        assertEquals("projeto", busca.getText());
        
        // Exemplo CSS SELECTOR
        WebElement noticias = driver.findElement(By.cssSelector("#content > div.splitcontentleft > div > h3"));
        assertNotNull(noticias);
        
        // Fecha o navegador
        //driver.quit();
    }
    
}
