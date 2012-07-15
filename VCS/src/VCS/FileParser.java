/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package VCS;

import java.io.FileWriter;
import java.io.IOException;
import java.text.AttributedCharacterIterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author lavz24
 */
public  class FileParser {
  
  
  public static Document parserFile(String file)
  {
    Document document = null;
    SAXReader reader = new SAXReader();
    
    try
    {
        document = reader.read(file);    
    }
    catch (DocumentException e)
    {
        //Imprimir error 
       System.err.print("El archivo con el respaldo de los servidores no se pudo abrir.");
       System.exit(1);
    }
    
    return document;
  }
  
  /*
   * Return server Elements in XML
   */
  public static List<Element> serverList(Document document)
  {
     Element root = document.getRootElement();
     List<Element> list = root.elements();

     return list;
  }
  
  public static String getValueOfServer(Element server, String attribute )
  { 
      List<Element> list = server.elements();
      for (Element lista:list)
      {
          if(attribute.equals( lista.getName()))
          {
              return lista.getStringValue();
          }
      }
      return "";
  }
  public static List<Element> getFilesServer(Element server )
  {
      List<Element> list = server.elements();
      for (Element lista:list)
      {
          if("data".equals( lista.getName()))
          {
              return lista.elements();
          }
      }
      
      return null;
  }
  
  public static String getValueOfFile(Element file, String attribute )
  {
      List<Element> list = file.elements();
      for (Element lista:list)
      {
          if(attribute.equals( lista.getName()))
          {
              return lista.getStringValue();
          }
      }
      
      return null;
  }
  
  public static void updateXMLFile(String config,Document document)
  {
        try {
            XMLWriter writer = new XMLWriter( new FileWriter(config));
            
            writer.write(document);
            writer.close();
        } catch (IOException ex) {
          System.err.println("No se pudo actualizar el archivo de configuracion.");
          //Ver que hacer aqui
        }
      
  }
  
}