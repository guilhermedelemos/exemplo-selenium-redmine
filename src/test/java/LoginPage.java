import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement usuario;
    @FindBy(id = "password")
    private WebElement senha;
    @FindBy(name = "login")
    private WebElement entrar;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getUsuario() {
        return usuario.getText();
    }

    public void setUsuario(String usuario) {
        this.usuario.sendKeys(usuario);
    }

    public String getSenha() {
        return senha.getText();
    }

    public void setSenha(String senha) {
        this.senha.sendKeys(senha);
    }

    public void entrar() {
        this.entrar.click();
    }

    public String executarLoginComCamposEmBranco() {
        this.setUsuario("");
        this.setSenha("");
        this.entrar();
        WebElement erro = driver.findElement(By.id("flash_error"));
        if(erro == null) {
            return "";
        } else {
            return erro.getText();
        }
    }

}
