package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class CompareProductsPage extends BasePage {


    public CompareProductsPage(WebDriver driver) {
        super(driver);
    }


    public Boolean VerificarSiEstanProductosEnCompareList(ArrayList<String> arrayDeNombres){
        Boolean resultado=false;
        for (int i = 0;i<arrayDeNombres.size();i++)
        {
           // System.out.println("get["+i+"]:"+arrayDeNombres.get(i));
            try
            {
                driver.findElement(By.xpath("//*[@class='table-wrapper']//*[contains(text(),'"+arrayDeNombres.get(i)+"')]"));
                resultado=true;
            }
            catch(Exception e)
            {
                System.out.println("No esta producto");
            }
        }
        return resultado;

    }


    public Boolean estoyEnComparePage(){
        return driver.findElement(By.xpath("//h1[contains(text(),'Compare products')]")).isDisplayed();
    }

    public Boolean compareListIsEmpty(){

        Boolean apareceTexo = false;
        try
        {
            if (driver.findElement(By.xpath("//*[contains(text(),'You have no items to compare.')]")).isDisplayed())
            {
                apareceTexo=true;
            }
        }
        catch (Exception e)
        {
            //si llega aca es porque la lista tiene productos y no encontró el texto 'You have no items to compare'
            System.out.println("");
        }

        return apareceTexo;
    }

}
