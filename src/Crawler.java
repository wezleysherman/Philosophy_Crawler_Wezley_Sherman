import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {
	private static String CRAWLER_AGENT = "Philosophy Crawler";
	private Document doc;
	public String nextUrl;
	public boolean crawlCurrentPage(String url, String findName)
	{
		try
		{
			Connection con = Jsoup.connect(url).userAgent(CRAWLER_AGENT);
			Document curDoc = con.get();
			this.doc = curDoc;
			Elements pageLinks = doc.select("a[href]");
			nextUrl = pageLinks.get(0).absUrl("href");
			System.out.println(nextUrl);
			boolean containsName = getPageTitle() == findName.toLowerCase();
			System.out.println(getPageTitle());
			if(containsName)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(IOException e)
		{
			return false;
		}
	}
	
	public String getPageTitle()
	{
		return this.doc.title().toLowerCase();
	}
}
