
package writerdom;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class WriterDom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
     File file = new File("src\\writerdom\\Empleados.xml");
                             
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            
            Document document = createRootFile(implementation);
            createEmployeeElement(document);
            createBossElement(document);
            
            /**
             * Creación de la fuente XML a partir del documento
             */
            Source source = new DOMSource(document);
            
            /**
             * Se crea el resultado en el fichero nuevosEmpleados.xml
             */
            Result result = new StreamResult(file);
            
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            
            /**
             * Se realiza la transformación del documento a fichero
             */
            transformer.transform(source, result);            
            System.out.println("Bien Escritura");
        }catch(Exception exception){
            System.out.println("Error: " + exception.getMessage());
        }      
    }
    
    /**
     * Función para crear la raiz del fichero
     * @param implementation
     * @return 
     */
    private static Document createRootFile(DOMImplementation implementation)
    {
        /**
         * Asignamos "Empleado" como nodo raíz
         */
        Document document = implementation.createDocument(null, "Empleados", null);
        /**
         * Asignamos la versión de nuestro fichero XML
         */
        document.setXmlVersion("1.0");
        
        return document;
    }
    
    /**
     * Función para insertar los atributos de cada empleado
     * @param document 
     */
    private static void createEmployeeElement(Document document)
    {
        Element elementoEmpleado = document.createElement("empleado");//creo nodo empleado
        document.getDocumentElement().appendChild(elementoEmpleado);//obtengo nodo raiz y asocio nodo empleado
        
        Element elementId = document.createElement("id");//creo nodo ir
        Text textId = document.createTextNode("1");//creo texto 
        elementId.appendChild(textId);//asocio texto a nodo id
        
        elementoEmpleado.appendChild(elementId);//asocio nodo id a nodo empleado
        
        Element elementNombre = document.createElement("nombre");//creo nodo nombre
        Text textNombre = document.createTextNode("Sergio");//creo texto del nodo nombre
       
        elementNombre.appendChild(textNombre);//asocio textoNombre a nodo nombre
        
        elementoEmpleado.appendChild(elementNombre);//asocio nodo nombre a empleado
               
    }   
    
    private static void createBossElement(Document document){
        
       Element elementJefe = document.createElement("jefe");
       document.getDocumentElement().appendChild(elementJefe);
       
       Element elementIdJefe = document.createElement("id");//creo nodo ir
              
       Text textId = document.createTextNode("1000");//creo valor del texto id
       elementIdJefe.appendChild(textId);
       
       elementJefe.appendChild(elementIdJefe);
       
              
       
       
               
    }
    
}
