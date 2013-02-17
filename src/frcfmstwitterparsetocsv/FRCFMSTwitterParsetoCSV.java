package frcfmstwitterparsetocsv;

/**@author Alex & Armadi  */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class FRCFMSTwitterParsetoCSV
{
    public static void main(String[] args) 
    {
        try {
            new FRCFMSTwitterParsetoCSV().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String n;

    private void start() throws Exception
    {
        URL url = new URL("https://api.twitter.com/1/statuses/user_timeline.rss?screen_name=frcfms");
        URLConnection connection = url.openConnection();

        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("description");


       BufferedWriter writer = null;
       String csv = null;

        for(int i=0; i<20 /**descNodes.getLength()*/;i++)
        {
            String tweet;
            String csvbuffer;
            tweet = (descNodes.item(i).getTextContent());
            csvbuffer = tweet.replaceAll("#FRC(\\S+)\\sTY\\s([PQE])\\sMC\\s(\\d+)\\sRF\\s(\\d+)\\sBF\\s(\\d+)\\sRA\\s(\\d+)\\s(\\d+)\\s(\\d+)\\sBA\\s(\\d+)\\s(\\d+)\\s(\\d+)\\sRC\\s(\\d+)\\sBC\\s(\\d+)\\sRFP\\s(\\d+)\\sBFP\\s(\\d+)\\sRAS\\s(\\d+)\\sBAS\\s(\\d+)\\sRTS\\s(\\d+)\\sBTS\\s(\\d+)", "$1,$2,$3,$4,$5,$6,$7,$8,$9,$10,$11,$12,$13,$14,$15,$16,$17,$18,$19");
            System.out.println("----------------------------------");
            csv = (csv + writer.newLine() + csvbuffer);
            System.out.println(csv);
        }
 
            

	try
	{
		writer = new BufferedWriter( new FileWriter("G:\\frcfms.csv"));
		writer.write(csv);
	}
	catch ( IOException e)
	{
	}
	finally
	{
		try
		{
			if ( writer != null)
				writer.close( );
		}
		catch ( IOException e)
		{
		}
        }
        }
    private Document parseXML(InputStream stream)
    throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }       

        return doc;
    }
}

