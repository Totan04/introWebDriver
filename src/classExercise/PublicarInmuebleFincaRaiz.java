package classExercise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class PublicarInmuebleFincaRaiz {
	
	static WebDriver driver;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		navegar ("https://www.fincaraiz.com.co/");
		
		validarPaginaPrincipal ();
		
		clickPublicar ();
		
		verificarFormularioDatosDeAviso ();

	}

	private static void verificarFormularioDatosDeAviso() {
		// TODO Auto-generated method stub
		WebElement tipoOfertaCel = driver.findElement(By.id("ctl00_phMasterPage_AdvertControl_cboTransaction"));
		
		if (tipoOfertaCel.isDisplayed()) {
			System.out.println("Se muestra el elemento de transaccion");
			Select elem = new Select(tipoOfertaCel);
			elem.selectByVisibleText("Venta");
		}
		
		
	}

	private static void clickPublicar() {
		// TODO Auto-generated method stub
		WebElement botonPublicar = driver.findElement(By.id("btnPriceAlert"));
		botonPublicar.click();
		
	}

	private static void validarPaginaPrincipal() {
		// TODO Auto-generated method stub
		
	}

	private static void navegar(String url) {
		// TODO Auto-generated method stub
		driver = new ChromeDriver ();
		driver.get(url);
		
	}

}
