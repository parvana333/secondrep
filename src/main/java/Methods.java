
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class Methods{

    public String findRNN(File file, String RNN) throws IOException {
        String transacId="";
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(RNN)) {
                transacId=line.substring(0,9);
                break;
            }
        }
        bufferedReader.close();
        return transacId;
    }

    public List<String> findId(File file,String transacId) throws IOException {
        List<String> list=new ArrayList<>();
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line=null;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.contains(transacId)) {
               list.add(line);
            }
        }
        bufferedReader.close();
        return list;
    }

     public void createDoc(List<String> list, String directory,String filename,String RNN) throws IOException {
         XWPFDocument document=new XWPFDocument();
         XWPFParagraph paragraph=document.createParagraph();
         XWPFRun run=paragraph.createRun();
         File file=new File(directory);
         if(!file.exists()){
             file.mkdirs();
         }
         FileOutputStream fileOutputStream=new FileOutputStream(new File(directory+filename));
         for(String l:list){
             if(l.contains(RNN)) {
                 run.setText(l.substring(0,l.length()-RNN.length()));
                 XWPFRun run1=paragraph.createRun();
                 run1.setText(RNN);
                 run1.setTextHighlightColor("red");
                // run1.addBreak();
             }
             else{
                 XWPFParagraph paragraph2=document.createParagraph();
                 XWPFRun run2=paragraph2.createRun();
                 run2.setText(l);
                 //run2.addBreak();

             }


         }
         document.write(fileOutputStream);
         fileOutputStream.close();
     }
     public void highlight(File file,String RNN) throws IOException, InvalidFormatException {
         try {
             XWPFDocument document=new XWPFDocument();
             FileInputStream fis = new FileInputStream("test.docx");
             XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
             XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
             String text = extractor.getText();
             if(text.contains(RNN)){


             }


         } catch(Exception ex) {
             ex.printStackTrace();
         }


         }

}
