package tuts4java;

import java.awt.List;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.*;

/**
 * @author {Andreas}
 *prints out the name of a class (including its package name), its super class, and all of its constructors, 
 *methods, and fields,including parameter and field types and modifiers (such as static and final)
 */
public class XMLDumper
{
   /**
     @param argClass the class to be queried. Can be either a Class object
     that describes the class or an instance of that class.
   */
	   private static final String BLANKS = "                             ";
	   private static final int INDENT = 3;
	   private static List xmlPrint = new List();
	   
   public static void dumpClass(Object classArg)
   {
      // What kind of parameter do we have
      if (classArg == null)
      {
         dumpln(0, "null");
         return;
      }
      Class theClass;
      if (classArg instanceof Class)
         theClass = (Class) classArg;
      else
         theClass = classArg.getClass();

      // The package
      Package thePackage = theClass.getPackage();

      if (thePackage != null)
         dumpln(0, "package " + thePackage.getName() + ";\n");

      // Name of the class or interface
      String className;
      if (thePackage == null)
         className = theClass.getName();
      else
         className =
            theClass.getName().substring(1 + thePackage.getName().length());

      String fullClassName;

      String modifiers = Modifier.toString(theClass.getModifiers());
      if (!modifiers.equals(""))
         modifiers = modifiers + " ";
      // modifiers contains "interface" but not "class"!
      if (theClass.isInterface())
         fullClassName = modifiers + className;
      else
         fullClassName = modifiers + "class " + className;

      // Superclass
      if (theClass.getSuperclass() != null
           && theClass.getSuperclass() != Object.class)
      {
         fullClassName = fullClassName +
               " extends " + theClass.getSuperclass().getName();
      }

      dumpln(0, fullClassName);

      // What interfaces does theClass extend or implement?
      Class[] interfaceList = theClass.getInterfaces();
      if (interfaceList.length != 0)
      {
         if (theClass.isInterface())
            dump(1, "extends ");
         else
            dump(1, "implements ");

         for (Class c : interfaceList)
            dump(0, c.getName() + ", ");
         dumpln(0, interfaceList[interfaceList.length - 1].getName());
      }

      // Code after class name starts here
      dumpln(0, "{");

      // Constructors
      Constructor[] constructorList = theClass.getDeclaredConstructors();
      if (constructorList.length > 0)
      {
         for (Constructor ct : constructorList)
         {
            String constructorName = ct.toString();
            modifiers = Modifier.toString(ct.getModifiers());
            if (!modifiers.equals(""))
               modifiers = modifiers + " ";

            String parameterList = constructorName.substring(
                  constructorName.indexOf("("));

            dumpln(1, modifiers + className + parameterList + ";");
         }
      }

      // Methods
      Method[] methodList = theClass.getDeclaredMethods();
      if (methodList.length > 0)
      {
         // Add a blank line, if needed
         if (constructorList.length > 0)
            dumpln(0, "");

         for (Method m : methodList)
         {
            String methodString = m.toString();

            modifiers = Modifier.toString(m.getModifiers());
            if (!modifiers.equals(""))
               modifiers = modifiers + " ";

            String returnType = m.getReturnType().getName();

            String parameterList = methodString.substring(
                  methodString.indexOf("("));

            String methodName = m.getName();

            dumpln(1, modifiers + returnType + " "
                  + methodName + parameterList + ";");
         }
      }

      // Fields
      Field[] fieldList = theClass.getDeclaredFields();
      if (fieldList.length > 0)
      {
         // Add a blank line, if needed
         if (constructorList.length + methodList.length > 0)
            dumpln(0, "");

         for (Field f : fieldList)
         {        

            modifiers = Modifier.toString(f.getModifiers());
            if (!modifiers.equals(""))
               modifiers = modifiers + " ";

            String fieldType = f.getType().getName();

            String fieldName = f.getName();

            dumpln(1, modifiers + fieldType + " " + fieldName + ";");
         }
      }

      // Code ends here
      dumpln(0, "}");
      // at the end of object recording, print to XML file.
      try {
  		FileOutputStream fos = new FileOutputStream(new File("./XMLConvertedObject.xml"));
  		XMLEncoder encoder = new XMLEncoder(fos);
  		encoder.writeObject(xmlPrint);
  		encoder.close();
  		fos.close();
  	}
  	catch(IOException ex) {
  		ex.printStackTrace();
  	}
   }

   private static void dump(int level, String s)
   {
	   // instead of printing dump will now add to String List
      xmlPrint.add(BLANKS.substring(1, 1 + INDENT * level) + s);
   }

   private static void dumpln(int level, String s)
   {
      dump(level, s + "\n");
   }
   
	
	
   

}
