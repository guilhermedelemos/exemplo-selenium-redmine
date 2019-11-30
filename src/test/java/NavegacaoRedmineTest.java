import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NavegacaoRedmineTest {
    
    @Test
    public void navegacaoTest() {
        // Carrega o driver do navegador
        WebDriverManager.chromedriver().setup();
        
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
        assertNotNull(busca);
        
        // Exemplo CSS SELECTOR
        WebElement noticias = driver.findElement(By.cssSelector("#content > div.splitcontentleft > div > h3"));
        assertNotNull(noticias);
        
        // Exemplo CLICK
        WebElement menuAjuda = driver.findElement(By.xpath("//*[@id=\"top-menu\"]/ul/li[3]/a"));
        menuAjuda.click();
        
        // Fecha o navegador
        driver.quit();
    }
    
}
