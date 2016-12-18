/*
 * segDirectoryReader.java
 *
 * Created on February 26, 2007, 11:01 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package segReader;
import java.io.*;
import java.util.*;


/**
 *
 * @author steve
 */
public class segDirectoryReader {
    
   public File myDirectory = null;
   public String errorMessage = "";
   public String debugMessage = "";
   public String [] fileList = null;
   public HashMap htable = null;
   public HashMap dirtable = null;
   public Vector vArray = null;
   public int max_index = 0;
   public HashMap sortList = null;
   public String  parentPath = "";
   
    
    /** Creates a new instance of segDirectoryReader */
    public segDirectoryReader()
    {
       htable = new HashMap();
       vArray = new Vector();
       dirtable = new HashMap();
       sortList = new HashMap();
       max_index = 0;
    }
    
    public void openDirectory(String filename)
    {
            
       //myDirectory = new File(filename);
       
        try 
        {
             debugMessage += "Calling openDirectory metho inside of try statement \n";       
             myDirectory = new File(filename);
              
             //myDirectory.
             
             String absx = myDirectory.getAbsolutePath();
             String getpath = myDirectory.getPath();
             debugMessage += "What is value of getpath --->";
             debugMessage += getpath;
             
             //process(dir);
             
        }
        catch(Exception ex)
        {
            errorMessage = ex.toString();
        }
        
    }
    
    
    
    
   public String [] getFiles()
   {
       return fileList;
   }
    
   
   
   public String getParentTab()
   {
       
      return parentPath; 
       
   }
   
   public String [] getCategories_sorted()
   {
   
       int size = sortList.size();
       String catArray [] = new String[size];
       
       for(int index = 0; index < sortList.size(); index++)
       {
           Object k = String.valueOf(index);
           Object value = sortList.get(k);
           
          if(value != null) 
           catArray[index] = value.toString();
           
           
       }
       
       return catArray;
       
   }
   
   public Object [] getLinks(String key)
   {
       
       
    /*     Object myindex = htable.get(key);
         int index = Integer.parseInt(myindex.toString());
         Object list = vArray.get(index);
         ArrayList myList = (ArrayList) list;
                 
        return myList.toArray();
     */
     
       int link_count = htable.size();
       Object [] link_container = new Object[link_count + 1];
       Object myindex = htable.get(key);
       ArrayList myList = null;
         
       try
       {
            int index = Integer.parseInt(myindex.toString());
            Object list = vArray.get(index);
            myList = (ArrayList) list;
            link_container = myList.toArray();
            
       } 
       catch (Exception e)
       {
           
           link_container = null; 
       }
       
       return link_container;
   }   
   
   public String getNextLevel(String key)
   {
        Object value = null;
        String svalue = "";
        value = dirtable.get(key);
       try
       {
         svalue = value.toString();
       }
       catch(Exception ex)
       {
           svalue = "";
       }
        return svalue;
        
   }
   
   public String [] getCategories()
   {
       
        Set myset = htable.keySet();
        Iterator iter = myset.iterator();
        ArrayList list = new ArrayList();
        int size = htable.size();
        String catArray [] = new String [size];
        int counter = 0;
        
        while(iter.hasNext())
        {
            
            Object item = iter.next();
            //String value = iter.toString(); ???
            catArray[counter] = item.toString();
            counter++;
            
        }
       
        return catArray;
        
   }
   
    
   
   public void test(String filename)
   {
        
        File dir = new File(filename);
       
       visitAllDirsAndFiles(dir);
       
   }
   
   
   
   public void test2(String filename, int depth)
   {
       
        File dir = new File(filename);
        
        visitTopFilesandDir(dir, 0);
      
        File pdir = dir.getParentFile();
        parentPath = pdir.getPath();
        
        
   }
   
   public void visitTopFilesandDir(File dir, int depth) {
      // process(dir);
     
        if (dir.isDirectory() && depth == 0 || dir.isDirectory() && depth == 1 && dir.getName() != "SEGFiles" ) {
            
            
            //store filename 
             String value = dir.getPath();
             
             
             File pdirectory = dir.getParentFile();
             String parentfile = pdirectory.getPath();
                                
             
           
             String [] dirorder = null; 
             
             dirorder = value.split("\\\\");
             
             int len = dirorder.length;
             
             
             value = dirorder[len-1];
             
             
             try
             {
                 dirorder = value.split("\\_");
                 value = dirorder[1];
                 
             }
              catch(Exception ex)
              {
                  String bug  = ex.getMessage();
              }
             
             boolean found = false;
             found  = htable.containsKey(value);
             
             int isSEGDir = value.compareTo("SEGFiles");
             
             if(found == false && isSEGDir != 0 && depth != 0)
             {
                 Object hash_value = Integer.toString(max_index);
                 Object hash_key = value;
                 
                 htable.put(hash_key,hash_value);
                 ArrayList mylist = new ArrayList();
                 //mylist.add(value);
                 Object item = mylist;
                 vArray.add(item);
                 max_index++;
                 
                 Object dirpath = dir.getPath();
                 
                 String dirpaths1 = (String) dirpath;
                 dirpaths1 += "/";
                 dirpath = (Object) dirpaths1;
                 
                 
                 dirtable.put(hash_key,dirpath);
                         
             }
            
             try
             {
                if(found == false && isSEGDir != 0 && depth != 0)
                {
                 Object dorder = dirorder[1];
                 Object skey = dirorder[0];
                 
                 sortList.put(skey,dorder);
                 }
                   
             }
             catch(Exception ex)
             {
                 
             }
             
             
                    if(found == false && isSEGDir != 0 && depth == 0)
             {
                 Object hash_value = Integer.toString(max_index);
                 Object hash_key = value;
                 
                 htable.put(hash_key,hash_value);
                 ArrayList mylist = new ArrayList();
                 //mylist.add(value);
                 Object item = mylist;
                 vArray.add(item);
                 max_index++;
                 
                 Object dirpath = dir.getPath();
                 
                 String dirpaths1 = (String) dirpath;
                 dirpaths1 += "/";
                 dirpath = (Object) dirpaths1;
                 
                 
                 dirtable.put(hash_key,dirpath);
                         
             }
            
             try
             {
                if(found == false && isSEGDir != 0 && depth == 0)
                {
                 Object dorder = dirorder[1];
                 Object skey = dirorder[0];
                 
                 sortList.put(0,dorder);
                 }
                   
             }
             catch(Exception ex)
             {
                 
                 
             }
            
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitTopFilesandDir(new File(dir, children[i]) , depth + 1);
            }
        }
        else if (dir.isFile() && depth == 0 || dir.isFile() && depth == 1  )
        { 
            String afile = dir.getName();
            String apath = dir.getPath();
            String someparent = dir.getParent();
            String absolutepath = dir.getAbsolutePath();
                                 
            //String canpath = dir.getCanonicalPath();
                             
            /*String parent = dir.getParent();
              File fparent = dir.getParentFile();
              String zz  = fparent.getName();
              String zzs  = fparent.getName();
            */
             File pfile = dir.getParentFile();
             String value = pfile.getName();
             boolean found = false;
             found  = htable.containsKey(value);
             
             if(found == true)
             {
                 Object myindex = htable.get(value);
                 int index = Integer.parseInt(myindex.toString());
                 Object list = vArray.get(index);
                 ArrayList myList = (ArrayList) list;
                 myList.add(afile);
                         
             }
            // get the file name 
            // add it to the directory 
        }
        
       
       
    }
   
   
   
        // Process all files and directories under dir
    public static void visitAllDirsAndFiles(File dir) {
      // process(dir);
     
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));
            }
        }
        else if (dir.isFile() )
        { 
            String afile = dir.getName();
            String apath = dir.getPath();
            String someparent = dir.getParent();
            String absolutepath = dir.getAbsolutePath();
                                 
            //String canpath = dir.getCanonicalPath();
                             
            String parent = dir.getParent();
            File fparent = dir.getParentFile();
            String zz  = fparent.getName();
             String zzs  = fparent.getName();
             
            // get the file name 
            // add it to the directory 
        }
        
       
       
    }
    
    // Process only directories under dir
    public static void visitAllDirs(File dir) {
        if (dir.isDirectory()) {
           //process(dir);
    
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitAllDirs(new File(dir, children[i]));
            }
        }
    }
    
    // Process only files under dir
    public static void visitAllFiles(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                visitAllFiles(new File(dir, children[i]));
            }
        } else {
          // process(dir);
        }
    }

       
    
   public void ReadAllFiles()
   {
      fileList = myDirectory.list();
       
     
      if (fileList == null)
      {
         // sorry could not find any files in this directory     
      }
      else if ( fileList != null)
      {
          for ( int counter = 0; counter < fileList.length; counter++)
          {
              
              //get the pre Extension of the file name 
              //1 first parse this information 
              //2 find out if this file name exist already 
              // if not then create an new array to hold these file types
             
             boolean found = false;
             String file  = ""; 
             String [] split2 = null;
             String value = "";
             String value2 = "";
             
             try
             {
                 
                 file = fileList[counter];
                 split2 = file.split("\\.");
                // int split_count = split2.length;
                  value = split2[0];
                  value2 = split2[1];
                 
                 
             }
             catch(Exception e)
             {
                 
                 
                 
             }
       
             
             
             found  = htable.containsKey(value);
             
             if(found == false)
             {
                 Object hash_value = Integer.toString(max_index);
                 Object hash_key = value;
                 
                 htable.put(hash_key,hash_value);
                 ArrayList mylist = new ArrayList();
                 mylist.add(file);
                 Object item = mylist;
                 vArray.add(item);
                 max_index++;
                         
             }
             else if( found == true)
             {
                 
                 Object myindex = htable.get(value);
                 int index = Integer.parseInt(myindex.toString());
                 Object list = vArray.get(index);
                 ArrayList myList = (ArrayList) list;
                 myList.add(file);
             }
        }
             
             

             
             
            
             
             
          }
          
      }
      
	  public void NewFeatureFunction1()
	  {
            //adding stubs for new feature 1		   
	
	  }


	  public void NewFeatureFunction2()
	  {
              //adding stubs for new feature 2		   
	
	  }

   
}
 
   
      
   

